package top.xiesen.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.util.Collector;
import top.xiesen.flink.util.WordCountData;

/**
 * @author 谢森
 * @Description Flink Batch Job
 * @className top.xiesen.flink.BatchJob
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/13 14:02 星期五
 */
public class BatchJob {

    public static void main(String[] args) throws Exception {
        /**
         * 检查输入参数
         */
        final ParameterTool params = ParameterTool.fromArgs(args);

        /**
         * 设置执行环境
         */
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        /**
         * 设置参数在 web 页面上可以查看
         */
        env.getConfig().setGlobalJobParameters(params);

        /**
         * 获取输入数据
         */
        DataSet<String> text;
        if (params.has("input")) {
            text = env.readTextFile(params.get("input"));
        } else {
            System.out.println("Executing WordCount example with default input data set.");
            System.out.println("Use --input to specify file input.");
            text = WordCountData.getDefaultTextLineDataSet(env);
        }

        DataSet<Tuple2<String, Integer>> counts =
                text.flatMap(new Tokenizer())
                        .groupBy(0)
                        .sum(1);

        if (params.has("output")) {
            counts.writeAsCsv(params.get("output"), "\n", " ");
            env.execute("WordCount BatchJob");
        } else {
            System.out.println("Printing result to stdout. Use --output to specify output path.");
            counts.print();
        }

    }

    /**
     * Implements the string tokenizer that splits sentences into words as a user-defined
     * FlatMapFunction. The function takes a line (String) and splits it into
     * multiple pairs in the form of "(word,1)" ({@code Tuple2<String, Integer>}).
     */
    public static final class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
            String[] tokens = value.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Tuple2<>(token, 1));
                }
            }
        }
    }
}
