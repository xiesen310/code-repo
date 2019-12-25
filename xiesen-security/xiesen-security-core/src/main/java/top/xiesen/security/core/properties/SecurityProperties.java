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

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
