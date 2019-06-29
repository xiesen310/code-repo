package com.xiesen.micro.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.xiesen.micro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MoviceController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${user.url}")
    private String url;

    @GetMapping("/movice/{id}")
    public User getUser(@PathVariable Long id) {
        // 访问提供者，获取数据
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("PRODUCER-USER", false);
        String pageUrl = instanceInfo.getHomePageUrl();
        return restTemplate.getForObject(pageUrl + "/user/" + id, User.class);
    }
}
