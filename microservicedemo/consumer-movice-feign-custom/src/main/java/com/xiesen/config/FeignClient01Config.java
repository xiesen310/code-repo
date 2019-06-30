package com.xiesen.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义 feignclient 配置
 *
 * @author Allen
 */
@Configuration
public class FeignClient01Config {
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }
}
