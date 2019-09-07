package top.xiesen.schema;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

/**
 * avro消费者
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2019/3/7 14:05.
 */
@SuppressWarnings("all")
public class AvroConsumer {
    private static final String BOOTSTRAP_SERVER = "LOCALHOST:9092";

    private Properties initConfig(){
        Properties config = new Properties();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVER);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"avro-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvorDeserializer.class.getName());
        return config;
    }
}
