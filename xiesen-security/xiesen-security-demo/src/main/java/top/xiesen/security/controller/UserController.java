package top.xiesen.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import top.xiesen.security.dto.User;
import top.xiesen.security.dto.UserQueryCondition;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @className top.xiesen.security.controller.UserController
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/21 9:57
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * @param user
     * @return
     * @Valid 校验数据
     * BindingResult errors 错误信息绑定
     */
    @PostMapping
    @ApiOperation("创建用户")
    public User create(@ApiParam("用户对象") @Valid @RequestBody User user) {
        System.out.println(user);
        user.setId("1");
        return user;
    }

    @ApiOperation("根据用户 ID 删除")
    @DeleteMapping("/{id:\\d+}")
    public void delete(@ApiParam("用户 ID") @PathVariable String id) {
        System.out.println(id);
    }

    @ApiOperation("用户更新")
    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(
                    error -> {
                        FieldError fieldError = (FieldError) error;
                        String msg = fieldError.getField() + " " + error.getDefaultMessage();
                        System.out.println(msg);
                    }
            );
        }
        System.out.println(user);

        return user;
    }

    /**
     * @return
     * @RequestParam(name = "username", required = false, defaultValue = "tom") String username
     * @RequestMapping(value = "/user", method = RequestMethod.GET)
     */
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation("用户查询")
    public List<User> query(UserQueryCondition userQueryCondition, Pageable pageable) {
        System.out.println(userQueryCondition);
        System.out.println(pageable);
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }


    /**
     * 正则表达式设置参数的格式
     *
     * @param id
     * @return
     * @RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
     * @RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
     */
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    @ApiOperation("根据 ID 查询")
    public User getInfo(@ApiParam("用户 ID") @PathVariable String id) {
//        throw new UserNotExistException(id);
        User user = new User();
        user.setUsername("tom");
        System.out.println(user);
        return user;
    }
}
