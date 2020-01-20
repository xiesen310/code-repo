package top.xiesen.kafka;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;
import top.xiesen.test.common.model.MetricEvent;
import top.xiesen.test.common.schemas.MetricSchema;
import top.xiesen.test.common.utils.ExecutionEnvUtil;
import top.xiesen.test.common.utils.KafkaConfigUtil;

/**
 * @description:
 * @author: 谢森
 * @Email xiesen@zork.com.cn
 * @time: 2020/1/16 0016 9:08
 */
public class Main {
    private static final String projectFileName = "/conf/flink-kafka-connector.properties";

    public static void main(String[] args) throws Exception {
        final ParameterTool parameterTool = ExecutionEnvUtil.createParameterTool(args, projectFileName);
        StreamExecutionEnvironment env = ExecutionEnvUtil.prepare(parameterTool);
        DataStreamSource<MetricEvent> data = KafkaConfigUtil.buildSource(env);

        data.addSink(new FlinkKafkaProducer010<MetricEvent>(
                parameterTool.get("kafka.sink.brokers"),
                parameterTool.get("kafka.sink.topic"),
                new MetricSchema()
        )).name("flink-connectors-kafka")
                .setParallelism(Integer.valueOf(parameterTool.get("stream.sink.parallelism").trim()));

        env.execute("flink learning connectors kafka");
    }
}
