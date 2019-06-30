package com.xiesen.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * MoviceRibbonApplication main
 *
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient("PRODUCER-USER") // 启用 ribbon 对 PRODUCER-USER 进行负载均衡
public class MoviceRibbonApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main( String[] args ) {
        SpringApplication.run(MoviceRibbonApplication.class);
    }
}
