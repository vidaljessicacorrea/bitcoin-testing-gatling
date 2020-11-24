package baseConfig

import io.gatling.core.Predef._
import io.gatling.http.Predef.http

class BaseSimulation extends Simulation{


  val nodeA = LoadConfiguration.baseUrlNodeA
  val nodeB = LoadConfiguration.baseUrlNodeB

  val usersPerSec = LoadConfiguration.usersPerSec
  val totalUsers = LoadConfiguration.totalUsers
  val totalTimeMin = LoadConfiguration.totalTimeMin

  val httpConf = http
    .baseUrl(nodeA)
    .warmUp(nodeA)
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("curl/7.64.1")
    .contentTypeHeader("application/json")
    .connectionHeader("keep-alive")

}
