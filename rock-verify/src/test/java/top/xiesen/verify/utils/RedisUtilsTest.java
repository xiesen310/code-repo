package top.xiesen.verify.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description
 * @className top.xiesen.verify.utils.RedisUtilsTest
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/20 23:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class RedisUtilsTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void test() {
        redisUtils.save("zorkdata","zorkdata");
    }
}
