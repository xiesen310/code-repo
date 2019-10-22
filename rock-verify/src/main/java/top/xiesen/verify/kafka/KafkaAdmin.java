package top.xiesen.verify.kafka;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.cluster.Cluster;
import kafka.server.ConfigType;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import scala.collection.JavaConversions;
import scala.collection.Map;
import scala.collection.Set;
import top.xiesen.verify.pojo.JSONResult;
import top.xiesen.verify.pojo.KafkaTopicBean;
import top.xiesen.verify.pojo.ZookeeperBean;
import top.xiesen.verify.utils.ConfigUtils;
import top.xiesen.verify.utils.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;

/**
 * KafkaAdmin
 */
public class KafkaAdmin {
    ZkUtils zkUtils = null;

    public KafkaAdmin(ZookeeperBean zookeeperBean) {
        String zkUrl = ConfigUtils.getString(zookeeperBean.getZkUrl(), "localhost:2181");
        Integer sessionTimeout = ConfigUtils.getInteger(zookeeperBean.getSessionTimeout(), 30000);
        Integer connectionTimeout = ConfigUtils.getInteger(zookeeperBean.getConnectionTimeout(), 30000);
        this.zkUtils = ZkUtils.apply(zkUrl, sessionTimeout, connectionTimeout, JaasUtils.isZkSecurityEnabled());
    }

    /**
     * 创建: bin/kafka-topics.sh --zookeeper 192.168.1.91:2181/01021 --create --partitions 2 --replication-factor 1 --topic test
     *
     * @param kafkaTopicBean
     * @return JSONResult
     */
    public JSONResult createKafkaTopic(KafkaTopicBean kafkaTopicBean) {
        if (!topicExists(kafkaTopicBean.getTopicName())) {
            try {
                AdminUtils.createTopic(zkUtils, kafkaTopicBean.getTopicName(), kafkaTopicBean.getPartition(),
                        kafkaTopicBean.getReplication(), new Properties(), new RackAwareMode.Enforced$());
                return JSONResult.ok("创建 " + kafkaTopicBean.getTopicName() + " 成功");
            } catch (Exception e) {
                return JSONResult.errorMsg("创建 " + kafkaTopicBean.getTopicName() + " 失败");
            } finally {
                zkUtils.close();
            }
        } else {
            return JSONResult.errorMsg("创建 " + kafkaTopicBean.getTopicName() + " 失败");
        }
    }


    /**
     * 判断 topic 是否存在
     *
     * @param topicName 主题
     * @return boolean
     */
    public boolean topicExists(String topicName) {
        return StringUtils.isEmpty(topicName) ? false : AdminUtils.topicExists(zkUtils, topicName);
    }


    /**
     * 列举: bin/kafka-topics.sh --zookeeper 192.168.1.91:2181/01021 --list
     *
     * @return List<String>
     */
    public JSONResult<List<String>> listKafkaTopic() {
        List<String> allTopicList = new ArrayList<>();
        try {
            allTopicList = JavaConversions.seqAsJavaList(zkUtils.getAllTopics());
        } catch (Exception e) {
            return JSONResult.errorMsg("获取 kafka topic 列表失败");
        } finally {
            zkUtils.close();
        }
        return JSONResult.ok(allTopicList);
    }

    /**
     * 删除 topic
     *
     * @param topicName
     */
    public JSONResult deleteKafkaTopic(String topicName) {
        try {
            if (topicExists(topicName)) {
                AdminUtils.deleteTopic(zkUtils, topicName);
                return JSONResult.ok("删除 " + topicName + " 成功");
            } else {
                return JSONResult.errorMsg("删除失败, " + topicName + " 不存在");
            }
        } catch (Exception e) {
            return JSONResult.errorMsg("删除 " + topicName + " 失败");
        } finally {
            zkUtils.close();
        }
    }

    /**
     * 删除多个topic
     *
     * @param topicNames
     * @return
     */
    public String[] deleteKafkaTopics(final String... topicNames) {
        if (topicNames == null || topicNames.length == 0) return new String[0];
        java.util.Set<String> deleted = new LinkedHashSet<String>();
        for (String topicName : topicNames) {
            if (topicName != null || !topicName.trim().isEmpty()) {
                deleteKafkaTopic(topicName);
                deleted.add(topicName.trim());
            }
        }
        return deleted.toArray(new String[deleted.size()]);
    }


    /**
     * 获取集群信息
     *
     * @return Cluster
     */
    public Cluster getCluster() {
        return zkUtils.getCluster();
    }

    /**
     * 获取指定topic的配置信息
     *
     * @param topicName
     * @return Properties
     */
    public Properties fetchEntityConfig(String topicName) {
        return AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(), topicName);
    }

    /**
     * 获取所有topic的配置信息
     *
     * @return Map<String, Properties>
     */
    public Map<String, Properties> fetchAllTopicConfigs() {
        return AdminUtils.fetchAllTopicConfigs(zkUtils);
    }

}
