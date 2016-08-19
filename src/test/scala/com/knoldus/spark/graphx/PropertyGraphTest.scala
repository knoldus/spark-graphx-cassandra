package com.knoldus.spark.graphx

import org.apache.spark.graphx.{ Edge, VertexId }
import org.apache.spark.rdd.RDD
import org.apache.spark.{ SparkConf, SparkContext }
import org.scalatest.FunSuite

object TestData {
  val sparkContext = new SparkContext(new SparkConf().setMaster("local").setAppName("test"))
}

class PropertyGraphTest extends FunSuite {
  import com.knoldus.spark.graphx.TestData.sparkContext

  val propertyGraph = new PropertyGraph(sparkContext)

  test("property graph returns graph") {
    val users: RDD[(VertexId, (String, String))] =
      sparkContext.parallelize(Array((3L, ("rxin", "student")), (7L, ("jgonzal", "postdoc")), (5L, ("franklin", "prof")), (2L, ("istoica", "prof"))))
    val relationships: RDD[Edge[String]] =
      sparkContext.parallelize(Array(Edge(3L, 7L, "collab"), Edge(5L, 3L, "advisor"), Edge(2L, 5L, "colleague"), Edge(5L, 7L, "pi")))
    val defaultUser = ("John Doe", "Missing")
    val graph = propertyGraph.getGraph(users, relationships, defaultUser)
    assert(graph.edges.count() === 4)
  }
}
