package top.xiesen.security.core.properties;

import lombok.Data;

/**
 * @Description 验证码配置
 * @className top.xiesen.security.core.properties.ImageCodeProperties
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/1/5 13:29
 */
@Data
public class ImageCodeProperties {
    /**
     * 验证码的宽度
     */
    private int width = 67;

    /**
     * 验证码高度
     */
    private int height = 23;

    /**
     * 验证码长度
     */
    private int length = 4;

    /**
     * 验证码失效时间
     */
    private int expireIn = 60;

    /**
     * 设置需要验证验证码的请求地址
     */
    private String url = "/authentication/form";

}
