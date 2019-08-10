package top.xiesen.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * @Description ReadProperties 读取 Properties 文件工具类
 * @Author 谢森
 * @Date 2019/8/6 15:45
 */
public class ReadProperties {
    public final static Config config = ConfigFactory.load("xiesen.properties");

    public static String getKey(String key) {
        return config.getString(key).trim();
    }

    public static String getKey(String key, String filename) {
        Config config = ConfigFactory.load(filename);
        return config.getString(key).trim();
    }
}
