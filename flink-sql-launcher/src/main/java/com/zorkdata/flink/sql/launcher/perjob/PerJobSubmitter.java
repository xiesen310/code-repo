package com.zorkdata.flink.sql.launcher.perjob;

import com.zorkdata.flink.sql.launcher.LauncherOptions;
import com.zorkdata.flink.sql.util.PluginUtil;
import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.client.deployment.ClusterSpecification;
import org.apache.flink.client.program.ClusterClient;
import org.apache.flink.core.fs.Path;
import org.apache.flink.runtime.jobgraph.JobGraph;
import org.apache.flink.shaded.guava18.com.google.common.base.Strings;
import org.apache.flink.shaded.guava18.com.google.common.collect.Sets;
import org.apache.flink.yarn.AbstractYarnClusterDescriptor;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class PerJobSubmitter {

    private static final Logger LOG = LoggerFactory.getLogger(PerJobSubmitter.class);

    public static String submit(LauncherOptions launcherOptions, JobGraph jobGraph) throws Exception {

		fillJobGraphClassPath(jobGraph);
		if (!StringUtils.isBlank(launcherOptions.getAddjar())) {
			String addjarPath = URLDecoder.decode(launcherOptions.getAddjar(), Charsets.UTF_8.toString());
			List<String>  paths = getJarPaths(addjarPath);
			paths.forEach( path -> {
				jobGraph.addJar(new Path("file://" + path));
			});
		}



		String confProp = launcherOptions.getConfProp();
        confProp = URLDecoder.decode(confProp, Charsets.UTF_8.toString());
        Properties confProperties = PluginUtil.jsonStrToObject(confProp, Properties.class);
        ClusterSpecification clusterSpecification = FLinkPerJobResourceUtil.createClusterSpecification(confProperties);

        PerJobClusterClientBuilder perJobClusterClientBuilder = new PerJobClusterClientBuilder();
        perJobClusterClientBuilder.init(launcherOptions.getYarnconf());

        String flinkJarPath = launcherOptions.getFlinkJarPath();

        AbstractYarnClusterDescriptor yarnClusterDescriptor = perJobClusterClientBuilder.createPerJobClusterDescriptor(confProperties, flinkJarPath, launcherOptions.getQueue());
        ClusterClient<ApplicationId> clusterClient = yarnClusterDescriptor.deployJobCluster(clusterSpecification, jobGraph,true);

        String applicationId = clusterClient.getClusterId().toString();
        String flinkJobId = jobGraph.getJobID().toString();

        String tips = String.format("deploy per_job with appId: %s, jobId: %s", applicationId, flinkJobId);
        System.out.println(tips);
        LOG.info(tips);

        return applicationId;
    }

	private static List<String> getJarPaths(String addjarPath) {
		if (addjarPath.length() > 2) {
			addjarPath = addjarPath.substring(1,addjarPath.length()-1).replace("\"","");
		}
		List<String> paths = Arrays.asList(addjarPath.split(","));
		return paths;
	}

	private static void fillJobGraphClassPath(JobGraph jobGraph) throws MalformedURLException {
		Map<String, String> jobCacheFileConfig = jobGraph.getJobConfiguration().toMap();
		Set<String> classPathKeySet = Sets.newHashSet();

		for(Map.Entry<String, String> tmp : jobCacheFileConfig.entrySet()){
			if(Strings.isNullOrEmpty(tmp.getValue())){
				continue;
			}

			if(tmp.getValue().startsWith("class_path")){
				//DISTRIBUTED_CACHE_FILE_NAME_1
				//DISTRIBUTED_CACHE_FILE_PATH_1
				String key = tmp.getKey();
				String[] array = key.split("_");
				if(array.length < 5){
					continue;
				}

				array[3] = "PATH";
				classPathKeySet.add(StringUtils.join(array, "_"));
			}
		}

		for(String key : classPathKeySet){
			String pathStr = jobCacheFileConfig.get(key);
			jobGraph.getClasspaths().add(new URL("file:" + pathStr));
		}
	}
}
