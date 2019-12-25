package top.xiesen.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * @Description
 * @className top.xiesen.security.browser.MyUserDetailsService
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/21 16:56
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(" 登录用户名是: {}", username);
        String password = "123456";
        String databasePassword = passwordEncoder.encode(password);
        logger.info("数据库密码是: " + databasePassword);
        /**
         * 1. 根据用户名查找用户信息
         * 2. 根据查找到的信息判断用户是否被冻结
         */
        return new User(username, databasePassword,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
