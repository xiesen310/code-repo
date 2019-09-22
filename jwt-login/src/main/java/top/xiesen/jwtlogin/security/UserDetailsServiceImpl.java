package top.xiesen.jwtlogin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.xiesen.jwtlogin.entity.UserEntity;
import top.xiesen.jwtlogin.service.UserService;
import org.springframework.security.core.userdetails.User;

/**
 * 实现官方的 UserDetailsService 接口，这里主要负责一个从数据库拿数据的操作
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userService.getUserByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("This username didn't exist.");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRole());
    }
}
