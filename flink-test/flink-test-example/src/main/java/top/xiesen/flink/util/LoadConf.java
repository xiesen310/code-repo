package top.xiesen.flink.util;

import org.apache.flink.shaded.jackson2.org.yaml.snakeyaml.Yaml;
import org.apache.flink.shaded.jackson2.org.yaml.snakeyaml.constructor.SafeConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * @description: 加载配置信息工具类
 * @author: 谢森
 * @Email xiesen@zork.com.cn
 * @time: 2020/1/9 0009 14:17
 */
public class LoadConf {
    private static final Logger LOG = LoggerFactory.getLogger(LoadConf.class);

    public LoadConf() {
    }

    private static List<URL> findResources(String name) {
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(name);
            ArrayList ret = new ArrayList();
            while (resources.hasMoreElements()) {
                ret.add(resources.nextElement());
            }

            return ret;
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    /**
     * 读取 yaml 配置文件
     *
     * @param name
     * @param mustExist
     * @param canMultiple
     * @return
     */
    private static Map findAndReadYaml(String name, boolean mustExist, boolean canMultiple) {
        InputStream in = null;
        boolean confFileEmpty = false;

        try {
            in = getConfigFileInputStream(name, canMultiple);
            if (null != in) {
                Yaml yaml = new Yaml(new SafeConstructor());
                Map ret = (Map) yaml.load(new InputStreamReader(in));
                if (null != ret) {
                    HashMap var7 = new HashMap(ret);
                    return var7;
                }

                confFileEmpty = true;
            }

            if (mustExist) {
                if (confFileEmpty) {
                    throw new RuntimeException("Config file " + name + " doesn't have any valid storm configs");
                } else {
                    throw new RuntimeException("Could not find config file on classpath " + name);
                }
            } else {
                HashMap var19 = new HashMap();
                return var19;
            }
        } catch (IOException var17) {
            throw new RuntimeException(var17);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException var16) {
                    throw new RuntimeException(var16);
                }
            }

        }
    }

    /**
     * 读取配置文件输入流
     *
     * @param configFilePath 配置文件路径
     * @param canMultiple
     * @return
     * @throws IOException
     */
    private static InputStream getConfigFileInputStream(String configFilePath, boolean canMultiple) throws IOException {
        if (null == configFilePath) {
            throw new IOException("Could not find config file, name not specified");
        } else {
            HashSet<URL> resources = new HashSet(findResources(configFilePath));
            if (resources.isEmpty()) {
                File configFile = new File(configFilePath);
                return configFile.exists() ? new FileInputStream(configFile) : null;
            } else if (resources.size() > 1 && !canMultiple) {
                throw new IOException("Found multiple " + configFilePath + " resources. You're probably bundling the Storm jars with your topology jar. " + resources);
            } else {
                LOG.info("Using " + configFilePath + " from resources");
                URL resource = (URL) resources.iterator().next();
                return resource.openStream();
            }
        }
    }

    private static InputStream getConfigFileInputStream(String configFilePath) throws IOException {
        return getConfigFileInputStream(configFilePath, true);
    }

    /**
     * 读取 yaml 配置文件
     *
     * @param confPath 文件路径
     * @return
     */
    public static Map LoadYaml(String confPath) {
        return findAndReadYaml(confPath, true, true);
    }

    /**
     * 读取 Property 文件
     *
     * @param prop
     * @return
     */
    public static Map LoadProperty(String prop) {
        InputStream in = null;
        Properties properties = new Properties();

        try {
            in = getConfigFileInputStream(prop);
            properties.load(in);
        } catch (FileNotFoundException var12) {
            throw new RuntimeException("No such file " + prop);
        } catch (Exception var13) {
            throw new RuntimeException("Failed to read config file");
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException var11) {
                    throw new RuntimeException(var11);
                }
            }

        }

        Map ret = new HashMap();
        ret.putAll(properties);
        return ret;
    }
}
