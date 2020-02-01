package top.xiesen.security.core.validate.code.sms;

/**
 * @Description 发送手机验证码逻辑
 * @className top.xiesen.security.core.validate.code.sms.DefaultSmsCodeSender
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/1/5 16:41
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机 " + mobile + " 发送短信验证码 " + code);
    }
}
