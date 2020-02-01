package top.xiesen.security.core.validate.code.image;

import lombok.Data;
import top.xiesen.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Description 验证码
 * @className top.xiesen.security.core.validate.code.ImageCode
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/22 14:53
 */
@Data
public class ImageCode extends ValidateCode {
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

}
