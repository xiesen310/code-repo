package top.xiesen.simulation.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import top.xiesen.simulation.fk.utils.KafkaUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * 网管系统日志
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/6/2 10:35.
 */
public class SysLog {

    private KafkaProducer<String, String> producer;
    private Properties properties;

    public SysLog() {
        properties = new Properties();
        properties.put("bootstrap.servers", "zorkdata-154:9092,zorkdata-156:9092");
//        properties.put("bootstrap.servers", "zorkdata-1:9092,zorkdata-2:9092,zorkdata-3:9092");
//        properties.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(properties);
    }

    /**
     * kafka发送消息
     *
     * @param topic
     * @param key
     * @param value
     */
    public void sendRecorder(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
    }

    /**
     * 成交表数据流消息体
     *
     * @return
     * @throws JSONException
     */
    public String message() throws JSONException {
        JSONObject event = new JSONObject();
        JSONObject _source = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject normalFields = new JSONObject();
        JSONObject dimensions = new JSONObject();
        JSONObject measures = new JSONObject();
        JSONArray indexTime = new JSONArray();
        JSONArray normalFields_logchecktime = new JSONArray();
        JSONArray timestamp = new JSONArray();
        JSONArray sort = new JSONArray();

        long timeMillis = System.currentTimeMillis();
        Random random = new Random();

        normalFields.put("message", "operational Transmit Flow Control state changed to on")
                .put("eventtype1", "ASA")
                .put("eventtype2", "302020")
                .put("Time", "Jun 14 2018 14:20:31")
                .put("logchecktime", "2018-06-14T14:20:31.747+08:00")
                .put("facility", "0")
                .put("priority", "0")
                .put("flat", "166")
                .put("IP", "DC-CBS-FW1")
                .put("Severity_log", "6")
                .put("severity", "0")
                .put("facility_label", "kernel")
                .put("severity_label", "Emergency");

        dimensions.put("logdate", "20180614")
                .put("appprogramname", "syslog")
                .put("appsystem", "linux_system")
                .put("hostname", "192.168.1.9" + random.nextInt(9));

        indexTime.put(timeMillis);
        normalFields_logchecktime.put(timeMillis);
        timestamp.put(timeMillis);
        sort.put(timeMillis);

        _source.put("timestamp", "2018-06-14T14:20:31.739+08:00")
                .put("indexTime", "2018-06-14T14:20:31.864+08:00")
                .put("source", "")
                .put("normalFields", normalFields)
                .put("dimensions", dimensions)
                .put("logTypeName", "linux_syslog")
                .put("measures", measures)
                .put("offset", "0");
        fields.put("indexTime", indexTime)
                .put("normalFields.logchecktime", normalFields_logchecktime)
                .put("timestamp", timestamp)
                .put("sort", sort);

        event.put("_index", "syslog_" + new SimpleDateFormat("yyyy.MM.dd").format(new Date()))
                .put("_type", "linux_syslog")
                .put("_version", 1)
                .put("_score", "null")
                .put("_source", _source)
                .put("fields", fields);

        return event.toString();
    }

    public static void main(String[] args) throws JSONException, InterruptedException {
        SysLog ms = new SysLog();
        while (true) {
//            System.out.println(ms.message());
            ms.sendRecorder("syslog", "key", ms.message());
            Thread.sleep(1000);
        }
    }

}
