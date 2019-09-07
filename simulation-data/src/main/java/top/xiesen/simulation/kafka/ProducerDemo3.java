package top.xiesen.simulation.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jettison.json.JSONObject;

import java.util.Properties;
import java.util.Random;

/**
 * todo
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/5/29 19:31.
 */
public class ProducerDemo3 {

    private KafkaProducer<String, String> producer;
    private Properties properties;

    public ProducerDemo3() {
        properties = new Properties();
        properties.put("bootstrap.servers", "zorkdata-68:9092,zorkdata-170:9092,zorkdata-biz:9092");
//        properties.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
    }

    public void sendRecorder(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
    }

    // {"sex":"boy", "idSex":1}
    public static String message() {
        JSONObject event = new JSONObject();
        Random random = new Random();
        String[] sexs = {"boy", "girl"};
        try {
            event.put("sex", sexs[random.nextInt(sexs.length)])
                    .put("idSex", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event.toString();
    }

    public static void main(String[] args) {
        ProducerDemo3 client = new ProducerDemo3();
        try {
            while (true) {
                System.out.println("Message : " + message());
                client.sendRecorder("ycjy", "key", message());
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
