package top.xiesen.verify.kafka;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xiesen.verify.pojo.JsonResult;
import top.xiesen.verify.pojo.KafkaTopicBean;
import top.xiesen.verify.pojo.ZookeeperBean;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class KafkaAdminTest {
    KafkaAdmin kafkaAdmin = null;

    @Before
    public void setUpBeforeClass() {
        ZookeeperBean zookeeperBean = new ZookeeperBean();
        zookeeperBean.setZkUrl("192.168.1.91:2181/01021");
        kafkaAdmin = new KafkaAdmin(zookeeperBean);
    }

    @Test
    public void createKafkaTopic() {
        KafkaTopicBean topicBean = new KafkaTopicBean();
        topicBean.setTopicName("test1");
        topicBean.setReplication(1);
        topicBean.setPartition(1);
        JsonResult kafkaTopic = kafkaAdmin.createKafkaTopic(topicBean);
        System.out.println(kafkaTopic);
    }

    @Test
    public void topicExists() {
        System.out.println(kafkaAdmin.topicExists("test1"));
    }

    @Test
    public void listKafkaTopic() {
        JsonResult<List<String>> listJSONResult = kafkaAdmin.listKafkaTopic();
        List<String> resultData = (List<String>) listJSONResult.getData();
        resultData.forEach((list) -> System.out.println(list));
    }

    @Test
    public void deleteKafkaTopic() {
        JsonResult deleteKafkaTopic = kafkaAdmin.deleteKafkaTopic("test1");
        System.out.println(deleteKafkaTopic);
    }


}
