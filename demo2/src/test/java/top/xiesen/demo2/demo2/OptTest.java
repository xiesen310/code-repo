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
 * @Description TODO
 * @className top.xiesen.demo2.demo2.OptTest
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/29 14:57 星期五
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OptTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void update() {
        // 更新前版本号
        int version = 0;
        User user = new User();
        user.setAge(31);
        user.setVersion(version);
        user.setId(1200304540868423682L);

        int rows = userMapper.updateById(user);
        System.out.println("影响记录条数: " + rows);
    }
}
