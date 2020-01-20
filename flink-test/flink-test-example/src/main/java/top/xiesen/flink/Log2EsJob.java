package top.xiesen.flink;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.xiesen.flink.constants.FlinkConstant;
import top.xiesen.flink.constants.KafkaConstant;
import top.xiesen.flink.model.LogEvent;
import top.xiesen.flink.util.ConfigUtil;
import top.xiesen.flink.util.ParameterUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @description: log2es job
 * @author: 谢森
 * @Email xiesen@zork.com.cn
 * @time: 2020/1/9 0009 14:34
 */
public class Log2EsJob {
    private static final Logger logger = LoggerFactory.getLogger(Log2EsJob.class);
    private static String sourceBootstrapServers = null;
    private static String sourceTopics = null;
    private static String sourceGroupId = null;
    private static String jobName = null;
    private static String kafkaConsumerType;

    private static void initConf(Map conf) {
        sourceBootstrapServers = String.valueOf(conf.get(KafkaConstant.SOURCE_KAFKA_BOOTSTRAP_SERVER)).trim();
        sourceTopics = String.valueOf(conf.get(KafkaConstant.SOURCE_KAFKA_TOPICS)).trim();
        sourceGroupId = String.valueOf(conf.get(KafkaConstant.SOURCE_KAFKA_GROUP_ID)).trim();
        jobName = ConfigUtil.getString(String.valueOf(conf.get(FlinkConstant.FLINK_JOB_NAME)).trim(),
                Log2EsJob.class.getSimpleName().toLowerCase());
        kafkaConsumerType = ConfigUtil.getString(String.valueOf(conf.get(KafkaConstant.KAKFA_CONSUMER_TYPE)),
                "none");
    }

    public static void main(String[] args) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().disableSysoutLogging();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.registerType(LogEvent.class);

        Map<String, String> conf = ParameterUtil.readParameter(args);
        ParameterTool parameterTool = ParameterTool.fromMap(conf);
        env.getConfig().setGlobalJobParameters(parameterTool);
        initConf(conf);

        List<String> inputTopics = Arrays.asList(sourceTopics.split(","));




    }
}
