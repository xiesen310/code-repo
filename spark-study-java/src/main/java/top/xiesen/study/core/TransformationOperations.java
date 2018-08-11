package top.xiesen.study.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;
import org.omg.CORBA.INTERNAL;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * transformation操作实战
 */
public class TransformationOperations {

    public static void main(String[] args) {
//        map();
//        filter();
//        flatMap();
//        groupByKey();
//        reduceByKey();
//        sortByKey();
//        join();
        cogroup();
    }

    /**
     * map 算子案例：将集合中每一个元素都乘以2
     */
    private static void map() {
        // 创建sparkConf
        SparkConf conf = new SparkConf()
                .setAppName("map")
                .setMaster("local");

        // 创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 构造集合
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        // 创建初始化RDD，并行化集合
        JavaRDD<Integer> numberRDD = sc.parallelize(nums);

        // 使用map算子，将集合中的每个元素都乘以2
        // map算子，是对任何类型的RDD，都可以调用的
        // 在java中，map算子接收参数书Function对象
        // 创建的Function对象一定会让你设置第二个泛型类型，这个泛型类型，就是返回新元素的类型
        // 同时call方法的返回值类型必须和第二个参数类型同步
        // 在call内部，就可以对原始RDD中的每个元素进行各种处理和计算，并返回一个新的元素
        // 所有新的元素会组成一个新的RDD
        JavaRDD<Integer> multipleNumberRDD = numberRDD.map(new Function<Integer, Integer>() {
            // 传入的就是1,2,3,4,5,
            // 返回的就是2,4，6,8,10
            @Override
            public Integer call(Integer v1) throws Exception {
                return v1 * 2;
            }
        });


        // 打印新的RDD
        multipleNumberRDD.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer t) throws Exception {
                System.out.println(t);
            }
        });

        // 关闭JavaSparkContext
        sc.close();
    }

    /**
     * filter 过滤集合中的偶数
     */
    private static void filter() {
        SparkConf conf = new SparkConf()
                .setAppName("filter")
                .setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> numbersRDD = sc.parallelize(nums);

        // 如果想要保存RDD，就返回true，不想保留就返回false
        JavaRDD<Integer> filterNumberRDD = numbersRDD.filter(new Function<Integer, Boolean>() {
            @Override
            public Boolean call(Integer num) throws Exception {
                return num % 2 == 0;
            }
        });

        filterNumberRDD.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer num) throws Exception {
                System.out.println(num);
            }
        });
    }

    /**
     * flatMap案例: 将文本行拆分成多个单词
     */
    private static void flatMap() {
        // 创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("flatMap")
                .setMaster("local");

        // 创建JavaSparkContext对象
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 构造集合
        List<String> lineList = Arrays.asList("hello you", "hello me", "hello word");

        // 并行化集合，创建RDD
        JavaRDD<String> lines = sc.parallelize(lineList);

        // 对RDD执行flatMap算子，将每一行文本，拆分成多个单词
        // flatMap在java中，接收的参数是FlatFunction
        // 我们需要自己定义FlatMapFunction第二个泛型参数，即代表了返回的新元素的类型
        // call() 方法返回的不是U，而是Iterable<U>,这里的U是第二个参数的类型
        // flatMap其实就是，接收原始中的每个元素，并进行各种逻辑的计算和处理，返回可以返回多个元素
        // 多个元素，即封装在Iterable集合中，可以使用ArrayList等集合
        // 新的RDD封装了多个元素，也就是说，新的RDD的大小 >= 原始的RDD大小
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterable<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" "));
            }
        });

        // 打印新的RDD
        words.foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                System.out.println(s);
            }
        });
        // 关闭JavaSparkContext
        sc.close();
    }

    /**
     * groupByKey案例：将每个班级的成绩进行分组
     */
    private static void groupByKey() {
        // 创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("groupByKey")
                .setMaster("local");

        // 创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 构造集合
        List<Tuple2<String, Integer>> scoreList = Arrays.asList(
                new Tuple2<String, Integer>("class1", 80),
                new Tuple2<String, Integer>("class2", 75),
                new Tuple2<String, Integer>("class1", 90),
                new Tuple2<String, Integer>("class2", 65)
        );

        // 并行化集合,创建JavaPairRDD
        JavaPairRDD<String, Integer> scores = sc.parallelizePairs(scoreList);

        // 针对scores RDD，执行groupByKey，对每个班级成绩进行分组
        JavaPairRDD<String, Iterable<Integer>> groupedScores = scores.groupByKey();

        // 打印新的RDD
        groupedScores.foreach(new VoidFunction<Tuple2<String, Iterable<Integer>>>() {
            @Override
            public void call(Tuple2<String, Iterable<Integer>> t) throws Exception {
                System.out.println("class: " + t._1);
                Iterator<Integer> ite = t._2.iterator();
                while (ite.hasNext()) {
                    System.out.println(ite.next());
                }
                System.out.println("==================================");
            }
        });


        // 关闭JavaSparkContext
        sc.close();
    }

    /**
     * reduceByKey案例：求每个班级的总分数
     */
    private static void reduceByKey() {
        // 创建sparkConf
        SparkConf conf = new SparkConf()
                .setAppName("reduceBykey")
                .setMaster("local");

        // 创建JavaSparkContext对象
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 构建集合
        List<Tuple2<String, Integer>> scoreList = Arrays.asList(
                new Tuple2<String, Integer>("class1", 80),
                new Tuple2<String, Integer>("class2", 75),
                new Tuple2<String, Integer>("class1", 90),
                new Tuple2<String, Integer>("class2", 65)
        );

        // 并行化集合,创建JavaPairRDD
        JavaPairRDD<String, Integer> scores = sc.parallelizePairs(scoreList);

        // 针对并行化集合,执行reduceByKey算子
        // reduceByKey接收的是Function2类型，它有三个泛型参数实际上代表着三个值
        // 第一个值和第二个值，代表着原始RDD元素的value类型
        // 因此对每一个key进行reduce，都会将第一个和第二个values传入，将值再与第三个值传入
        // 因此，此处会自己创建两个泛型类型，代表call方法的两个传入参数的类型
        // 第三个泛型类型，代表了每次reduce操作返回的值得类型，默认也是与原始RDD类型相同的
        // reduceByKey返回的还是JavaPairRDD<key,values>

        JavaPairRDD<String, Integer> totalScores = scores.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        // 打印新的RDD
        totalScores.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> t) throws Exception {
                System.out.println(t._1 + " total scores is " + t._2);
            }
        });

        // 关闭JavaSparkContext对象
        sc.close();
    }

    /**
     * sortByKey案例：按照学生成绩进行排序
     */
    private static void sortByKey() {
        // 创建sparkConf
        SparkConf conf = new SparkConf()
                .setAppName("reduceBykey")
                .setMaster("local");

        // 创建JavaSparkContext对象
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 构建集合
        List<Tuple2<Integer, String>> scoreList = Arrays.asList(
                new Tuple2<Integer, String>(80, "leo"),
                new Tuple2<Integer, String>(70, "tom"),
                new Tuple2<Integer, String>(90, "tomas"),
                new Tuple2<Integer, String>(86, "jack")
        );

        // 并行化集合,创建JavaPairRDD
        JavaPairRDD<Integer, String> scores = sc.parallelizePairs(scoreList);

        // sortByKey根据key进行排序，可以手动指定升序或者降序，返回的还是JavaPairRDD，其中的元素都是一模一样的
        // 但是，就是顺序不同了。
        final JavaPairRDD<Integer, String> sortScores = scores.sortByKey(false);

        // 打印sortScores
        sortScores.foreach(new VoidFunction<Tuple2<Integer, String>>() {
            @Override
            public void call(Tuple2<Integer, String> t) throws Exception {
                System.out.println(t._1 + " " + t._2);
            }
        });

        // 关闭JavaSparkContext对象
        sc.close();
    }

    /**
     * join和cogroup案例: 打印学生成绩
     */
    private static void join() {
        // 创建sparkConf
        SparkConf conf = new SparkConf()
                .setAppName("joinAndCogroup")
                .setMaster("local");

        // 创建JavaSparkContext对象
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 模拟集合
        List<Tuple2<Integer, String>> studentList = Arrays.asList(
                new Tuple2<Integer, String>(1, "leo"),
                new Tuple2<Integer, String>(2, "jack"),
                new Tuple2<Integer, String>(3, "tom"),
                new Tuple2<Integer, String>(4, "tomas")
        );

        List<Tuple2<Integer, Integer>> scoreList = Arrays.asList(
                new Tuple2<Integer, Integer>(1, 100),
                new Tuple2<Integer, Integer>(2, 90),
                new Tuple2<Integer, Integer>(3, 60),
                new Tuple2<Integer, Integer>(4, 50)
        );

        // 并行化两个RDD
        JavaPairRDD<Integer, String> students = sc.parallelizePairs(studentList);
        JavaPairRDD<Integer, Integer> scores = sc.parallelizePairs(scoreList);

        // 使用join算子关联两个RDD
        // join之后，还是会根据key进行join，并返回javaPairRDD
        // 但是JavaPairRDD的第一个泛型类型是之前两个JavaPairRDD的key的类型，因为是通过key进行join的。
        // 第二个泛型类型，是Tuple<v1,v2>的类型，Tuple2的两个类型分别为原始RDD的类型
        // join，就返回的RDD的每一个元素，就是通过key join上的一个pair
        JavaPairRDD<Integer, Tuple2<String, Integer>> studentScores = students.join(scores);

        // 打印studentScores RDD
        studentScores.foreach(new VoidFunction<Tuple2<Integer, Tuple2<String, Integer>>>() {
            @Override
            public void call(Tuple2<Integer, Tuple2<String, Integer>> t) throws Exception {
                System.out.println("student id: " + t._1);
                System.out.println("student name: " + t._2._1);
                System.out.println("student score: " + t._2._2);
                System.out.println("=========================================");
            }
        });

        // 关闭JavaSparkContext对象
        sc.close();
    }

    /**
     * jion和cogroup案例: 打印学生成绩
     */
    private static void cogroup() {
        // 创建sparkConf
        SparkConf conf = new SparkConf()
                .setAppName("cogroup")
                .setMaster("local");

        // 创建JavaSparkContext对象
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 模拟集合
        List<Tuple2<Integer, String>> studentList = Arrays.asList(
                new Tuple2<Integer, String>(1, "leo"),
                new Tuple2<Integer, String>(2, "jack"),
                new Tuple2<Integer, String>(3, "tom"),
                new Tuple2<Integer, String>(4, "tomas")
        );

        List<Tuple2<Integer, Integer>> scoreList = Arrays.asList(
                new Tuple2<Integer, Integer>(1, 100),
                new Tuple2<Integer, Integer>(2, 90),
                new Tuple2<Integer, Integer>(3, 60),
                new Tuple2<Integer, Integer>(4, 50),
                new Tuple2<Integer, Integer>(1, 90),
                new Tuple2<Integer, Integer>(2, 100),
                new Tuple2<Integer, Integer>(3, 90),
                new Tuple2<Integer, Integer>(4, 50)
        );

        // 并行化两个RDD
        JavaPairRDD<Integer, String> students = sc.parallelizePairs(studentList);
        JavaPairRDD<Integer, Integer> scores = sc.parallelizePairs(scoreList);


        // cogroup与join不同，相当于，一个key join上的所有value，都放在一个Iterable 里面去了
        JavaPairRDD<Integer, Tuple2<Iterable<String>, Iterable<Integer>>> studentScores = students.cogroup(scores);

        // 打印studentScores RDD
        studentScores.foreach(new VoidFunction<Tuple2<Integer, Tuple2<Iterable<String>, Iterable<Integer>>>>() {
            @Override
            public void call(Tuple2<Integer, Tuple2<Iterable<String>, Iterable<Integer>>> t) throws Exception {
                System.out.println("student id: " + t._1);
                System.out.println("student name: " + t._2._1);
                System.out.println("student score: " + t._2._2);
                System.out.println("==========================================");
            }
        });

        // 关闭JavaSparkContext对象
        sc.close();
    }
}
