package top.xiesen.task;

import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import top.xiesen.entity.BrandLike;
import top.xiesen.kafka.KafkaEvent;
import top.xiesen.kafka.KafkaEventSchema;
import top.xiesen.map.BrandLinkMap;
import top.xiesen.reduce.BrandLinkReduce;
import top.xiesen.sink.BrandLinkeSink;

import javax.annotation.Nullable;

/**
 * @Description 终端偏好计算
 * @Author 谢森
 * @Date 2019/8/10 10:46
 */
public class UseTypeTask {
    public static void main(String[] args) {
        args = new String[]{"--input-topic", "scanProductLog", "--bootstrap.servers", "192.168.231.150:9092", "--zookeeper.connect", "192.168.231.150:2181", "--group.id", "youfan"};
        final ParameterTool params = ParameterTool.fromArgs(args);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.getConfig().disableSysoutLogging();
        env.getConfig().setRestartStrategy(RestartStrategies.fixedDelayRestart(4, 10000));
        env.enableCheckpointing(5000);
        env.getConfig().setGlobalJobParameters(params);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        DataStreamSource<KafkaEvent> input = env.addSource(
                new FlinkKafkaConsumer010<>(
                        params.getRequired("--input-topic"),
                        new KafkaEventSchema(),
                        params.getProperties()
                ).assignTimestampsAndWatermarks(new CustomWatermarkExtractor()));

        DataStream<BrandLike> brandLikeMap = input.flatMap(new BrandLinkMap());
        DataStream<BrandLike> brandLikeReduce= brandLikeMap.keyBy("groupFieds").timeWindow(Time.seconds(2)).reduce(new BrandLinkReduce());

        brandLikeReduce.addSink(new BrandLinkeSink());
        try {
            env.execute("BrandLinkTask");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class CustomWatermarkExtractor implements AssignerWithPeriodicWatermarks<KafkaEvent> {

        private static final long serialVersionUID = -742759155861320823L;

        private long currentTimestamp = Long.MIN_VALUE;

        @Override
        public long extractTimestamp(KafkaEvent event, long previousElementTimestamp) {
            // the inputs are assumed to be of format (message,timestamp)
            this.currentTimestamp = event.getTimestamp();
            return event.getTimestamp();
        }

        @Nullable
        @Override
        public Watermark getCurrentWatermark() {
            return new Watermark(currentTimestamp == Long.MIN_VALUE ? Long.MIN_VALUE : currentTimestamp - 1);
        }
    }
}
