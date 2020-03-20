package top.xiesen.verify.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @className top.xiesen.verify.config.RedisConfig
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/20 23:10
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
}
