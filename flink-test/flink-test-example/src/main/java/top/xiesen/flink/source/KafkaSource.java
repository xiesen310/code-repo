package top.xiesen.flink.source;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.xiesen.flink.constants.KafkaConstant;
import top.xiesen.flink.util.ConfigUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @description:
 * @author: 谢森
 * @Email xiesen@zork.com.cn
 * @time: 2020/1/9 0009 15:25
 */
public class KafkaSource {
    private static final Logger logger = LoggerFactory.getLogger(KafkaSource.class);

    public static FlinkKafkaConsumer011 getKafkaConsumer(Map conf) {
        return getKafkaBaseConsumer(conf, new SimpleStringSchema());
    }

    public static FlinkKafkaConsumer011 getKafkaBaseConsumer(Map conf, DeserializationSchema deserializer) {
        // 判断 conf 中是否包含 kafka 需要的配置信息
        if (!isExist(conf)) {
            logger.error("配置文件有误，请检查 {},{},{},{}是否有误"
                    , KafkaConstant.SOURCE_KAFKA_BOOTSTRAP_SERVER
                    , KafkaConstant.SOURCE_KAFKA_TOPICS
                    , KafkaConstant.SOURCE_KAFKA_GROUP_ID);
            System.exit(-1);
        }

        String sourceBootstrapServers = String.valueOf(conf.get(KafkaConstant.SOURCE_KAFKA_BOOTSTRAP_SERVER)).trim();
        String inputTopic = String.valueOf(conf.get(KafkaConstant.SOURCE_KAFKA_TOPICS)).trim();
        String sourceGroupId = ConfigUtil.getString(String.valueOf(conf.get(KafkaConstant.SOURCE_KAFKA_GROUP_ID)).trim(),
                KafkaConstant.DEFAULT_CONSUMER_GROUP_ID);

        Properties sourceProperties = new Properties();
        sourceProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, sourceBootstrapServers);
        sourceProperties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, sourceGroupId);
        sourceProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        sourceProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        List<String> inputTopics = Arrays.asList(inputTopic.split(","));
        return new FlinkKafkaConsumer011<String>(inputTopic, deserializer, sourceProperties);
    }


    /**
     * 获取 topic 列表
     *
     * @param conf 配置文件
     * @return List<String>
     */
    private static List<String> getTopics(Map conf) {
        String inputTopic = String.valueOf(conf.get(KafkaConstant.SOURCE_KAFKA_TOPICS)).trim();
        List<String> inputTopics = Arrays.asList(inputTopic.split(","));
        return inputTopics;
    }

    /**
     * 判断 参数是否存在
     *
     * @param conf
     * @return
     */
    private static boolean isExist(Map conf) {
        boolean b = conf.containsKey(KafkaConstant.SOURCE_KAFKA_BOOTSTRAP_SERVER)
                && conf.containsKey(KafkaConstant.SOURCE_KAFKA_TOPICS)
                && conf.containsKey(KafkaConstant.SOURCE_KAFKA_GROUP_ID);
        return b;
    }
}
