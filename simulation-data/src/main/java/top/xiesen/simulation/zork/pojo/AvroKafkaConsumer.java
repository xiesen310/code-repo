package top.xiesen.simulation.zork.pojo;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import top.xiesen.simulation.zork.avro.AvroDeserializer;
import top.xiesen.simulation.zork.avro.AvroDeserializerFactory;
import top.xiesen.simulation.zork.avro.LogAvroMacroDef;

import java.util.Collections;
import java.util.Properties;

public class AvroKafkaConsumer {
    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers","zorkdata222:9092");

        kafkaProps.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("value.deserializer","org.apache.kafka.common.serialization.ByteArrayDeserializer");

        kafkaProps.put("group.id","DemoAvroKafkaConsumer");

        kafkaProps.put("auto.offset.reset","earliest");

        KafkaConsumer<String ,byte[]> consumer = new KafkaConsumer<String, byte[]>(kafkaProps);

        consumer.subscribe(Collections.singletonList("test"));

        Schema.Parser parser = new Schema.Parser();

        Schema schema = parser.parse(LogAvroMacroDef.metadata);

        while (true) {
            ConsumerRecords<String, byte[]> records  = consumer.poll(1000L);
            for (ConsumerRecord<String,byte[]> record : records) {
                AvroDeserializer logsDeserializer = AvroDeserializerFactory.getLogsDeserializer();
                GenericRecord genericRecord = logsDeserializer.deserializing(record.value());
                System.out.println(genericRecord);
//                System.out.println(record);
            }
        }
    }
}
