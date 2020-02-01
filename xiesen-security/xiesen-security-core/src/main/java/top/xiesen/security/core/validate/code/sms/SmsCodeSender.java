package top.xiesen.security.core.validate.code.sms;

/**
 * @Description 短信发送接口, 不同的短信服务商实现此接口
 * @className top.xiesen.security.core.validate.code.sms.SmsCodeSender
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/1/5 16:40
 */
public interface SmsCodeSender {
    void send(String mobile, String code);
}
