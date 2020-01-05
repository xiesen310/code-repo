package top.xiesen.security.core.properties;

import lombok.Data;

/**
 * @Description
 * @className top.xiesen.security.core.properties.BrowserProperties
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/22 10:57
 */
@Data
public class BrowserProperties {
    private String loginPage = "/xiesen-login-in.html";
    private LoginType loginType = LoginType.JSON;
    private int rememberMeSeconds = 3600;

}
