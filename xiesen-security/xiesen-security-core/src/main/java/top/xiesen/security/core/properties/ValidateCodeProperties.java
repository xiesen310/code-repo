package top.xiesen.security.core.properties;

import lombok.Data;

/**
 * @Description 校验码验证配置
 * @className top.xiesen.security.core.properties.ValidateCodeProperties
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/1/5 13:33
 */
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
