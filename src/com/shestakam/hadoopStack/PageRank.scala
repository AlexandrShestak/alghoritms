package edu.gatech.cse6242

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object PageRank {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("Task2"))

    // read the file
    val file = sc.textFile("hdfs://localhost:8020" + args(0))

    val links = file.map{ s =>
      val parts = s.split("\\t")
      (parts(0), (parts(1), Integer.parseInt(parts(2))))   // (src, (tgt, weight))
    }.distinct().groupByKey().cache()    // [(src => [(tgt, weight), (tgt, weight), ..]),..]

    val allVertex = file.map { s => s.split("\\t")(0) }.distinct()
      .union(file.map { s => s.split("\\t")(1) }.distinct()).distinct()
    var ranks = allVertex.map(vertex => (vertex -> 1.0))
    val n = allVertex.count()

    val sumOfOutgoingEdges = links.mapValues(v => v.map(_._2).sum)
    val linksAndSumOfOutgoingEdges = links.join(sumOfOutgoingEdges) // [(src => ([(tgt, weight), (tgt, weight), ..], sumOfOutgoingEdgesFromSrc)),..]

    val iters = 50
    var errors:Map[Int, Double] = Map()
    for (i <- 1 to iters) {
      val contribs = linksAndSumOfOutgoingEdges
        .join(ranks)
        .values
        .flatMap { case ((targetsWithWeights, sumOfOutgoingEdgesFromSrc), rank) =>
          targetsWithWeights.map(targetWithWeight => (targetWithWeight._1, rank * targetWithWeight._2 / sumOfOutgoingEdgesFromSrc))
        }

      var newRanks = contribs
        .reduceByKey((x, y) => (x + y))
        .mapValues(rank => 0.85 * rank)

      val gamma = ranks.values.sum - newRanks.values.sum
      newRanks = newRanks.mapValues(rank => rank + gamma / n)

      var errorOnCurrentStep = ranks.join(newRanks)
        .values
        .map { case (previousStepRank, currentStepRank) =>
          Math.abs(previousStepRank - currentStepRank)
        }
        .sum() / n

      println("Iteration # "  + i + ". Error: " + errorOnCurrentStep)
      errors += (i -> errorOnCurrentStep)
      ranks = newRanks

    }

    errors.toSeq.sortBy(_._1).foreach(tup => println("Iteration # "  + tup._1 + ". Error: " + tup._2))

    ranks
      .map(item => item.swap)
      .sortByKey(false)
      .map(item => item.swap)
      .take(50)
      .map(tup => (tup._1, tup._2))
      .map(_.productIterator.mkString("\t"))
      .foreach(println)

    ranks
      .map(tup => (tup._1, tup._2))
      .map(_.productIterator.mkString("\t"))
      .saveAsTextFile("hdfs://localhost:8020" + args(1))

    sc.stop()
  }
}
