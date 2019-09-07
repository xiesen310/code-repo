package top.xiesen.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import top.xiesen.simulation.flinksql.KafkaFlinkSqlProducer;
import top.xiesen.simulation.flinksql.KafkaThslog;
import top.xiesen.test.pojo.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * topic：test
 * 消息体：json格式
 * <p>
 * {
 * "userId": 0,
 * "timestamp": 1567824267485,
 * "funcId": 0
 * }
 * <p>
 * userId 需要完整的模拟 1~100000 不随机
 * <p>
 * funcId 需要完整模拟 1~100000 随机
 */
public class FlinkStreamSqlMock {
    private KafkaProducer<String, String> producer;
    private Properties properties;

    public FlinkStreamSqlMock() {
        properties = new Properties();
        properties.put("bootstrap.servers", "zorkdata224:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
    }

    public void sendRecorder(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
    }


    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        for (int i = 0; i < 1000000; i++) {
            User user = new User();
            int randint =(int)Math.floor((random.nextDouble()*100000.0)) + 1; // 1 - 100000
            user.setUserId(i);
            user.setTimestamps(String.valueOf(System.currentTimeMillis()));
            user.setFuncId(randint);
            String msg = JSONObject.toJSON(user).toString();
//            System.out.println(msg);
            FlinkStreamSqlMock client = new FlinkStreamSqlMock();
            client.sendRecorder("xiesen2", "", msg);
//            Thread.sleep(20);
        }

    }
}
