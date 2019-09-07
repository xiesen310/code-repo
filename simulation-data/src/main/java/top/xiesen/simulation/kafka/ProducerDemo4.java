package top.xiesen.simulation.kafka;

import kafka.producer.KeyedMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jettison.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * todo
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/5/29 19:31.
 */
public class ProducerDemo4 {

    private KafkaProducer<String, String> producer;
    private Properties properties;
    private static Random r = new Random();

    public ProducerDemo4() {
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

    public static String getNow() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String sendMessage() {
        Random r = new Random();
        int id = r.nextInt(10000000);
        int memberid = r.nextInt(100000);
        int totalprice = r.nextInt(1000) + 100;
        int youhui = r.nextInt(100);
        int sendpay = r.nextInt(3);

        StringBuffer data = new StringBuffer();
        data.append(String.valueOf(id))
                .append("\t")
                .append(String.valueOf(memberid))
                .append("\t")
                .append(String.valueOf(totalprice))
                .append("\t")
                .append(String.valueOf(youhui))
                .append("\t")
                .append(String.valueOf(sendpay))
                .append("\t")
                .append(getNow());
        return data.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerDemo4 client = new ProducerDemo4();
        while (true) {
            System.out.println(sendMessage());
            client.sendRecorder("test","key",sendMessage());
            Thread.sleep(1000);
        }
    }
}
