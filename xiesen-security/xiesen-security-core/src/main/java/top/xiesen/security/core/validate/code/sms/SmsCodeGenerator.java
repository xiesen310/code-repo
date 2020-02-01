package top.xiesen.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import top.xiesen.security.core.properties.SecurityProperties;
import top.xiesen.security.core.validate.code.ValidateCode;
import top.xiesen.security.core.validate.code.ValidateCodeGenerator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Description
 * @className top.xiesen.security.core.validate.code.ImageCodeGenerator
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/1/5 14:05
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;
    
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
