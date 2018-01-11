package edu.gatech.cse6242

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object Task2 {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("Task2"))

    // read the file
    val file = sc.textFile("hdfs://localhost:8020" + args(0))

    val sumOfIncomingEdges = file.map(line => {
      val lineSplit = line.split("\\t")
      (lineSplit(1), Integer.parseInt(lineSplit(2)))
    }).reduceByKey(_ + _)
      .map(_.productIterator.mkString("\t"))

    sumOfIncomingEdges.saveAsTextFile("hdfs://localhost:8020" + args(1))
    sc.stop()
  }
}
