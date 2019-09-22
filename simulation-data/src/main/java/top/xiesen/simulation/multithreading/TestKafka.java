package top.xiesen.simulation.multithreading;

import com.alibaba.fastjson.JSONObject;
import top.xiesen.simulation.zork.pojo.Xiesen;
import top.xiesen.test.pojo.User;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestKafka {
    public void testSendMessageSingleton() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Random random = new Random();
//        for (int j = 0; j < 5; j++) {
//            for (int i = 1; i <= 1000000; i++) {
        User user = new User();
        int randint = (int) Math.floor((random.nextDouble() * 100000.0)) + 1; // 1 - 100000
        user.setUserId(1);
        user.setTimestamps(String.valueOf(System.currentTimeMillis()));
        user.setFuncId(randint);
        String msg = JSONObject.toJSON(user).toString();
        executor.submit(new HandlerProducer("xiesen5", msg));
//            }
//            Thread.sleep(10000);
//        }
    }

    public void testSendMessageSingleton3() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Xiesen xiesen = new Xiesen();
        xiesen.setA("a");
        xiesen.setB("a");
        xiesen.setC("a");
        xiesen.setD("a");
        xiesen.setE("a");
        xiesen.setF("a");
        xiesen.setG("a");
        xiesen.setH("a");
        xiesen.setI("a");
        xiesen.setJ("a");
        xiesen.setK("a");
        xiesen.setL("a");
        xiesen.setM("a");
        xiesen.setN("a");
        xiesen.setO("a");
        xiesen.setP("a");
        xiesen.setQ("a");
        xiesen.setR("a");
        xiesen.setS("a");
        xiesen.setT("a");
        xiesen.setU("a");
        xiesen.setV("a");
        xiesen.setW("a");
        xiesen.setX("a");
        xiesen.setY("a");
        xiesen.setZ("a");
        String msg = JSONObject.toJSON(xiesen).toString();
        executor.submit(new HandlerProducer("xiesen6", msg));
//            }
//            Thread.sleep(10000);
//        }
    }

    public void testSendMessageSingleton1() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Random random = new Random();
//        for (int j = 0; j < 5; j++) {
//            for (int i = 1; i <= 1000000; i++) {


        Map<String, Object> event = new HashMap<String, Object>();
        String logTypeName = "log";
        // 统一系统时间为UTC时间
        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());

        String source = "/var/log/elasticseach/es.log";
        String offset = "122";
        Map<String, String> dimensions = new HashMap<String, String>();
        dimensions.put("hostname", "localhost");
        dimensions.put("appsystem", "tdx");
        dimensions.put("appprogramname", "tdx");
        dimensions.put("ip", "192.168.1.1");

        Map<String, Double> measures = new HashMap<String, Double>();
        measures.put("lantern", 0.5);
        String status[] = {"req", "resp"};


        Map<String, String> normalFields = new HashMap<String, String>();
        normalFields.put("message", timestamp + " this is message...");
        normalFields.put("logtime", timestamp);
        normalFields.put("status", status[random.nextInt(status.length)]);
        normalFields.put("id", String.valueOf(random.nextInt(100)));


        event.put("logTypeName", logTypeName);
        event.put("timestamp", timestamp);
        event.put("source", source);
        event.put("offset", offset);
        event.put("dimensions", dimensions);
        event.put("measures", measures);
        event.put("normalFields", normalFields);
        String msg = JSONObject.toJSON(event).toString();
        executor.submit(new HandlerProducer("xiesen5", msg));
//            }
//            Thread.sleep(10000);
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestKafka testKafka = new TestKafka();
        testKafka.testSendMessageSingleton3();
    }
}
