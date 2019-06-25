package com.xiesen.micro.controller;

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


    @Value("${user.url}")
    private String url;

    @GetMapping("/movice/{id}")
    public User getUser(@PathVariable Long id) {
        return restTemplate.getForObject(url + id, User.class);
    }
}
