package top.xiesen.verify.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.xiesen.verify.pojo.JsonResult;
import top.xiesen.verify.pojo.LogEvent;
import top.xiesen.verify.service.LogService;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Description
 * @className top.xiesen.verify.controller.LogController
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/20 12:38
 */
@RestController
@RequestMapping("/api/v1/log")
@Api("业务操作接口")
public class LogController {

    @Autowired
    private LogService logService;

    @ApiOperation("发送日志")
    @RequestMapping(value = "/putLog", method = RequestMethod.POST)
    public JsonResult putLog(@Valid @RequestBody LogEvent logEvent) {
        return logService.putLog(JSON.toJSONString(logEvent));
    }

    @ApiOperation("发送日志")
    @RequestMapping(value = "/putData", method = RequestMethod.POST)
    public JsonResult putData(@RequestBody Map<String, String> map) {
        return logService.putData(map);
    }

}
