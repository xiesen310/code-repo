package top.xiesen.simulation.zork;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Logger;
import top.xiesen.simulation.zork.avro.AvroSerializer;
import top.xiesen.simulation.zork.avro.AvroSerializerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * zork 单线程生产者
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2019/2/24 15:32.
 */
public class ZorkAvroProducer {

    private static final Logger LOG = Logger.getLogger(ZorkAvroProducer.class);

    /**
     * 设置实例生产消息的总数
     */
    private static final int MSG_SIZE = 10;

    /**
     * 主题名称
     */
    private static final String TOPIC = "test";

    /**
     * kafka 集群
     */
    private static final String BROKER_LIST = "zorkdata-91:9092,zorkdata-92:9092,zorkdata-95:9092";
//    private static final String BROKER_LIST = "zorkdata-154:9092,zorkdata-150:9092,zorkdata-156:9092";

    /**
     * 线程数
     */
    private static final int HREADS_NUM = 2;


    private static KafkaProducer<String, byte[]> producer = null;

    static {
        // 1. 构造用于实例化Kaf kaProducer 的Properties 信息
        Properties configs = initConfig();
        // 2. 初始化一个 KafkaProducer
        producer = new KafkaProducer<String, byte[]>(configs);
    }

    /**
     * 初始化 Kafka 配置
     *
     * @return
     */
    private static Properties initConfig() {
        Properties properties = new Properties();

        // kakfa broker 列表
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);

        // 设置序列化类
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());

        return properties;
    }


    private static byte[] zorkDara2Avro() {
        Map<String, Object> event = new HashMap<String, Object>();
        String logTypeName = "log";
        // 统一系统时间为UTC时间
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());

        String source = "/var/log/elasticseach/es.log";
        String offset = "122";
        Map<String, String> dimensions = new HashMap<String, String>();
        dimensions.put("hostname", "localhost");
        dimensions.put("appsystem", "tdx");
        dimensions.put("appprogramname", "tdx");
        dimensions.put("ip", "192.168.1.1");

        Map<String, Double> measures = new HashMap<String, Double>();
        measures.put("lantern", 0.5);
        Random random = new Random();
        String status[] = {"req", "resp"};


        Map<String, String> normalFields = new HashMap<String, String>();
        normalFields.put("message", timestamp + " this is message...");
        normalFields.put("logtime", timestamp);
        normalFields.put("status", status[random.nextInt(status.length)]);
        normalFields.put("id", String.valueOf(random.nextInt(100)));


        event.put("logTypeName", logTypeName);
        event.put("timestamp", timestamp);
        event.put("source", source);
        event.put("offset", offset);
        event.put("dimensions", dimensions);
        event.put("measures", measures);
        event.put("normalFields", normalFields);
        System.out.println(JSON.toJSONString(event));
        AvroSerializer avroSerializer = AvroSerializerFactory.getLogAvorSerializer();
        return avroSerializer.serializingLog(logTypeName, timestamp, source, offset, dimensions, measures, normalFields);
    }


    /**
     * 单线程生产者
     */
    public static void test1() {
        ProducerRecord<String, byte[]> record = null;
        byte[] msg = null;

        try {
            int num = 0;
            for (int i = 0; i < MSG_SIZE; i++) {
                msg = zorkDara2Avro();
                record = new ProducerRecord<>(TOPIC, "zorklog", msg);
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

            }
        } finally {
            producer.close();
        }
    }


    @SuppressWarnings("all")
    public static void test2() {
        ProducerRecord<String, byte[]> record = null;
        byte[] msg = null;
        try {
            while (true) {
                msg = zorkDara2Avro();
                record = new ProducerRecord<>("avro-log", "zorklog", msg);
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
                Thread.sleep(1 * 1000);
            }
        } catch (Exception e) {
            LOG.error("连接失败");
        } finally {
            producer.close();
        }

    }

    public static void main(String[] args) {
//        test1();
        test2();
    }
}
