package top.xiesen.verify.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.xiesen.verify.kafka.Producer;
import top.xiesen.verify.kafka.ProducerPool;
import top.xiesen.verify.kafka.json.JsonSchema;
import top.xiesen.verify.pojo.JSONResult;
import top.xiesen.verify.pojo.LogEvent;
import top.xiesen.verify.service.LogService;

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

    @Override
    public JSONResult putLog(String logJson) {
//        producer = ProducerPool.getInstance().getProducer();

        try {
            producer.sendLog(logJson);
            return JSONResult.ok();
        } catch (Exception e) {
            return JSONResult.errorMsg(e.getCause().toString());
        }
    }

    @Override
    public JSONResult putData(Map<String, String> map) {

        String jsonData = map.get("data");
        String jsonSchema = map.get("schema");
        boolean flag = JsonSchema.check(jsonSchema, jsonData);

        if (flag) {
            producer.sendLog(jsonData);
            return JSONResult.ok();
        } else {
            return JSONResult.errorMsg("数据与 schema 不一致");
        }
    }

}
