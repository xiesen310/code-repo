package top.xiesen.simulation.flinksql;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class KafkaFlinkSqlProducer {

    private KafkaProducer<String, String> producer;
    private Properties properties;

    public KafkaFlinkSqlProducer() {
        properties = new Properties();
        properties.put("bootstrap.servers", "zorkdata-91:9093");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
    }

    public void sendRecorder(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
    }

    public static String message() {
        Random random = new Random();
        KafkaThslog kafkaThslog = new KafkaThslog();
        kafkaThslog.setLogtypename("ths");
        kafkaThslog.setClientid(random.nextInt(1000)+ "");
        kafkaThslog.setFunctionid(random.nextInt(100) + "");
        kafkaThslog.setHostname("ths1");
        kafkaThslog.setIp("192.168.1.1");
        kafkaThslog.setLatency(random.nextInt(100));
        kafkaThslog.setPhone("13838381438");
        kafkaThslog.setTimestamps(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        kafkaThslog.setUrl("http://ths.com/8080");

        return JSONObject.toJSON(kafkaThslog).toString();
    }

    public static void main(String[] args) {
        KafkaFlinkSqlProducer client = new KafkaFlinkSqlProducer();
        try {
            while (true) {
                System.out.println("Message : " + message());
                client.sendRecorder("test", "key", message());
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
