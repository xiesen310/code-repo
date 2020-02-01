package top.xiesen.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import top.xiesen.security.core.properties.SecurityConstants;

/**
 * @Description
 * @className top.xiesen.security.core.authentication.AbstractChannelSecurityConfig
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/2/1 17:10
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler xiesenAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler xiesenAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(xiesenAuthenticationSuccessHandler)
                .failureHandler(xiesenAuthenticationFailureHandler);
    }

}

