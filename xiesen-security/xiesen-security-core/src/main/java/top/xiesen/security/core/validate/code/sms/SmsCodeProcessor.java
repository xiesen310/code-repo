package top.xiesen.security.core.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import top.xiesen.security.core.properties.SecurityConstants;
import top.xiesen.security.core.validate.code.ValidateCode;
import top.xiesen.security.core.validate.code.impl.AbstractValidateCodeProcessor;

/**
 * @Description 短信验证码处理器
 * @className top.xiesen.security.core.validate.code.sms.SmsCodeProcessor
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/1/5 17:49
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
