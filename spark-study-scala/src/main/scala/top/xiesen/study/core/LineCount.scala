package top.xiesen.study.core

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 统计每行出现的次数
  *
  */
object LineCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("LineCount")
      .setMaster("local")

    val sc = new SparkContext(conf)

    val lines = sc.textFile("C:\\Users\\Allen\\Desktop\\hello.txt")

    val pairs = lines.map(line => (line, 1))

    val lineCounts = pairs.reduceByKey(_ + _)

    lineCounts.foreach(lineCount => println(lineCount._1 + " appears " + lineCount._2 + " times."))


  }
}
