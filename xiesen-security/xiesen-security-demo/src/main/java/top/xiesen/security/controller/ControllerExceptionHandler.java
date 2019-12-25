package top.xiesen.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.xiesen.security.exception.UserNotExistException;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 控制器异常处理器
 * @className top.xiesen.security.controller.ControllerExceptionHandler
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/21 14:07
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotExistException.class)
    public Map<String, Object> handlerUserNotExistException(UserNotExistException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        return result;
    }

}
