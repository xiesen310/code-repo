package top.xiesen.security.demo.web.controller;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import top.xiesen.security.demo.dto.User;
import top.xiesen.security.demo.dto.UserQueryCondition;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(page = 1,
                                    size = 17,
                                    sort = "username,asc") Pageable pageable) {

        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
    public User getInfo(@PathVariable(name = "id", required = true) String id) {
        User user = new User();
        user.setUsername("tom");
        return user;
    }
}
