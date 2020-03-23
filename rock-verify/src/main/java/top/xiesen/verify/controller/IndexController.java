package top.xiesen.verify.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xiesen.verify.pojo.SysUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description
 * @className top.xiesen.verify.controller.IndexController
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/21 14:59
 */
@Controller
public class IndexController {


    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public String ajaxLogin(@RequestParam("userInfo") String userInfo, HttpServletRequest request) {
        SysUser user = JSON.parseObject(userInfo, SysUser.class);
        if ("123".equals(user.getUsername()) && "123".equals(user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("name", user.getUsername());
            return "success";
        } else {
            return "fail";
        }
    }
}
