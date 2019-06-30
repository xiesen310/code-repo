package com.xiesen.micro.feign;

import com.xiesen.config.FeignClient02Config;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Allen
 */
@FeignClient(name = "abcdef",url = "http://localhost:10000",configuration = FeignClient02Config.class)
public interface FeignClient02 {
    @RequestMapping("/eureka/apps/{servicename}")
    String getServiceInfo(@PathVariable("servicename") String serviceName);
}
