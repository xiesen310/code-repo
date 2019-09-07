package top.xiesen.simulation.zork.pojo;

import java.util.Map;

/**
 * zork数据格式
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2019/2/24 15:33.
 */
public class ZorkData {
    private String logTypeName;
    private String timestamp;
    private String source;
    private String offset;
    private Map<String,String> dimensions;
    private Map<String,Double> measures;
    private Map<String,String> normalFields;

    public String getLogTypeName() {
        return logTypeName;
    }

    public void setLogTypeName(String logTypeName) {
        this.logTypeName = logTypeName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public Map<String, String> getDimensions() {
        return dimensions;
    }

    public void setDimensions(Map<String, String> dimensions) {
        this.dimensions = dimensions;
    }

    public Map<String, Double> getMeasures() {
        return measures;
    }

    public void setMeasures(Map<String, Double> measures) {
        this.measures = measures;
    }

    public Map<String, String> getNormalFields() {
        return normalFields;
    }

    public void setNormalFields(Map<String, String> normalFields) {
        this.normalFields = normalFields;
    }

    @Override
    public String toString() {
        return "{" +
                "logTypeName='" + logTypeName + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", source='" + source + '\'' +
                ", offset='" + offset + '\'' +
                ", dimensions=" + dimensions +
                ", measures=" + measures +
                ", normalFields=" + normalFields +
                '}';
    }
}
