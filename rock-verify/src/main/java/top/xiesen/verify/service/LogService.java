package top.xiesen.verify.service;

import top.xiesen.verify.pojo.JsonResult;

import java.util.Map;

/**
 * @Description
 * @className top.xiesen.verify.service.LogService
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/20 12:19
 */
public interface LogService {
    /**
     * 发送日志数据
     *
     * @param logJson 日志
     * @return JSONResult {@link JsonResult}
     */
    JsonResult putLog(String logJson);

    /**
     * 发送 json 数据
     *
     * @param map
     * @return JSONResult {@link JsonResult}
     */
    JsonResult putData(Map<String, String> map);
}
