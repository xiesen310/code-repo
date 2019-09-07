package top.xiesen.simulation.performanceTest;

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
 * @Date 2018/7/31 11:18.
 */
public class PTProducer {
    private KafkaProducer<String, String> producer;
    private Properties properties;

    public PTProducer() {
        properties = new Properties();
        properties.put("bootstrap.servers", "zork-jstorm01:9092,zork-jstorm02:9092,zork-jstorm03:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
    }

    public void sendRecorder(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
    }

    /**
     * 模拟数据
     *
     * @return
     */
    public static String message() {
        JSONObject event = new JSONObject();

        Random random = new Random();
        String[] names = {"Allen", "Jack", "Tom", "Tomas", "Zhangsan", "Lisi", "Wangwu"};
        String[] sexs = {"man", "woman"};
        try {

            event.put("name", names[random.nextInt(names.length)])
                    .put("age", random.nextInt(70) + 10)
                    .put("sex", sexs[random.nextInt(sexs.length)])
                    .put("test", "test");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event.toString();
    }

    public static void main(String[] args) {
        PTProducer client = new PTProducer();
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
