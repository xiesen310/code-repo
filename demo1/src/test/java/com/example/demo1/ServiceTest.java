package com.example.demo1;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo1.pojo.User;
import com.example.demo1.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author 谢森
 * @Description TODO
 * @className com.example.demo1.ServiceTest
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/26 11:01 星期二
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void getOne() {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge, 25), false);
        System.out.println(user);
    }

    @Test
    public void batch(){
        User user1 = new User();
        user1.setName("徐丽1");
        user1.setAge(28);

        User user2 = new User();
        user2.setName("徐丽2");
        user2.setAge(30);

        List<User> userList = Arrays.asList(user1, user2);
        boolean batch = userService.saveOrUpdateBatch(userList);
        System.out.println(batch);
    }

    @Test
    public void chain(){
        List<User> userList = userService.lambdaQuery().gt(User::getAge, 25).like(User::getName, "雨").list();
        userList.forEach(System.out::println);
    }

    @Test
    public void chain1(){
        boolean update = userService.lambdaUpdate().eq(User::getAge, 25).set(User::getAge, 26).update();
        System.out.println(update);
    }

    @Test
    public void remove(){
        boolean remove = userService.lambdaUpdate().eq(User::getName, "向中").remove();
        System.out.println(remove);
    }
}
