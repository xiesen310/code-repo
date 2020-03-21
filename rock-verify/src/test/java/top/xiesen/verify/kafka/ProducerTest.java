package top.xiesen.verify.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class ProducerTest {

    @Test
    public void sendLog() {
        Producer producer = ProducerPool.getInstance().getProducer();
        String logJson = "{\"logTypeName\":\"tomcat\",\"timestamp\":\"2019-07-31T15:17:20.094+08:00\",\"source\":\"/logs/goldsun-biz.log\",\"offset\":\"3947660\"}";
        producer.sendLog(logJson);
    }

    @Test
    public void test1() {

    }
}
