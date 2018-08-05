package top.xiesen.study.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

import java.util.Arrays;
import java.util.List;

/**
 * 并行化集合创建RDD
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/8/5 15:50.
 */
public class ParallelizeCollection {
    public static void main(String[] args) {
        // 创建sparkConf
        SparkConf conf = new SparkConf()
                .setMaster("local")
                .setAppName("ParallelizeCollection");

        // 创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 要通过并行化创建RDD，那么调用sparkContext以及子类，的parallelize()方法
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        JavaRDD<Integer> numberRDD = sc.parallelize(numbers);

        //执行算子操作
        // 相当于，先进行1 + 2 = 3 ；然后再用3 + 3 = 6；然后再用6 + 4 =10。。。 以此类推
        int sum = numberRDD.reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer num1, Integer num2) throws Exception {
                return num1 + num2;
            }
        });

        // 输出累加的和
        System.out.println("1--10 sum = " + sum);
        // 关闭JavaSparkContext
        sc.close();
    }
}
