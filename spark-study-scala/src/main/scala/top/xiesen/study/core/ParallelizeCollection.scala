package top.xiesen.study.core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 并行化集合创建RDD
  * 案例: 1到10累加求和
  */
object ParallelizeCollection {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("ParallelizeCollection").setMaster("local")
    val sc = new SparkContext(conf)
    val numbers = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbersRDD = sc.parallelize(numbers, 5)
    val sum = numbers.reduce(_ + _)
    println("1--10 sum = : " + sum)

  }
}
