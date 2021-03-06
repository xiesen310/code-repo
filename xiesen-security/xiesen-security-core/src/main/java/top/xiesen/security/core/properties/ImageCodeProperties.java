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
public class ImageCodeProperties extends SmsCodeProperties {

    /**
     * 验证码的宽度
     */
    private int width = 67;

    /**
     * 验证码高度
     */
    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }
}
