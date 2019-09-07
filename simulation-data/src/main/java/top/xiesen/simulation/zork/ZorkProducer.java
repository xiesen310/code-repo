package top.xiesen.simulation.zork;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Logger;
import top.xiesen.simulation.zork.avro.AvroSerializer;
import top.xiesen.simulation.zork.avro.AvroSerializerFactory;
import top.xiesen.simulation.zork.pojo.ZorkData;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * zork 单线程生产者
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2019/2/24 15:32.
 */
public class ZorkProducer {

    private static final Logger LOG = Logger.getLogger(ZorkProducer.class);

    /**
     * 设置实例生产消息的总数
     */
    private static final int MSG_SIZE = 20;

    /**
     * 主题名称
     */
    private static final String TOPIC = "test";

    /**
     * kafka 集群
     */
    private static final String BROKER_LIST = "zorkdata222:9092";

    /**
     * 线程数
     */
    private static final int HREADS_NUM = 2;


    private static KafkaProducer<String,String> producer = null;

    static {
        // 1. 构造用于实例化Kaf kaProducer 的Properties 信息
        Properties configs = initConfig();
        // 2. 初始化一个 KafkaProducer
        producer = new KafkaProducer<String, String>(configs);
    }

    /**
     * 初始化 Kafka 配置
     * @return
     */
    private static Properties initConfig() {
        Properties properties = new Properties();

        // kakfa broker 列表
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);

        // 设置序列化类
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        return properties;
    }

    /**
     * 生产zorkdata 信息
     * @return
     */
    private static ZorkData createZorkDataInfo() {
        ZorkData zorkData = new ZorkData();
        Random r = new Random();
        String offset = "600100" + r.nextInt(10);
//        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

        // 统一系统时间为UTC时间
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());

        Map<String,String> dimensions = new HashMap<>();
        dimensions.put("ip","192.168.1.1");
        dimensions.put("hostname","localhost");
        dimensions.put("appsystem","tdx");
        dimensions.put("appprogramname","tdx");

        Map<String,Double> measures = new HashMap<>();
        measures.put("lantern",0.5);

        Map<String,String> normalFields = new HashMap<>();
        normalFields.put("message",timestamp + " order service error");
        normalFields.put("logTime",timestamp);

        zorkData.setOffset(offset);
        zorkData.setLogTypeName("zorkdata");
        zorkData.setSource("/var/log/logstash/logstash-plain.log");
        zorkData.setTimestamp(timestamp);
        zorkData.setDimensions(dimensions);
//        zorkData.setMeasures(measures);
        zorkData.setNormalFields(normalFields);
        return zorkData;
    }

    /**
     * zorkData 对象转换成json字符串
     * @param zorkData
     * @return
     */
    private static String  zorkData2JsonString(ZorkData zorkData) {
        return JSON.toJSONString(zorkData);
    }


    /**
     * 创建zorkdata son字符串
     * @return
     */
    private static String createZorkdataJsonString() {
        return zorkData2JsonString(createZorkDataInfo());
    }



    /**
     * 单线程生产者
     */
    public static void test1() {
        ProducerRecord<String,String> record = null;
        String msg = null;

        try{
            int num = 0;
            for (int i = 0; i< MSG_SIZE; i++ ) {
                msg = createZorkdataJsonString();
                record = new ProducerRecord<>(TOPIC,"zorklog",msg);
                producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if(null != e) {
                            LOG.error("Send message occurs exception: " + e);
                        }
                        if (null != recordMetadata) {
                            LOG.info(String.format("offset:%s,partition:%s",recordMetadata.offset(),recordMetadata.partition()));
                        }
                    }
                });  // 异步发送消息


                if(num++ % 2 == 0) {
                    Thread.sleep(2000L); // 休眠2s
                }
            }
        }catch (InterruptedException e) {
            LOG.error("Send message occurs exception: ",e);
        }finally {
            producer.close();
        }
    }



    public static void main(String[] args) {
        test1();
    }
}
