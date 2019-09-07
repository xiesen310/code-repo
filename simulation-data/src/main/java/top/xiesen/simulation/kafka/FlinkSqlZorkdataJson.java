package top.xiesen.simulation.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jettison.json.JSONException;
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
public class FlinkSqlZorkdataJson {

    private KafkaProducer<String, String> producer;
    private Properties properties;
    private static Random r = new Random();

    public FlinkSqlZorkdataJson() {
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
        Random random = new Random();
        JSONObject dimensions = new JSONObject();
        JSONObject normalFields = new JSONObject();
        JSONObject event = new JSONObject();

        dimensions.put("hostname","localhost");
        dimensions.put("ip","192.168.1.1");
        dimensions.put("appsystem","test");

        normalFields.put("message",getNow() + " icube system start success...");
        normalFields.put("logTime",getNow());

        event.put("source","/var/log/logstash-plain.log");
        event.put("timestamps",getNow());
        event.put("logTypeName","zork");
        event.put("dimensions",dimensions);
        event.put("normalFields",normalFields);

        return event.toString();
    }

    public static void main(String[] args) throws Exception {
        FlinkSqlZorkdataJson client = new FlinkSqlZorkdataJson();
        while (true) {
            System.out.println(sendMessage());
            client.sendRecorder("test","key",sendMessage());
            Thread.sleep(1000);
        }
    }
}
