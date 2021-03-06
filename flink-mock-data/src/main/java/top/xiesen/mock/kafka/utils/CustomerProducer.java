package top.xiesen.mock.kafka.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import top.xiesen.mock.kafka.avro.AvroSerializerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @Description
 * @className top.xiesen.mock.kafka.utils.CustomerProducer
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/4/2 9:39
 */
@Slf4j
public class CustomerProducer {
    static String servers = "kafka-1:9092,kafka-2:9092,kafka-3:9092";
    static int batchSize = 1;
    static CustomerProducer testProducer;
    static String topics;
    public static long logSize;

    private static KafkaProducer<String, byte[]> producer;
    private static KafkaProducer<String, String> noAvroProducer;

    public static synchronized CustomerProducer getInstance(String propertiesName) {
        if (testProducer == null) {
            testProducer = new CustomerProducer(propertiesName);
        }
        return testProducer;
    }

    public CustomerProducer(String propertiesName) {
        try {
            initConfig(propertiesName);
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");

            /**
             * 这个参数控制着相同分区内数据发送的批次个数大小，也就是当数据达到 这个size 时，进行数据发送,
             * 但是并不是数据达不到 size 的值，就不会发送数据，默认是 1048576，即 16k
             */
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);

            // 当数据发送失败时，重试次数设置
            props.put(ProducerConfig.RETRIES_CONFIG, 5);

            /**
             * 消息是否发送，不是仅仅通过 batch.size 的值来控制的，实际上是一种权衡策略，即吞吐量和延时之间的权衡
             * linger.ms 参数就是控制消息发送延时行为的，默认是 0，表示消息需要被立即发送。
             */
            props.put(ProducerConfig.LINGER_MS_CONFIG, 100);

            /**
             * 控制消息发送的最大消息大小，默认是 10485760 字节 即 10Mb
             */
            props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 10485760);

            /**
             * 当 producer 发送消息到 broker 时，broker 需要在规定的时间内返回结果，这个时间就是该参数控制的，默认是 30s
             */
            props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 60000);

            /**
             * 指定了producer 端用于缓存的缓存区大小，单位是字节，默认是 33554432, 即 32G
             */
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
            /**
             * 用户控制 生产者的持久性 acks 有3个值，
             *  0: 表示producer 完全不理睬 broker 的处理结果
             *  all： 表示发送数据时，broker 不仅会将消息写入到本地磁盘，同时也要保证其他副本也写入完成，才返回结果
             *  1: 表示发送数据时，broker 接收到消息写入到本地磁盘即可，无需保证其他副本是否写入成功
             */
            props.put(ProducerConfig.ACKS_CONFIG, "1");
            producer = new KafkaProducer<String, byte[]>(props);

            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            noAvroProducer = new KafkaProducer<String, String>(props);

        } catch (Exception ex) {
            log.error("初始化Kafka失败,系统自动退出! ", ex);
            System.exit(1);
        }
    }

    public void initConfig(String propertiesName) throws Exception {
        Properties properties = PropertiesUtil.getProperties(propertiesName);
        topics = properties.getProperty("log.topic");
        servers = properties.getProperty("kafka.servers", "zorkdata-151:9092").trim();
        batchSize = StringUtil.getInt(properties.getProperty("kafka.batch.size", "5000").trim(), 1);
        logSize = StringUtil.getLong(properties.getProperty("log.size", "5000").trim(), 1);
    }

    public void sendLog(String logTypeName, String timestamp, String source, String offset,
                        Map<String, String> dimensions, Map<String, Double> metrics, Map<String, String> normalFields) throws ExecutionException, InterruptedException {
        try {
            long l1 = System.currentTimeMillis();
            byte[] bytes = AvroSerializerFactory.getLogAvorSerializer().serializingLog(logTypeName, timestamp, source,
                    offset, dimensions, metrics, normalFields);
            long l2 = System.currentTimeMillis();
//            System.out.println("数据序列化需要的时间: " + (l2 - l1) + "ms");
            producer.send(new ProducerRecord<String, byte[]>(topics, null, bytes));
            long l3 = System.currentTimeMillis();
//            System.out.println("执行 producer.send 方法所需要的时间: " + (l3 - l2) + "ms");
        } catch (Exception e) {
            log.error("sendLog-插入Kafka失败", e);
        }
    }


    public void sendJsonLog(String logJson) {
        try {
            noAvroProducer.send(new ProducerRecord<String, String>(topics, null, logJson));
        } catch (Exception e) {
            log.error("send json Log-插入Kafka失败", e);
        }
    }

}
