package com.xiesen.config;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义 feignclient 配置
 *
 * @author Allen
 */
@Configuration
public class FeignClient02Config {
    /**
     * 用于创建用户名和密码的对象
     * @return
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthorizationInterceptor() {
        return new BasicAuthRequestInterceptor("user","123");
    }

    /**
     * 配置输出的日志是那些， 必须在 debug 模式下才可以输出
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
