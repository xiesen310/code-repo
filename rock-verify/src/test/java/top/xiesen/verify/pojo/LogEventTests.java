package top.xiesen.verify.pojo;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @className top.xiesen.verify.pojo.LogEventTests
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/20 12:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class LogEventTests {

    /**
     * @NotNull private String businessId;
     * private String type;
     * @NotNull private Long timestamp;
     * private String source;
     * private String hosts;
     * @NotNull private String ip;
     * private Long offset;
     * @NotNull private String message;
     * <p>
     * <p>
     * private Map<String, String> dim;
     * <p>
     * <p>
     * private Map<String, String> tags;
     */
    @Test
    public void test() {
        LogEvent logEvent = new LogEvent();
        logEvent.setBusinessId("1");
        logEvent.setType("tomcat");
        logEvent.setTimestamp(System.currentTimeMillis());
        logEvent.setSource("/etc/hosts");
        logEvent.setIp("127.0.0.1");
        logEvent.setHosts("localhost");
        logEvent.setMessage("2020-03-20 12:44:33,582 [http-nio-8080-exec-1] [org.apache.kafka.common.utils.AppInfoParser$AppInfo.<init>(AppInfoParser.java:83)] - [INFO] Kafka version : 0.10.2.1");
        Map<String, String> dim = new HashMap<>();
        dim.put("app", "rock-verify");
        Map<String, String> tags = new HashMap<>();
        tags.put("leave", "info");
        logEvent.setDim(dim);
        logEvent.setTags(tags);

        String msg = JSON.toJSONString(logEvent);
        System.out.println(msg);

    }
}
