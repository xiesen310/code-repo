package top.xiesen.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description 验证码生成器
 * @className top.xiesen.security.core.validate.code.ValidateCodeGenerator
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/1/5 14:02
 */
public interface ValidateCodeGenerator {
    ImageCode generator(ServletWebRequest request);
}
