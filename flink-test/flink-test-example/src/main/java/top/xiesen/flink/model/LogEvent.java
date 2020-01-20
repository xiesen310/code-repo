package top.xiesen.flink.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @description: 日志事件
 * @author: 谢森
 * @Email xiesen@zork.com.cn
 * @time: 2020/1/9 0009 14:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogEvent {

    /**
     * logTypeName 日志类型
     */
    private String logTypeName;
    /**
     * timestamp 时间戳
     */
    private String timestamp;
    /**
     * source
     */
    private String source;
    /**
     * offset 偏移量
     */
    private String offset;
    /**
     * dimensions 维度
     */
    private Map<String, String> dimensions;
    /**
     * measures
     */
    private Map<String, Double> measures;
    /**
     * normalFields
     */
    private Map<String, String> normalFields;
}
