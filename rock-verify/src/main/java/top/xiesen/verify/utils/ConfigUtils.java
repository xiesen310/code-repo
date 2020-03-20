package top.xiesen.verify.utils;

/**
 * 配置文件工具类
 * @author 谢森
 */
public class ConfigUtils {

    public static String getString(String value, String defaultValue) {
        String result = value == null || "".equals(value) || "null".equals(value) ? defaultValue : value;
        return result;
    }

    public static Integer getInteger(Integer value, Integer defaultValue) {
        Integer result = value == null ? defaultValue : value;
        return result;
    }

    public static Double getDouble(Double value, Double defaultValue) {
        Double result = value == null ? defaultValue : value;
        return result;
    }

    public static Float getFloat(Float value, Float defaultValue) {
        Float result = value == null ? defaultValue : value;
        return result;
    }

    public static Long getLong(Long value, Long defaultValue) {
        Long result = value == null ? defaultValue : value;
        return result;
    }

    public static Boolean getBoolean(Boolean value, Boolean defaultValue) {
        Boolean result = value == null ? defaultValue : value;
        return result;
    }
}
