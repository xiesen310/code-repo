package top.xiesen.mock.kafka.mock;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: 谢森
 * @Email xiesen@zork.com.cn
 * @time: 2020/1/17 0017 10:57
 */
public class MockStreamxJson {
    static class DemoProducerCallback implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {//如果Kafka返回一个错误，onCompletion方法抛出一个non null异常。
                e.printStackTrace();//对异常进行一些处理，这里只是简单打印出来
            }
        }
    }
    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "zorkdata-91:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);

        ProducerRecord record = new ProducerRecord<>("test3", "aa", "aaaaaaaa");//Topic Key Value
        producer.send(record, new DemoProducerCallback());


    }
}
