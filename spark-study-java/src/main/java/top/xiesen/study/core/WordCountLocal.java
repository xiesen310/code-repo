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
 * 本地测试的wordcount程序
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/8/4 10:03.
 */
public class WordCountLocal {

    public static void main(String[] args) {
        // 编写spark应用程序

        // 第一步: 创建sparkConf对象，设置spark应用的配置信息
        // 使用setMaster()可以设置spark应用程序要链接的spark集群的master节点的url;但是设置为local，则代表在本地运行
        SparkConf conf = new SparkConf()
                .setAppName("WordCountLocal")
                .setMaster("local");

        // 创建JavaSparkContext对象
        // SparkContext是spark的入口，作用是包括spark初始化应用程序所需要的一些组件，包含调度器(DAGSchedule、TaskSchedule)，还会去spark Master节点上进行注册等等
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 第三步: 要针对输入源，初始化一个RDD
        // 输入源中的数据会被打散，分配到RDD中的每个partition中，从而形成一个分布式的数据集
        // SparkContext中，用于根据文件类型的输入源创建RDD的方法，叫做textFile()方法
        // 在java中创建的普通RDD，叫做javaRDD
        // 在这里RDD中，有元素的概念，如果是在hdfs或者本地文件，创建的RDD，每个元素相当于文件中的一行
        final JavaRDD<String> lines = sc.textFile("C:\\Users\\Allen\\Desktop\\spark.txt");

        // 第四步: 对初始化的RDD进行transformation操作，也就是一些计算操作
        // 通常会创建Function，并配合RDD的map、flatMap等算子来执行
        // function通常，如果比较简单，则创建Function的匿名内部类；但是如果Function比较复杂，则会单独的创建一个类，作为实现这个function接口的类

        // 先将每一行拆分成单个单词
        // FlatMapFunction 有两个泛型，分别代表输入和输出类型
        // flatMap算子作用： 将RDD的一个元素，给拆分成一个或多个元素
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterable<String> call(String lines) throws Exception {
                return Arrays.asList(lines.split(" "));
            }
        });

        // 接着，需要将每一个单词，映射为(单词,1)的格式，只有这样后面才能根据单词作为key，来进行每个单词的出现次数的累加
        // mapToPair,其实就是将每个元素映射成(v1,v2)这样的Tuple类型的元素
        // mapToPair这个算子，要求是与PairFunction配合使用,第一个参数代表了输入类型，第二个和第三个代表的是输出的Tuple2的第一个值和第二个值
        // JavaPairRDD的两个泛型，分别代表着Tuple2的第一个值和第二个值的类型
        JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<String, Integer>(word, 1);
            }
        });

        // 接着，需要以单词作为key，统计每个单词出现的次数
        // 这里使用reduceBykey算子对每个key对应的value，都进行reduce操作
        // reduce 操作，相当于是把第一个值和第二个值进行计算，然后将结果与第三个值进行计算
        JavaPairRDD<String, Integer> wordCounts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        // 到这里为止，我们已经统计出了单词的次数，但是，之前我们使用的flatMap、mapTopair、reduceByKey，都叫做transformation操作
        // 一个spark应用中，光是有transformation操作，是不行的，是不会执行的，必须有一种叫做action操作
        // 最后，我们使用一种action操作，比如foreach来触发程序的执行
        wordCounts.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> wordCount) throws Exception {
                System.out.println(wordCount._1 + " appeared " + wordCount._2 + " times.");
            }
        });
    }
}
