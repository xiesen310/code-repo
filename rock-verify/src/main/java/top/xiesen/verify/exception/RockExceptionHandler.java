package top.xiesen.verify.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class RockExceptionHandler {
    private static final String ROCK_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName(ROCK_ERROR_VIEW);
        return modelAndView;
    }

}
