package top.xiesen.verify.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import top.xiesen.verify.config.RockConfig;
import top.xiesen.verify.constants.RockConstant;
import top.xiesen.verify.utils.PropertiesUtil;
import top.xiesen.verify.utils.StringUtils;

import java.util.Properties;

/**
 * @author 谢森
 */
public class Producer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
    static Producer producer;
    static String servers = "s103:9092";
    static int batchSize = 1;
    static String topic = "test";

    private static org.apache.kafka.clients.producer.KafkaProducer<String, byte[]> avroProducer;
    private static org.apache.kafka.clients.producer.KafkaProducer<String, String> noAvroProducer;

    public static synchronized Producer getInstance() {
        if (producer == null) {
            producer = new Producer();
        }
        return producer;
    }

    public Producer() {
        try {
            Properties props = new Properties();

            props.put("bootstrap.servers", RockConfig.getConfig().getProperty(RockConstant.ROCK_VERIFY_KAFKA_SERVERS));
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
            props.put("batch.size", StringUtils.getInt(RockConfig.getConfig().getProperty(RockConstant.ROCK_VERIFY_KAFKA_BATCH_SIZE), 100));
            avroProducer = new KafkaProducer<String, byte[]>(props);

            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            noAvroProducer = new KafkaProducer<String, String>(props);

        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error("初始化Kafka失败,系统自动退出! 异常信息是: {}", ex.getMessage());
            System.exit(1);
        }
    }


    /**
     * 发送数据
     *
     * @param logJson
     */
    public void sendLog(String logJson) {
        try {
            topic = RockConfig.getConfig().getProperty(RockConstant.ROCK_VERIFY_KAFKA_TOPIC);
            noAvroProducer.send(new ProducerRecord<String, String>(topic, null, logJson));
        } catch (Exception e) {
            LOGGER.error("发送数据到 {} 失败,错误信息是: {}", topic, e.getMessage());
        }
    }

}
