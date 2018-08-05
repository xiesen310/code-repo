package top.xiesen.study.core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 本地文件创建RDD
  * 案例: 统计文件的字母数量
  *
  */
object LocalFile {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("LocalFile").setMaster("local")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("C:\\Users\\Allen\\Desktop\\spark.txt", 1)
    val count = lines.map { line => line.length }.reduce(_ + _)
    println("file's count is " + count)
  }
}
