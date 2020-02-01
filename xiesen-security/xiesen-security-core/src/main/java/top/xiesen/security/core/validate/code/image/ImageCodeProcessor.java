package top.xiesen.security.core.validate.code.image;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import top.xiesen.security.core.validate.code.impl.AbstractValidateCodeProcessor;

import javax.imageio.ImageIO;

/**
 * @Description 图片验证码处理器
 * @className top.xiesen.security.core.validate.code.image.ImageCodeProcessor
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/1/5 17:52
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    /**
     * 发送图形验证码，将其写到响应中
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
