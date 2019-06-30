package com.xiesen.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author Allen
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignApplicationCustom {
    public static void main( String[] args ) {
        SpringApplication.run(FeignApplicationCustom.class);
    }
}
