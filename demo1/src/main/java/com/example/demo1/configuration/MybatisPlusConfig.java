package com.example.demo1.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 谢森
 * @Description mybatis plus 配置类
 * @className com.example.demo1.configuration.MybatisPlusConfig
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/25 19:19 星期一
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
