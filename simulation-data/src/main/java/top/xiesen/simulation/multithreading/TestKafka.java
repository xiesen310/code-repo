package top.xiesen.simulation.multithreading;

import com.alibaba.fastjson.JSONObject;
import top.xiesen.test.pojo.User;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestKafka {
    public void testSendMessageSingleton() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Random random = new Random();
            for (int i = 1; i <= 1000000; i++) {
                User user = new User();
                int randint = (int) Math.floor((random.nextDouble() * 100000.0)) + 1; // 1 - 100000
                user.setUserId(i);
                user.setTimestamps(String.valueOf(System.currentTimeMillis()));
                user.setFuncId(randint);
                String msg = JSONObject.toJSON(user).toString();
                executor.submit(new HandlerProducer("xiesen5", msg));
            }
    }

    public static void main(String[] args) throws InterruptedException {
        TestKafka testKafka = new TestKafka();
        testKafka.testSendMessageSingleton();
    }
}
