package com.example.demo1;

import com.baomidou.mybatisplus.annotation.IdType;
import com.example.demo1.dao.UserMapper;
import com.example.demo1.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author 谢森
 * @Description AR 模式测试
 * @className com.example.demo1.ARTest
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/26 10:20 星期二
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ARTest {

    @Test
    public void insert() {
        User user = new User();
        user.setName("向中");
        user.setAge(25);
        user.setEmail("xiangzhong@163.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        boolean insert = user.insert();
        System.out.println(insert);
    }

    @Test
    public void selectById(){
        User user = new User();
        User userSelect = user.selectById(1088248166370832385L);
        System.out.println(userSelect == user);
        System.out.println(userSelect);
    }

    @Test
    public void selectById2(){
        User user = new User();
        user.setId(1088248166370832385L);
        User userSelect = user.selectById();
        System.out.println(userSelect == user);
        System.out.println(userSelect);
    }

    @Test
    public void updateById(){
        User user = new User();
        user.setId(1088248166370832385L);
        user.setName("向中1");
        boolean update = user.updateById();
        System.out.println(update);
    }

    @Test
    public void deleteById(){
        User user = new User();
        user.setId(1088248166370832385L);
        boolean deleteById = user.deleteById();
        System.out.println(deleteById);
    }
}
