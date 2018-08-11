package top.xiesen.study.core;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Action 操作实战
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/8/10 21:50.
 */
public class ActionOperations {
    public static void main(String[] args) {
//        reduce();
//        collect();
//        count();
//        take();
//        saveAsTextFile();
        countByKey();
    }


    /**
     * reduce 案例: 1到10 累加
     */
    private static void reduce() {
        // 创建sparkConf和JavaSparkContent
        SparkConf conf = new SparkConf()
                .setAppName("reduce")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        // 有一个集合，里面1到10,10个数字，现在对这10个数字进行累加
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        JavaRDD<Integer> numbers = sc.parallelize(numberList);

        //首先将第一个元素和第二个元素，传入call方法，进行计算，会获得一个结果，比如，1 + 2 = 3
        // 接着将结果与下一个元素传入到call方法中，比如 3 + 3 = 6
        // 依次类推，所以reduce的本质就是聚合，将多个元素聚合成一个元素
        Integer sum = numbers.reduce(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        System.out.println("1 + .. + 10 = " + sum);

        // 关闭JavaSparkContext
        sc.close();
    }

    private static void collect() {
        // 创建sparkConf和JavaSparkContent
        SparkConf conf = new SparkConf()
                .setAppName("reduce")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        // 有一个集合，里面1到10,10个数字，现在将每个元素都乘以2
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        JavaRDD<Integer> numbers = sc.parallelize(numberList);

        // 使用map将每个元素乘以2
        JavaRDD<Integer> mulNumbers = numbers.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer v1) throws Exception {
                return v1 * 2;
            }
        });

        // 不使用foreach action操作，在远程集群上遍历rdd中的每个元素
        // 而使用collect操作，将分布在远程集群上的doubleNumbers rdd的数据拉取到本地
        // 这种方式一般不建议使用，因为如果rdd中的数据量大的时候，比如超过100000条，性能比较差
        // 因为从远程走大量的网络传输，将数据获取到本地，此外，除了性能比较差，还可能在rdd中数据量特别大的情况下，发生oom异常,
        // 内存溢出,因此推荐使用foreach action操作，来对最终的数据进行处理
        List<Integer> mulNumberList = mulNumbers.collect();
        for (Integer num : mulNumberList) {
            System.out.println(num);
        }

        // 关闭JavaSparkContext
        sc.close();
    }


    private static void count() {
        // 创建sparkConf和JavaSparkContent
        SparkConf conf = new SparkConf()
                .setAppName("count")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        // 有一个集合，里面1到10,10个数字，现在对这10个数字进行累加
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        JavaRDD<Integer> numbers = sc.parallelize(numberList);

        // 对RDD进行count操作，统计它有多少个元素
        long count = numbers.count();
        System.out.println("The number of statistical elements is " + count);


        // 关闭JavaSparkContext
        sc.close();
    }

    private static void take() {
        // 创建sparkConf和JavaSparkContent
        SparkConf conf = new SparkConf()
                .setAppName("take")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        // 有一个集合，里面1到10,10个数字，现在对这10个数字进行累加
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        JavaRDD<Integer> numbers = sc.parallelize(numberList);

        // take操作和collect操作类似，也是从远程服务器上获取数据，collect获取所有数据，take只是获取前n个数据
        List<Integer> top3Numbers = numbers.take(3);
        for (Integer num : top3Numbers) {
            System.out.println(num);
        }


        // 关闭JavaSparkContext
        sc.close();
    }

    private static void saveAsTextFile() {
        // 创建sparkConf和JavaSparkContent
        SparkConf conf = new SparkConf()
                .setAppName("saveAsTextFile");

        JavaSparkContext sc = new JavaSparkContext(conf);

        // 有一个集合，里面1到10,10个数字，现在将每个元素都乘以2
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        JavaRDD<Integer> numbers = sc.parallelize(numberList);

        // 使用map将每个元素乘以2
        JavaRDD<Integer> mulNumbers = numbers.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer v1) throws Exception {
                return v1 * 2;
            }
        });

        // 直接将rdd中的文件保存在本地
        // 实际上hdfs上的路径是/double_number.txt/part-00000，我们的路径中设置的就是目录
        mulNumbers.saveAsTextFile("hdfs://spark1:9000/double_number.txt");

        // 关闭JavaSparkContext
        sc.close();
    }

    private static void countByKey() {
        // 创建sparkConf
        SparkConf conf = new SparkConf()
                .setAppName("countByKey")
                .setMaster("local");

        // 创建JavaSparkContext对象
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 构建集合
        List<Tuple2<String, String>> studentList = Arrays.asList(
                new Tuple2<String, String>("class1", "tom"),
                new Tuple2<String, String>("class2", "jack"),
                new Tuple2<String, String>("class1", "marry"),
                new Tuple2<String, String>("class2", "lina"),
                new Tuple2<String, String>("class2", "drive")
        );

        // 并行化集合,创建JavaRDD
        JavaPairRDD<String, String> students = sc.parallelizePairs(studentList);

        // 对RDD进行countByKey操作，统计每个班级的学生人数，也就是统计key对应的元素个数
        Map<String, Object> studentsCount = students.countByKey();

        for (Map.Entry<String, Object> studentCount : studentsCount.entrySet()) {
            System.out.println(studentCount.getKey() + " : " + studentCount.getValue());
        }

        // 关闭JavaSparkContext对象
        sc.close();
    }
}
