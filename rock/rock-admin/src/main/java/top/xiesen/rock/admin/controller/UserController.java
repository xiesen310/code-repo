package top.xiesen.rock.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.xiesen.rock.admin.model.User;
import top.xiesen.rock.admin.model.dto.RespDto;
import top.xiesen.rock.admin.service.UserService;

@Api(value = "用户接口api")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "按照用户 id 进行查询")
    @RequestMapping(value = "/user/selectUserById", method = RequestMethod.GET)
    @ResponseBody
    public RespDto selectUserById(
            @ApiParam(value = "插件实例主键id", required = true)
            @RequestParam(value = "id", required = true) Integer id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    @ResponseBody
    public RespDto login(
            @ApiParam(value = "用户对象", required = true) @RequestBody User user) {
        return userService.login(user);
    }


}
