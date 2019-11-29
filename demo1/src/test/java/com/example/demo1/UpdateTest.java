package com.example.demo1;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.example.demo1.dao.UserMapper;
import com.example.demo1.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 谢森
 * @Description 更新测试类
 * @className com.example.demo1.UpdateTest
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/25 19:45 星期一
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void updateByIds() {
        User user = new User();
        user.setId(1088248166370832385L);
        user.setAge(26);
        user.setEmail("wtf2@baomidou.com");
        int rows = userMapper.updateById(user);
        System.out.println("影响记录数: " + rows);
    }

    @Test
    public void updateByWrapper() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟").eq("age", 28);

        User user = new User();
        user.setAge(29);
        user.setEmail("lyw2019@baomidou.com");
        int rows = userMapper.update(user, updateWrapper);
        System.out.println("影响记录数: " + rows);
    }

    @Test
    public void updateByWrapper1() {

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟").eq("age", 28);

        User user = new User();
        user.setAge(29);
        user.setEmail("lyw2019@baomidou.com");
        int rows = userMapper.update(user, updateWrapper);
        System.out.println("影响记录数: " + rows);
    }

    @Test
    public void updateByWrapper2() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "李艺伟")
                .eq("age", 29)
                .set("age", 30);

        int rows = userMapper.update(null, updateWrapper);
        System.out.println("影响记录数: " + rows);
    }

    @Test
    public void updateByWrapper3() {
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = Wrappers.<User>lambdaUpdate();
        userLambdaUpdateWrapper.eq(User::getName,"李艺伟")
                .eq(User::getAge,30)
                .set(User::getAge,31);

        int rows = userMapper.update(null, userLambdaUpdateWrapper);
        System.out.println("影响记录数: " + rows);
    }

    @Test
    public void updateByWrapper4() {
        boolean update = new LambdaUpdateChainWrapper<User>(userMapper).eq(User::getName, "李艺伟")
                .eq(User::getAge, 31)
                .set(User::getAge, 32).update();
        System.out.println(update);
    }
}
