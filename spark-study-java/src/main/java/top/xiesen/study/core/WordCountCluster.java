package top.xiesen.study.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;

/**
 * 将Java开发的WordCount程序部署到集群环境
 *
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/8/4 10:03.
 */
public class WordCountCluster {

    public static void main(String[] args) {

        // 如果想要在spark集群上运行，只需要修改两个部分
        // 将setMaster删除，默认自己会链接
        // 第二，我们针对的不是本地文件，修改为hdfs上的路径

        /**
         * 实际操作步骤
         * 1. 将spark.txt文件上传到hdfs上
         * 2. 使用maven插件，打包
         * 3. 将spark jar上传到集群环境
         * 4. 编写spark-submit脚本
         */
        SparkConf conf = new SparkConf().setAppName("WordCountCluster").setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        final JavaRDD<String> lines = sc.textFile("hdfs://spark1:9000/spark.txt");

        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterable<String> call(String lines) throws Exception {
                return Arrays.asList(lines.split(" "));
            }
        });

        JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<String, Integer>(word, 1);
            }
        });

        JavaPairRDD<String, Integer> wordCounts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });


        wordCounts.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> wordCount) throws Exception {
                System.out.println(wordCount._1 + " appeared " + wordCount._2 + " times.");
            }
        });
    }
}
