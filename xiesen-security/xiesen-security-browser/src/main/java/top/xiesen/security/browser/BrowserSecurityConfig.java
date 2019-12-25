package top.xiesen.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import top.xiesen.security.core.properties.SecurityProperties;

/**
 * @Description
 * @className top.xiesen.security.browser.BrowserSecurityConfig
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/21 16:31
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler xiesenAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler xiesenAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 配置设置使用表单的方式进行登录 http.formLogin()
         * 配置设置使用httpBasic 的方式进行登录 http.httpBasic()
         */
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(xiesenAuthenticationSuccessHandler)
                .failureHandler(xiesenAuthenticationFailureHandler)
//        http.httpBasic()
                .and()
                .authorizeRequests()
                /**
                 * 设置 xiesen-login.html 不需要认证授权
                 */
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                /**
                 * 关闭 csrf 系统防护
                 */
                .and().csrf().disable();

    }
}
