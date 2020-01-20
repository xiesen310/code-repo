package top.xiesen.mock.kafka.mock;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import top.xiesen.mock.kafka.avro.AvroSerializer;
import top.xiesen.mock.kafka.avro.AvroSerializerFactory;
import top.xiesen.mock.kafka.utils.DateUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @description:
 * @author: 谢森
 * @Email xiesen@zork.com.cn
 * @time: 2020/1/17 0017 10:57
 */
public class MockKafkaConnect {
    private static String topic = "kafka-connect-sources";
    private static String brokerAddr = "zorkdata-91:9092";
    private static ProducerRecord<String, byte[]> producerRecord = null;
    private static KafkaProducer<String, byte[]> producer = null;

    public static void init() {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerAddr);
        props.put("acks", "1");
        props.put("retries", 0);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", ByteArraySerializer.class.getName());
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        producer = new KafkaProducer<String, byte[]>(props);
    }

    public static byte[] buildKafkaConnect() {

        String logTypeName = "tc50_biz_filebeat_req";
        String timestamp = DateUtil.getUTCTimeStr();
        String source = "/opt/20191231.log";
        String offset = String.valueOf(6322587L);
        Map<String, String> dimensions = new HashMap<>();
        dimensions.put("hostname", "localhost");
        dimensions.put("appprogramname", "tc50");
        dimensions.put("appsystem", "TXJY");
        Map<String, Double> measures = new HashMap<>();
        measures.put("latence", 301.0);

        Map<String, String> normalFields = new HashMap<>();
        normalFields.put("message", "成功处理");

//        ZorkData data = new ZorkData(logTypeName, source, offset, timestamp, measures, normalFields, dimensions);
//        System.out.println(JSON.toJSONString(data));
        AvroSerializer avroSerializer = AvroSerializerFactory.getLogAvorSerializer();
        byte[] bytes = avroSerializer.serializingLog(logTypeName, timestamp, source, offset, dimensions, measures, normalFields);
        return bytes;
    }

    public static void send(String topic) throws ExecutionException, InterruptedException {
        init();
        byte[] req = buildKafkaConnect();
        producerRecord = new ProducerRecord<String, byte[]>(
                topic,
                null,
                req
        );
        producer.send(producerRecord);
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int size = 1;
        if (2 == args.length) {
            size = Integer.valueOf(args[0]);
            topic = args[1];
        }
        System.out.println(topic);
        long start = System.currentTimeMillis();
        for (int j = 0; j <= size; j++) {
            for (int i = 0; i <= 1000; i++) {
                send(topic);
            }
//            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("写入数据 " + (size * 1000) + " 条");
        System.out.println("耗时: " + (end - start) + "ms");
    }
}
