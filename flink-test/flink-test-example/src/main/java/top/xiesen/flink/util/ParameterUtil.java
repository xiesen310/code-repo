package top.xiesen.flink.util;

import org.apache.flink.api.java.utils.ParameterTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @description: 参数工具类
 * @author: 谢森
 * @Email xiesen@zork.com.cn
 * @time: 2020/1/9 0009 14:15
 */
public class ParameterUtil {
    private static final Logger logger = LoggerFactory.getLogger(ParameterUtil.class);

    /**
     * 配置参数使用 --configPath 来指定配置文件的路径
     *
     * @param args 参数
     * @return
     */
    public static Map<String, String> readParameter(String[] args) {
        Map<String, String> conf = null;
        String configPath;
        try {
            ParameterTool parameterTool = ParameterTool.fromArgs(args);
            configPath = parameterTool.get("configPath");
        } catch (Exception e) {
            configPath = "/etc/flinkConfig.yaml";
        }

        if (!configPath.endsWith("yaml")) {
            System.err.println("Please input correct configuration file and flink run mode!");
            System.exit(-1);
        } else {
            conf = LoadConf.LoadYaml(configPath);
            if (conf == null) {
                logger.error("配置文件" + args[0] + "不存在,系统退出");
                System.exit(-1);
            }
        }
        return conf;
    }
}
