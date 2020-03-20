package top.xiesen.verify.service;

import top.xiesen.verify.pojo.JSONResult;

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
     * @return JSONResult {@link JSONResult}
     */
    JSONResult putLog(String logJson);

    /**
     * 发送 json 数据
     *
     * @return JSONResult {@link JSONResult}
     */
    JSONResult putData(Map<String, String> map);
}
