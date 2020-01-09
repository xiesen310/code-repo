package top.xiesen.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description 自定义校验异常
 * @className top.xiesen.security.core.validate.code.ValidateCodeException
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/22 15:23
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 3695806567333917245L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
