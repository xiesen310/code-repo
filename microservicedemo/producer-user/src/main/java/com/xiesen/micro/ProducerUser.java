package com.xiesen.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * springboot main
 */
@SpringBootApplication
@EnableEurekaClient
public class ProducerUser {
    public static void main(String[] args) {
        SpringApplication.run(ProducerUser.class);
    }
}
