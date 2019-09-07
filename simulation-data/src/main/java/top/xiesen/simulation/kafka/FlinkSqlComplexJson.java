package top.xiesen.simulation.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * todo
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/5/29 19:31.
 */
public class FlinkSqlComplexJson {

    private KafkaProducer<String, String> producer;
    private Properties properties;
    private static Random r = new Random();

    public FlinkSqlComplexJson() {
        properties = new Properties();
        properties.put("bootstrap.servers", "flink:9092");
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

    public static String sendMessage() throws JSONException {
        JSONObject properties = new JSONObject();
        JSONObject event = new JSONObject();

        JSONObject smallProperties = new JSONObject();
        JSONObject innerData = new JSONObject();

        innerData.put("d1","d1");

        properties.put("innerData",innerData);
        properties.put("user_id",UUID.randomUUID());
        properties.put("name","allen");
        properties.put("tel","18503816210");
        properties.put("mac","2001:0:53aa:64c:24da:34a1:3f57:e1e4");

        event.put("data",properties);
        event.put("domain","domain");
        return event.toString();
    }

    public static void main(String[] args) throws Exception {
        FlinkSqlComplexJson client = new FlinkSqlComplexJson();
        while (true) {
            System.out.println(sendMessage());
            client.sendRecorder("test","key",sendMessage());
            Thread.sleep(1000);
        }
    }
}
