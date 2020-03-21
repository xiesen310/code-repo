package top.xiesen.verify.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.xiesen.verify.utils.PropertiesUtil;

import java.util.Properties;

/**
 * @Description
 * @className top.xiesen.verify.config.RockConfig
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/21 11:35
 */
public class RockConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RockConfig.class);

    public static Properties getConfig() {
        try {
            Properties properties = PropertiesUtil.getProperties("/rockConfig.properties");
            return properties;
        } catch (Exception e) {
            LOGGER.error("读取配置文件 rockConfig.properties 失败, {}", e.getCause());
            System.exit(-1);
        }
        return null;
    }
}
