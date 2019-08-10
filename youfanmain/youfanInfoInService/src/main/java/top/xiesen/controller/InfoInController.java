package top.xiesen.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.xiesen.entity.ResultMessage;
import top.xiesen.log.AttentionProductLog;
import top.xiesen.log.BuyCartLog;
import top.xiesen.log.CollectionProductLog;
import top.xiesen.log.ScanProductLog;
import top.xiesen.utils.ReadProperties;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description InfoInController
 * @Author 谢森
 * @Date 2019/8/6 9:53
 */
@RestController
@RequestMapping("infolog")
public class InfoInController {
    private final String attentionProductLogTopic = ReadProperties.getKey("attentionProductLog");
    private final String buyCartLogTopic = ReadProperties.getKey("buyCartLog");
    private final String collectionProductLogTopic = ReadProperties.getKey("collectionProductLog");
    private final String scanProductLogTopic = ReadProperties.getKey("scanProductLog");

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping(value = "helloword", method = RequestMethod.GET)
    public String helloword(HttpServletRequest req) {
        String ip = req.getRemoteAddr();
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMessage("hello: " + ip);
        resultMessage.setStatus("success");
        return JSONObject.toJSONString(resultMessage);
    }

    /**
     * AttentionProductLog: {"productId":"productId"...}
     * BuyCartLog: {"productId":"productId"...}
     * CollectionProductLog: {"productId":"productId"...}
     * ScanProductLog: {"productId":"productId"...}
     *
     * @param receivelog
     * @param req
     * @return
     */
    @RequestMapping(value = "receivelog", method = RequestMethod.POST)
    public String receiveLog(String receivelog, HttpServletRequest req) {
        if (StringUtils.isBlank(receivelog)) {
            return null;
        }

        String[] rearrays = receivelog.split(":", 2);
        String classname = rearrays[0];
        String data = rearrays[1];
        String resultMsg = "";
        if ("AttentionProductLog".equals(classname)) {
            AttentionProductLog attentionProductLog = JSONObject.parseObject(data, AttentionProductLog.class);
            resultMsg = JSONObject.toJSONString(attentionProductLog);
            kafkaTemplate.send(attentionProductLogTopic, resultMsg + "##1##" + new Date().getTime());
        } else if ("BuyCartLog".equals(classname)) {
            BuyCartLog buyCartLog = JSONObject.parseObject(data, BuyCartLog.class);
            resultMsg = JSONObject.toJSONString(buyCartLog);
            kafkaTemplate.send(buyCartLogTopic, resultMsg + "##1##" + new Date().getTime());
        } else if ("CollectionProductLog".equals(classname)) {
            CollectionProductLog collectionProductLog = JSONObject.parseObject(data, CollectionProductLog.class);
            resultMsg = JSONObject.toJSONString(collectionProductLog);
            kafkaTemplate.send(collectionProductLogTopic, resultMsg + "##1##" + new Date().getTime());
        } else if ("ScanProductLog".equals(classname)) {
            ScanProductLog scanProductLog = JSONObject.parseObject(data, ScanProductLog.class);
            resultMsg = JSONObject.toJSONString(scanProductLog);
            kafkaTemplate.send(scanProductLogTopic, resultMsg + "##1##" + new Date().getTime());
        }

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMessage(resultMsg);
        resultMessage.setStatus("success");
        return JSONObject.toJSONString(resultMessage);
    }
}
