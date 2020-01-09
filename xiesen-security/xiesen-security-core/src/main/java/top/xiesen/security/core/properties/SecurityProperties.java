package top.xiesen.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description
 * @className top.xiesen.security.core.properties.SecurityProperties
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/22 10:56
 */
@ConfigurationProperties(prefix = "xiesen.security")
public class SecurityProperties {
    BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
