package top.xiesen.security.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import top.xiesen.security.core.validate.code.ImageCode;
import top.xiesen.security.core.validate.code.ValidateCodeGenerator;

/**
 * @Description
 * @className top.xiesen.security.code.DemoImageCodeGenerator
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/1/5 14:23
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generator(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生产代码");
        return null;
    }
}
