package top.xiesen.demo2.demo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.xiesen.demo2.demo2.dao.UserMapper;
import top.xiesen.demo2.demo2.pojo.User;

/**
 * @author 谢森
 * @Description 查询测试
 * @className top.xiesen.demo2.demo2.SearchTest
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/26 11:43 星期二
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void select(){
        User user = userMapper.selectById(1088248166370832385L);
        System.out.println(user);
    }
}
