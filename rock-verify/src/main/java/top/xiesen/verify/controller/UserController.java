package top.xiesen.verify.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xiesen.verify.pojo.JSONResult;
import top.xiesen.verify.pojo.Resource;
import top.xiesen.verify.pojo.User;

import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private Resource resource;

    @RequestMapping("/getUser")
    public JSONResult getUser() {
        User user = new User();
        user.setName("谢森");
        user.setAge(18);
        user.setBirthday(new Date());
        user.setPassword("xiesen");
        user.setDesc("描述信息");
        return JSONResult.ok(user);
    }

    @RequestMapping("/getResource")
    public JSONResult getResource() {
        Resource bean = new Resource();
        BeanUtils.copyProperties(resource, bean);
        return JSONResult.ok(bean);
    }



}
