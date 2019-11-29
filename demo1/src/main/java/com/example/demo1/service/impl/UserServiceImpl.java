package com.example.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1.dao.UserMapper;
import com.example.demo1.pojo.User;
import com.example.demo1.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 谢森
 * @Description UserService 服务
 * @className com.example.demo1.service.impl.UserServiceImpl
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/26 10:59 星期二
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
