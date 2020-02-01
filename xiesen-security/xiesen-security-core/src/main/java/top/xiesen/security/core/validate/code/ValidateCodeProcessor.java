package top.xiesen.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description 校验码处理器，封装不同校验码的处理逻辑
 * @className top.xiesen.security.core.validate.code.ValidateCodeProcessor
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/1/5 17:32
 */
public interface ValidateCodeProcessor {
    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     *
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest spring 封装请求和响应
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);
}
