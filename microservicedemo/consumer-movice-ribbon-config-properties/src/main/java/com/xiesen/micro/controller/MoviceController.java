package com.xiesen.micro.controller;

import com.netflix.discovery.EurekaClient;
import com.xiesen.micro.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/movice/{id}")
    public User getUser(@PathVariable Long id) {
        // 访问提供者，获取数据
//        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("PRODUCER-USER", false);
//        String pageUrl = instanceInfo.getHomePageUrl();
        User user = restTemplate.getForObject("http://PRODUCER-USER/user/" + id, User.class);
        return user;
    }

    @GetMapping("/test")
    public String test() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCER-USER"); // 查找对应服务的实例,会通过负载均衡的算法返回一个
        System.err.println("[ribbon] " + serviceInstance.getServiceId() + "," + serviceInstance.getHost() + ":" + serviceInstance.getPort());
        return "1";

    }
}
