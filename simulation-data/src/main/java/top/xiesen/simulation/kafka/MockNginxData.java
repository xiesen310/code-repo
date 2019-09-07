package top.xiesen.simulation.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;
import java.util.UUID;

/**
 * 模拟nginx日志信息
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/11/11 13:37.
 */
public class MockNginxData {
    private KafkaProducer<String, String> producer;
    private Properties properties;

    /**
     * 加载配置信息
     */
    public MockNginxData() {
        properties = new Properties();
//        properties.put("bootstrap.servers", "bigdata01:9092,bigdata02:9092,bigdata05:9092");
        properties.put("bootstrap.servers", "test:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
    }

    /**
     * 发送数据
     * @param topic 主题
     * @param key
     * @param value
     */
    public void sendRecorder(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
    }

    /**
     * appid    ip                 mid                                 userid  login_type  request             status  http_refereer user_agent                                                            time
     * 1000    121.11.87.171   8b0ea90a-77a5-4034-99ed-403c800263dd    20202   1       GET /top HTTP/1.0       408     null      Mozilla/5.0 (Windows; U; Windows NT 5.1)Gecko/20070803 Firefox/1.5.0.12 1523188120263
     *
     * appid包括：web:1000,android:1001,ios:1002,ipad:1003
     * mid:唯一的id此id第一次会种在浏览器的cookie里。如果存在则不再种。作为浏览器唯一标示。移动端或者pad直接取机器码。
     * login_type：登录状态，0未登录、1：登录用户
     * request：类似于此种 "GET /userList HTTP/1.1"
     * status：请求的状态主要有：200 ok、404 not found、408 Request Timeout、500 Internal Server Error、504 Gateway Timeout等
     * http_referer：请求该url的上一个url地址。
     * user_agent：浏览器的信息，例如："Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36"
     * time：时间的long格式：1451451433818
     *
     * @return
     */
    public static String message() {
        Random random = new Random();

        String appIds[] = {"1000","1001","1002","1003"};
        String ips[] = {"192.168.1.91", "192.168.1.92", "192.168.1.93"};
        String mid = UUID.randomUUID().toString();
        String userId = "202" + random.nextInt(100);
        String loginTypes[] = {"0","1"};
        String request = "GET /userList HTTP/1.1";
        String statuses[] = {"200","404","408","500","504"};
        String httpRefereer =  "null";
        String userAgent[] = {"Mozilla/5.0 (Windows NT 6.1; WOW64)","AppleWebKit/537.36 (KHTML, like Gecko)","Chrome/47.0.2526.106 Safari/537.36"};
        long time = System.currentTimeMillis();
        String msg = appIds[random.nextInt(appIds.length)] + "  " + ips[random.nextInt(ips.length)] + "   "
                + mid + " " +  userId + "   " + loginTypes[random.nextInt(loginTypes.length)] + "   " +
                request + "   " + statuses[random.nextInt(statuses.length)] + "   " +
                httpRefereer + "   " + userAgent[random.nextInt(userAgent.length)] + "  " + time;

        String event = msg.toString();
        return event;
    }

    public static void main(String[] args) {
        MockNginxData mockNginxData = new MockNginxData();
        String topic = "zorkdata";
        int count = 0;
        while (true) {
            count ++ ;
            String key = "nginx-" + count;
            String msg = message();
            try{
                mockNginxData.sendRecorder(topic,key,msg);
            }
            catch (Exception e) {
                System.err.println("MockNginxData 产生数据失败");
                e.printStackTrace();
            }
        }
    }
}
