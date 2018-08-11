package top.xiesen.study.core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Action 操作
  *
  */
object ActionOperations {

  def main(args: Array[String]): Unit = {
    //    reduce()
    //    collect()
    //    count()
    //    take()
    //    saveAsTestFile()
    countBykey()
  }

  /**
    * reduce案例: 1到10 累加
    */
  def reduce() = {
    val conf = new SparkConf()
      .setAppName("reduce")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers = sc.parallelize(numberList)
    val sum = numbers.reduce(_ + _)
    println("1 + .. + 10 = " + sum)
  }

  /**
    * collect 案例: 将数据拉入到客户端，一般不使用
    */
  def collect() = {
    val conf = new SparkConf()
      .setAppName("collect")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers = sc.parallelize(numberList)
    val doubleNumbers = numbers.map(num => num * 2)
    val numList = doubleNumbers.collect()

    for (num <- numList) {
      println(num)
    }
  }

  def count() = {
    val conf = new SparkConf()
      .setAppName("count")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers = sc.parallelize(numberList)
    val count = numbers.count()
    println("The number of statistical elements is " + count)
  }

  def take() = {
    val conf = new SparkConf()
      .setAppName("take")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers = sc.parallelize(numberList)
    val top3Numbers = numbers.take(3)
    for (num <- top3Numbers) {
      println(num)
    }
  }

  def saveAsTestFile() = {
    val conf = new SparkConf()
      .setAppName("saveAsTestFile")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers = sc.parallelize(numberList)
    val doubleNumbers = numbers.map(num => num * 2)
    doubleNumbers.saveAsTextFile("hdfs://spark1:9000/doubleNumbers")
  }

  def countBykey() = {
    val conf = new SparkConf()
      .setAppName("countBykey")
      .setMaster("local")

    val sc = new SparkContext(conf)
    val studentList = Array(Tuple2("class1", "jack"), Tuple2("class2", "tom"), Tuple2("class1", "tomas"), Tuple2("class2", "marry"), Tuple2("class2", "lina"))
    val students = sc.parallelize(studentList, 1)
    val studentsCount = students.countByKey()
    println(studentsCount)
  }
}
