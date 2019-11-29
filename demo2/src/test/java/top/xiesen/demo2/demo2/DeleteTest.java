package top.xiesen.demo2.demo2;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.xiesen.demo2.demo2.dao.UserMapper;
import top.xiesen.demo2.demo2.pojo.User;

import java.util.List;

/**
 * @author 谢森
 * @Description TODO
 * @className top.xiesen.demo2.demo2.DeleteTest
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/26 13:36 星期二
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteById() {
        int rows = userMapper.deleteById(1094592041087729666L);
        System.out.println("影响行数: " + rows);
    }

    @Test
    public void select() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void updateById() {
        User user = new User();
        user.setAge(26);
        user.setId(1088248166370832385L);
        int rows = userMapper.updateById(user);
        System.out.println("影响行数: " + rows);
    }

    @Test
    public void mySelect() {
        List<User> userList = userMapper
                .mySelectList(Wrappers.<User>lambdaQuery()
                        .gt(User::getAge, 25)
                        .eq(User::getDeleted, 0));
        userList.forEach(System.out::println);
    }
}
