package top.xiesen.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.xiesen.security.core.properties.SecurityProperties;

/**
 * @Description
 * @className top.xiesen.security.core.SecurityCoreConfig
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/22 11:00
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
