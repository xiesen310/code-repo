package com.example.demo1;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo1.dao.UserMapper;
import com.example.demo1.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 谢森
 * @Description 删除测试类
 * @className com.example.demo1.DeleteTest
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/26 9:59 星期二
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteById() {
        int rows = userMapper.deleteById(1198839399178137501L);
        System.out.println("删除条数: " + rows);
    }

    @Test
    public void deleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("id", 1198839399178137201L);
        int rows = userMapper.deleteByMap(columnMap);
        System.out.println("删除条数: " + rows);
    }

    @Test
    public void deleteBatchIds() {
        int rows = userMapper.deleteBatchIds(Arrays.asList(1198839399178137301L,
                1198839399178137401L,
                1198839399178137501L));
        System.out.println("删除条数: " + rows);
    }

    @Test
    public void deleteByWrapper(){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        userLambdaQueryWrapper.eq(User::getAge,25).or().gt(User::getAge,41);
        int rows = userMapper.delete(userLambdaQueryWrapper);
        System.out.println("删除条数: " + rows);
    }

}
