package com.knoldus.spark.graphx

import org.apache.spark.SparkContext
import org.apache.spark.graphx.{ Edge, Graph, VertexId }
import org.apache.spark.rdd.RDD

class PropertyGraph(sparkContext: SparkContext) {
  def getGraph(vertices: RDD[(VertexId, (String, String))], edges: RDD[Edge[String]], defaultVertexAttr: (String, String)): Graph[(String, String), String] =
    Graph(vertices, edges, defaultVertexAttr)
}
