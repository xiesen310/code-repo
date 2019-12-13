package top.xiesen.simulation.zork;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author 谢森
 * @Description Avro 消费者
 * @className top.xiesen.simulation.zork.ZorkAvroConsumer
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/11 14:11 星期三
 */
public class ZorkAvroConsumer {
    public static String KAFKA_KERBEROS_KRB5_CONF = "D:\\develop\\workspace\\code-repo\\simulation-data\\src\\main\\resources\\krb5.conf";
    public static String KAFKA_KERBEROS_JAAS_CONF = "D:\\develop\\workspace\\code-repo\\simulation-data\\src\\main\\resources\\kafka_server_jaas.conf";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "zorkdata224:9092");
        props.put("group.id", "test17");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());

        /**
         * kerberos
         */
        props.setProperty("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "GSSAPI");
        props.put("sasl.kerberos.service.name", "kafka");

//        System.setProperty("java.security.krb5.conf", KAFKA_KERBEROS_KRB5_CONF);
//        System.setProperty("java.security.auth.login.config", KAFKA_KERBEROS_JAAS_CONF);

        props.put("sasl.jaas.config", "com.sun.security.auth.module.Krb5LoginModule required "
                + "useTicketCache=false "
                + "renewTicket=true "
                + "serviceName=\"kafka\""
                + "useKeyTab=true "
                + "keyTab=\"D:\\\\develop\\\\workspace\\\\code-repo\\\\simulation-data\\\\src\\\\main\\\\resources\\\\kafka.keytab\""
                + "principal=\"kafka/zorkdata224@ZORKDATA.COM\";");



        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        consumer.subscribe(Arrays.asList("test"));
        ConsumerRecords<String, String> msgList = consumer.poll(1000);

        if (null != msgList && msgList.count() > 0) {
            for (ConsumerRecord<String, String> record : msgList) {
                String value = record.value();
                System.out.println(value);
            }
        }
    }
}
