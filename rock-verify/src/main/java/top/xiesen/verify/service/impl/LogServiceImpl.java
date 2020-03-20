package top.xiesen.verify.service.impl;

import kafka.utils.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xiesen.verify.kafka.Producer;
import top.xiesen.verify.kafka.ProducerPool;
import top.xiesen.verify.kafka.json.JsonSchema;
import top.xiesen.verify.pojo.JsonResult;
import top.xiesen.verify.service.LogService;
import top.xiesen.verify.service.RockLogStructService;

import java.util.Map;

/**
 * @Description
 * @className top.xiesen.verify.service.impl.LogServiceImpl
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/20 12:33
 */
@Service
public class LogServiceImpl implements LogService {
    private Producer producer = ProducerPool.getInstance().getProducer();

    @Autowired
    private RockLogStructService rockLogStructService;

    @Override
    public JsonResult putLog(String logJson) {
        try {
            producer.sendLog(logJson);
            return JsonResult.ok();
        } catch (Exception e) {
            return JsonResult.errorMsg(e.getCause().toString());
        }
    }

    @Override
    public JsonResult putData(Map<String, String> map) {
        String jsonData = map.get("data");
        if (null == jsonData) {
            return JsonResult.errorMsg("data 不能为空");
        }
        String jsonSchema = map.get("schema");

        if (null == jsonSchema) {
            String id = map.get("id");
            String logStruct = rockLogStructService.findLogStructById(id);
            if (null == logStruct) {
                return JsonResult.errorMsg("schema 不存在");
            } else {
                jsonSchema = logStruct;
            }
        }

        boolean flag = JsonSchema.check(jsonSchema, jsonData);

        if (flag) {
            producer.sendLog(jsonData);
            return JsonResult.ok();
        } else {
            return JsonResult.errorMsg("数据与 schema 不一致");
        }
    }

}
