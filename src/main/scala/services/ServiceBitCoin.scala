package services

import baseConfig.BodiesFiles
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ServiceBitCoin {

  def generatetoaddress(node: String) = exec(http("generatetoaddress")
    .post(node)
    .header("Authorization","${AUTHORIZATION_NODE}")
    .body(ElFileBody(BodiesFiles.generatetoaddress)).asJson
    .check(status.is(200)))
    .exitHereIfFailed

  def getbalance(node: String) = exec(http("getbalance")
    .post(node)
    .header("Authorization","${AUTHORIZATION_NODE}")
    .body(ElFileBody(BodiesFiles.getbalance)).asJson
    .check(status.is(200))
    .check(jsonPath("$.result").find.saveAs("BALANCE")))
    .exitHereIfFailed

  def sendtoaddress(node: String) = exec(http("sendtoaddress")
    .post(node)
    .header("Authorization","${AUTHORIZATION_NODE}")
    .body(ElFileBody(BodiesFiles.sendtoaddress)).asJson
    .check(status.is(200))
    .check(jsonPath("$.result").find.saveAs("BALANCE")))
    .exitHereIfFailed
}
