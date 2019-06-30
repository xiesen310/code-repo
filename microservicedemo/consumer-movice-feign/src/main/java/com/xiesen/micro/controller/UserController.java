package com.xiesen.micro.controller;

import com.xiesen.micro.feign.FeignClient01;
import com.xiesen.micro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Allen
 */
@RestController
public class UserController {

    @Autowired
    private FeignClient01 feignClient01;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        User user = feignClient01.getOrder(id);
        return user;
    }

    @GetMapping("/get-user")
    public User getUser(User user) {
        User user1 = feignClient01.getUser(user);
        return user1;
    }
}
