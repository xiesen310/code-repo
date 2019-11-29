package com.example.demo1;

import com.example.demo1.dao.UserMapper;
import com.example.demo1.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @Description 插入测试
 * @className com.example.demo1.InsertTest
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/25 13:35 星期一
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("向中1");
        user.setAge(25);
        user.setEmail("xiangzhong@163.com");
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        int rows = userMapper.insert(user);
        System.out.println("影响记录数: " + rows);
    }
}
