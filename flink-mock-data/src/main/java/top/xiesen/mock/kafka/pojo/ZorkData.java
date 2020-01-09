package top.xiesen.mock.kafka.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
public class ZorkData {
    private String logTypeName;
    private String source;
    private String timestamp;
    private String offset;
    private Map<String, Double> measures;
    private Map<String, String> normalFields;
    private Map<String, String> dimensions;
}
