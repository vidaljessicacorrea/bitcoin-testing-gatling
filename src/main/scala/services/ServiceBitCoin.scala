package services

import baseConfig.{BodiesFiles, CustomLogging}
import chains.ChainBitCoinB.nodeB
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ServiceBitCoin extends CustomLogging{

  def generatetoaddress(node: String,nblocks:Int) = feed(Array(Map("N_BLOCKS"-> nblocks)).circular)
    .exec(http("generatetoaddress")
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
    .check(status.is(200)))
    .exitHereIfFailed

  def getActualBalance(node:String) = exec(session => {
    val actualBalanceNode = session("BALANCE").as[Double]
    session.set("ACTUAL_BALANCE",actualBalanceNode)
  })

  def verifyBalance(node:String) = exec(session => {
    val newBalanceNode = session("BALANCE").as[Double]
    val oldBalanceNode = session("ACTUAL_BALANCE").as[Double]
    val userId = session.userId
    consoleLogger.info(">>> USER ID:%s".format(userId))
    consoleLogger.info(">>> NODE:%s".format(node))
    consoleLogger.info(">>> OLD_BALANCE is %s: ".format(oldBalanceNode))
    consoleLogger.info(">>> NEW_BALANCE is %s: ".format(newBalanceNode))
    if(oldBalanceNode<=newBalanceNode)
      session.markAsFailed
    session
  })

  def getunconfirmedbalance(node: String) = exec(http("getunconfirmedbalance")
    .post(node)
    .header("Authorization","${AUTHORIZATION_NODE}")
    .body(ElFileBody(BodiesFiles.getunconfirmedbalance)).asJson
    .check(status.is(200))
    .check(jsonPath("$.result").find.saveAs("UNCONFIRMED_BALANCE")))
    .exitHereIfFailed

  def validateUnconfirmBalance(node: String) = exec(ServiceNodeB.getunconfirmedbalance(nodeB))
  .exec(session => {
    val unconfirmedbalance = session("UNCONFIRMED_BALANCE").as[Double]
    val userId = session.userId
    consoleLogger.info(">>> USER ID:%s".format(userId))
    consoleLogger.info(">>> NODE:%s".format(node))
    consoleLogger.info(">>> UNCONFIRMED BALANCE:%s".format(unconfirmedbalance))
    session
  })
}
