package com.xiesen.micro.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.xiesen.micro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@RestController
public class UserController {

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return new User(id);
    }

    @GetMapping("/eurekainfo")
    public String info() {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("producer-user", false);
        return instanceInfo.getHomePageUrl();
    }
}
