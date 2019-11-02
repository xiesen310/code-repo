package top.xiesen.security.demo.service.impl;

import org.springframework.stereotype.Service;
import top.xiesen.security.demo.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        System.out.println("greeting");
        return "hello " + name;
    }
}
