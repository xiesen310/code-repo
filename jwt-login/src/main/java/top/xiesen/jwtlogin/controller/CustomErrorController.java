package top.xiesen.jwtlogin.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xiesen.jwtlogin.entity.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 我们要把异常的返回形式也统一了，这样才能方便前端的调用。
 * 我们平常会使用 @RestControllerAdvice 来统一异常，但是它只能管理 Controller 层面抛出的异常。
 * Security 中抛出的异常不会抵达 Controller，无法被 @RestControllerAdvice 捕获，
 * 故我们还要改造 ErrorController
 */
@RestController
public class CustomErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ResponseEntity handleError(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity(response.getStatus(), (String) request.getAttribute("javax.servlet.error.message"), null);
    }

}
