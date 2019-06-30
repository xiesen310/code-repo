package com.xiesen.micro;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
@ExcludeCommentScan // 排除注解
public class TestConfig {

    @Autowired
    IClientConfig iClientConfig;

    /**
     * 创建负载均衡算法的方法
     * @param config
     * @return
     */
    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new RandomRule(); // 返回随机算法
    }
}
