package top.xiesen.simulation.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

/**
 * IP2HostName
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/5/29 19:31.
 */
public class IP2HostName {

    private KafkaProducer<String, String> producer;
    private Properties properties;

    public IP2HostName() {
        properties = new Properties();
        properties.put("bootstrap.servers", "bigdata01:9092,bigdata02:9092,bigdata05:9092");
//        properties.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
    }

    /**
     * 1c59d79f-87df-4d28-8f42-91f1cc0bd96c 192.168.1.92 1528812290327
     * id ip timestamp
     *
     * @param topic
     * @param key
     * @param value
     */
    public void sendRecorder(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
    }

    public static String message() {
        Random random = new Random();
        String ips[] = {"192.168.1.91", "192.168.1.92", "192.168.1.93"};
        String event = UUID.randomUUID().toString() + " " + ips[random.nextInt(ips.length)] + " " + System.currentTimeMillis();
        return event;
    }

    /**
     * {
     * "@timestamp": "2018 - 11 - 06 T13: 10: 42.142 Z",
     * "offset": 302485,
     * "host": "zorkdata-151",
     * "source": "/var/log/messages",
     * "message": "2018-11-06T13--start system success"
     * }
     *
     * @return
     */
    public static String message1() {
        Random random = new Random();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // 封装json字符串
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("@timestamp", currentTime);
        jsonObject.put("offset", random.nextInt(1000000));
        jsonObject.put("host", "zorkdata-" + random.nextInt(100));
        jsonObject.put("source", "/var/log/messages");
        jsonObject.put("message", currentTime + "--start system success");
        String jsonString = jsonObject.toString();

        String ips[] = {"192.168.1.91", "192.168.1.92", "192.168.1.93"};
        String event = ips[random.nextInt(ips.length)] + " " + jsonString;
        return event;
    }

    public static void main(String[] args) {

        IP2HostName client = new IP2HostName();
        try {
            while (true) {
                System.out.println("Message : " + message1());
                client.sendRecorder("test", "key", message1());
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
