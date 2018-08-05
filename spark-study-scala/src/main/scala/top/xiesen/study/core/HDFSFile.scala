package top.xiesen.study.core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * HDFS 文件创建RDD
  * 案例: 统计文件的字母数量
  *
  */
object HDFSFile {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("HDFSFile")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("hdfs://spark1:9000/spark.txt", 1)
    val count = lines.map { line => line.length }.reduce(_ + _)
    println("file's count is " + count)
  }
}
