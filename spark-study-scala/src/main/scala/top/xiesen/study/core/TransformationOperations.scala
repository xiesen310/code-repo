package top.xiesen.study.core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * transformation操作实战
  *
  */
object TransformationOperations {

  def main(args: Array[String]): Unit = {
    //    map()
    //    filter()
    //    flatMap()
    //    groupByKey()
    //    reduceByKey()
    //    sortByKey()
//    join()
    cogroup()
  }

  /**
    * map 算子案例：将集合中每一个元素都乘以2
    */
  def map() = {
    val conf = new SparkConf()
      .setAppName("map")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val nums = Array(1, 2, 3, 4, 5)
    val numberRDD = sc.parallelize(nums)
    val multipleNumberRDD = numberRDD.map(num => num * 2)

    multipleNumberRDD.foreach(num => println(num))
  }

  /**
    * filter 过滤集合中的偶数
    */
  def filter() = {
    val conf = new SparkConf()
      .setAppName("filter")
      .setMaster("local")

    val sc = new SparkContext(conf)
    val nums = Array(1, 2, 3, 4, 5)
    val numbersRDD = sc.parallelize(nums)
    val filterNumberRDD = numbersRDD.filter(num => num % 2 == 0)
    filterNumberRDD.foreach(num => println(num))

  }

  /**
    * flatMap案例: 将文本行拆分成多个单词
    */
  def flatMap() = {
    val conf = new SparkConf()
      .setAppName("flatMap")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val lineArray = Array("hello you", "hello me", "hello word")
    val lines = sc.parallelize(lineArray, 1)
    val words = lines.flatMap(line => line.split(" "))
    words.foreach(word => println(word))
  }

  /**
    * groupByKey案例：将每个班级的成绩进行分组
    */
  def groupByKey() = {
    val conf = new SparkConf()
      .setAppName("groupByKey")
      .setMaster("local")

    val sc = new SparkContext(conf)
    val scoreList = Array(Tuple2("class1", 80), Tuple2("class2", 75), Tuple2("class1", 90), Tuple2("class2", 65))
    val scores = sc.parallelize(scoreList, 1)
    val groupedScores = scores.groupByKey()
    groupedScores.foreach {
      score =>
        println(score._1);
        score._2.foreach(singleScore => println(singleScore))
        println("=========================================")
    }
  }

  /**
    * reduceByKey案例：求每个班级的总分数
    */
  def reduceByKey() = {
    val conf = new SparkConf()
      .setAppName("reduceByKey")
      .setMaster("local")

    val sc = new SparkContext(conf)
    val scoreList = Array(Tuple2("class1", 80), Tuple2("class2", 75), Tuple2("class1", 90), Tuple2("class2", 65))
    val scores = sc.parallelize(scoreList, 1)
    val totalScores = scores.reduceByKey(_ + _)
    totalScores.foreach(score => println(score._1 + " total scores is " + score._2))
  }

  /**
    * sortByKey案例：按照学生成绩进行排序
    */
  def sortByKey() = {
    val conf = new SparkConf()
      .setAppName("sortByKey")
      .setMaster("local")

    val sc = new SparkContext(conf)
    val scoreList = Array(Tuple2(50, "leo"), Tuple2(100, "tom"), Tuple2(80, "tomas"), Tuple2(84, "jack"))
    val scores = sc.parallelize(scoreList)
    val sortScores = scores.sortByKey(false)
    sortScores.foreach(score => println(score._1 + " " + score._2))
  }

  /**
    * join和cogroup案例: 打印学生成绩
    */
  def join() = {
    val conf = new SparkConf()
      .setAppName("join")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val studentList = Array(
      Tuple2(1, "leo"),
      Tuple2(2, "jack"),
      Tuple2(3, "tom"),
      Tuple2(4, "tomas")
    )

    val scoreList = Array(
      Tuple2(1, 100),
      Tuple2(2, 90),
      Tuple2(3, 60),
      Tuple2(4, 50)
    )

    val students = sc.parallelize(studentList)
    val scores = sc.parallelize(scoreList)
    val studentScores = students.join(scores)
    studentScores.foreach(studentScore => {
      println("student id: " + studentScore._1)
      println("student name: " + studentScore._2._1)
      println("student score: " + studentScore._2._2)
      println("=========================")
    })
  }

  /**
    * join和cogroup案例: 打印学生成绩
    */
  def cogroup() = {
    val conf = new SparkConf()
      .setAppName("cogroup")
      .setMaster("local")

    val sc = new SparkContext(conf)
    val studentList = Array(
      Tuple2(1, "leo"),
      Tuple2(2, "jack"),
      Tuple2(3, "tom"),
      Tuple2(4, "tomas")
    );

    val scoreList = Array(
      Tuple2(1, 100),
      Tuple2(2, 90),
      Tuple2(3, 60),
      Tuple2(4, 50),
      Tuple2(1, 90),
      Tuple2(2, 80),
      Tuple2(3, 70),
      Tuple2(4, 60)
    );

    // 并行化两个RDD
    val students = sc.parallelize(studentList)
    val scores = sc.parallelize(scoreList)

    val studentScores = students.cogroup(scores)

    studentScores.foreach(studentScore => {
      println("student id: " + studentScore._1)
      println("student name: " + studentScore._2._1)
      println("student scores: " + studentScore._2._2)
      println("======================================")
    })
  }
}
