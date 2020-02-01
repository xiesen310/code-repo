package top.xiesen.simulation.zork;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class FlinkLogMerge {
    private static final Logger LOG = LoggerFactory.getLogger(FlinkLogMerge.class);
    private static KafkaProducer<String, String> producer = null;
    private static final int MSG_SIZE = 20;
    private static final String TOPIC = "test";
    private static final String BROKER_LIST = "zork-poc103:9092";

    private static Properties initConfig() {
        Properties properties = new Properties();

        // kakfa broker 列表
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);

        // 设置序列化类
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }

    static {
        // 1. 构造用于实例化Kaf kaProducer 的Properties 信息
        Properties configs = initConfig();
        // 2. 初始化一个 KafkaProducer
        producer = new KafkaProducer<String, String>(configs);
    }


    public static void send() {
        ProducerRecord<String, String> record = null;
        String msg = null;

        try {
            int num = 0;
            for (int i = 0; i < MSG_SIZE; i++) {
                msg = "hello world";
                record = new ProducerRecord<>(TOPIC, "test", msg);
                producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (null != e) {
                            LOG.error("Send message occurs exception: " + e);
                        }
                        if (null != recordMetadata) {
                            LOG.info(String.format("offset:%s,partition:%s", recordMetadata.offset(), recordMetadata.partition()));
                        }
                    }
                });  // 异步发送消息


                if (num++ % 2 == 0) {
                    Thread.sleep(2000L); // 休眠2s
                }
            }
        } catch (InterruptedException e) {
            LOG.error("Send message occurs exception: ", e);
        } finally {
            producer.close();
        }
    }

    public static void main(String[] args) {
        send();
    }
}
