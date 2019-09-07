package top.xiesen.simulation.flinksql;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import top.xiesen.simulation.flinksql.entity.JsonRootBean;
import top.xiesen.simulation.flinksql.entity._id;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

/**
 * @Description TODO
 * @Author 谢森
 * @Date 2019/8/8 18:34
 */
public class JtBackTest {
    private KafkaProducer<String, String> producer;
    private Properties properties;

    public JtBackTest() {
        properties = new Properties();
        properties.put("bootstrap.servers", "xiesen310:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
    }

    public void sendRecorder(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
    }

    public static String message() {
        JsonRootBean jsonRootBean = new JsonRootBean();
        _id id = new _id();
        id.set$oid(UUID.randomUUID().toString());
        jsonRootBean.set_id(id);
        jsonRootBean.setProbeIpStr("10.182.21.49");
        jsonRootBean.setLinkLabel(1970);
        return JSONObject.toJSON(jsonRootBean).toString();
    }
    public static void main(String[] args) {
        JtBackTest jtBackTest = new JtBackTest();
        System.out.println(jtBackTest.message());
    }
}
