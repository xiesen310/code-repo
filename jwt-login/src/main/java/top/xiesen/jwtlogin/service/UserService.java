package top.xiesen.jwtlogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xiesen.jwtlogin.entity.UserEntity;

@Service
public class UserService {
    @Autowired
    private Database database;

    public UserEntity getUserByUsername(String username) {
        return database.getDatabase().get(username);
    }

}
