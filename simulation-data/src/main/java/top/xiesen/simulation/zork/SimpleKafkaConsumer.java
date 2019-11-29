package top.xiesen.simulation.zork;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import top.xiesen.simulation.zork.avro.AvroDeserializer;
import top.xiesen.simulation.zork.avro.AvroDeserializerFactory;
import top.xiesen.simulation.zork.pojo.ZorkData;

import java.util.Arrays;
import java.util.Properties;

public class SimpleKafkaConsumer {
    private static KafkaConsumer<String, byte[]> consumer;
    private final static String TOPIC = "xiesen1";

    public SimpleKafkaConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "zorkdata-91:9092");
        //每个消费者分配独立的组号
        props.put("group.id", "test115");
        //如果value合法，则自动提交偏移量
//        props.put("enable.auto.commit", "true");
        //设置多久一次更新被消费消息的偏移量
//        props.put("auto.commit.interval.ms", "1000");
        //设置会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
//        props.put("session.timeout.ms", "30000");
        //自动重置offset
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        consumer = new KafkaConsumer<String, byte[]>(props);
    }

    public void consume() {
        consumer.subscribe(Arrays.asList(TOPIC));
        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(1000);

            for (ConsumerRecord<String, byte[]> record : records) {
                byte[] value = record.value();
                AvroDeserializer logsDeserializer = AvroDeserializerFactory.getLogsDeserializer();
                GenericRecord myavroevent = logsDeserializer.deserializing(value);
                String s = new String(value);
                if (null != myavroevent){
                    ZorkData zorkData = (ZorkData) myavroevent;
                    System.out.println(zorkData.getLogTypeName());
                }

                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new SimpleKafkaConsumer().consume();
    }
}
