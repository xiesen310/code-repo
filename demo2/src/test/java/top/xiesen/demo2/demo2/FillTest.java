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
 * @Description 自动填充测试类
 * @className top.xiesen.demo2.demo2.FillTest
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/29 14:28 星期五
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FillTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("刘西");
        user.setAge(30);
        user.setEmail("lx@163.com");
        user.setManagerId(1088248166370832385L);
        int rows = userMapper.insert(user);
        System.out.println("影响的行数: " + rows);
    }

    @Test
    public void update() {
        User user = new User();
        user.setAge(27);
        user.setId(1088248166370832385L);
        int rows = userMapper.updateById(user);
        System.out.println("影响的行数: " + rows);
    }
}
