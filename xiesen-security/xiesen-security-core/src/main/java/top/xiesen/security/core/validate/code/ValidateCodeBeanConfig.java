package top.xiesen.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.xiesen.security.core.properties.SecurityProperties;
import top.xiesen.security.core.validate.code.image.ImageCodeGenerator;
import top.xiesen.security.core.validate.code.sms.DefaultSmsCodeSender;
import top.xiesen.security.core.validate.code.sms.SmsCodeGenerator;
import top.xiesen.security.core.validate.code.sms.SmsCodeSender;

/**
 * @Description 验证码 bean 配置
 * @className top.xiesen.security.core.validate.code.ValidateCodeBeanConfig
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/1/5 14:14
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}
