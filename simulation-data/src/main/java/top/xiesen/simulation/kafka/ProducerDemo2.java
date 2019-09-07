package top.xiesen.simulation.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jettison.json.JSONObject;

import java.util.Properties;
import java.util.Random;

/**
 * {"name":"Jack", "idName":1, "tel":"18211112222"}
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/5/29 19:31.
 */
public class ProducerDemo2 {

    private KafkaProducer<String, String> producer;
    private Properties properties;

    public ProducerDemo2() {
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

    // {"name":"Jack", "idName":1, "tel":"18211112222"}
    public static String message() {
        JSONObject event = new JSONObject();
        JSONObject counter = new JSONObject();

        Random random = new Random();
        String[] names = {"Allen", "Jack", "Tom", "Tomas", "Zhangsan", "Lisi", "Wangwu"};
        String[] tels = {"18611234321", "18711234321", "15111234321", "13611234321", "15611234321", "17611234321"};
        String[] orderNames = {"apple", "orange", "banana", "pig", "sala"};
        try {
            /*counter.put("orderId", random.nextInt(10))
                    .put("orderName", orderNames[random.nextInt(orderNames.length)]);*/

            event.put("name", names[random.nextInt(names.length)])
                    .put("idName", 10)
//                    .put("counter",counter)
                    .put("tel", tels[random.nextInt(tels.length)]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event.toString();
    }

    public static void main(String[] args) {
        ProducerDemo2 client = new ProducerDemo2();
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
