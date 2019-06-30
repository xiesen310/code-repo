package com.xiesen.micro.feign;

import com.xiesen.micro.pojo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Allen
 */
@FeignClient("producer-user")
public interface FeignClient01 {

//    @GetMapping("/user/{id}")
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    User getOrder(@PathVariable("id") Long id); // 注意必须加 @PathVariable("id") 注解

    /**
     * 如果传递的是复杂参数，那么 feign 不管你配置的是什么请求方式，都会以 post 方式发出
     * 无法访问，提供者那边必须是 post 方式，这边才可以使用，如果非要以 get 方式传递多个数据，只能以普通方式传递，不能封装成复杂对象
     * @param user
     * @return
     */
    @GetMapping("/get-user")
    User getUser(User user);
}
