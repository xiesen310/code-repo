package top.xiesen.simulation.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jettison.json.JSONObject;
import top.xiesen.utils.TimeConvertUtils;

import java.util.Properties;
import java.util.Random;

/**
 * 模拟生产环境上新版的数据格式
 * {
 * 	"appsystem": "ydkh",
 * 	"appprogram": "tomcat",
 * 	"logtypename": "ydkhlog",
 * 	"hostname": "localhost",
 * 	"collectime": "2019-07-03T10:13:06.808Z",
 * 	"transtime": "2019-07-03T10:13:06.808Z",
 * 	"logdate": "20190703",
 * 	"source": "\/var\/log\/ydkh.log",
 * 	"offset": "9943",
 * 	"message": "icube system start...",
 * 	"logtimeflag": "true",
 * 	"ip": "127.0.0.1"
 * }
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/5/29 19:31.
 */
public class ProducerDfzqDemo {

    private KafkaProducer<String, String> producer;
    private Properties properties;

    public ProducerDfzqDemo() {
        properties = new Properties();
        properties.put("bootstrap.servers", "zorkdata-91:9092,zorkdata-92:9092,zorkdata-93:9092");
//        properties.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
    }

    public void sendRecorder(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
    }

    public static String message() {
        JSONObject event = new JSONObject();
        Random random = new Random();
        String appsystem = "ydkh";
        String appprogram = "tomcat";
        String logtypename = "ydkhlog";
        String hostname = "localhost";
        String collectime = TimeConvertUtils.getUTC();
        String transtime = TimeConvertUtils.getUTC();
        String logdate = TimeConvertUtils.getDate();
        String source = "/var/log/ydkh.log";
        String offset = String.valueOf(random.nextInt(10000));
        String message = "icube system start...";
        String logtimeflag = "true";
        String ip = "127.0.0.1";
        try {
            event.put("appsystem", appsystem)
                    .put("appprogram", appprogram)
                    .put("logtypename", logtypename)
                    .put("hostname", hostname)
                    .put("collectime", collectime)
                    .put("transtime", transtime)
                    .put("logdate", logdate)
                    .put("source", source)
                    .put("offset", offset)
                    .put("message", message)
                    .put("logtimeflag", logtimeflag)
                    .put("ip", ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event.toString();
    }

    public static void main(String[] args) {
        ProducerDfzqDemo client = new ProducerDfzqDemo();
        try {
            while (true) {
                System.out.println("Message : " + message());
                client.sendRecorder("dfzq-test", "key", message());
                Thread.sleep(10);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
