package top.xiesen.verify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/err/error")
    public String error() {
        int a = 1 / 0;
        return "error";
    }
}
