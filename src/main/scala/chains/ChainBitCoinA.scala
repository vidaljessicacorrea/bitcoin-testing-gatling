package chains

import baseConfig.LoadConfiguration
import io.gatling.core.Predef._
import services.ServiceNodeA

object ChainBitCoinA{

  var nodeA = LoadConfiguration.baseUrlNodeA
  var authorization = "Basic bm9kZUE6c2VjcmV0cGFzc3dvcmQx"

  def generateBitCoin(nblocks:Int) = feed(Array(Map("AUTHORIZATION_NODE_A"-> authorization)).circular)
    .exec(ServiceNodeA.getnewaddress(nodeA))
    .feed(Array(Map("AUTHORIZATION_NODE"-> authorization)).circular)
    .exec(ServiceNodeA.getbalance(nodeA))
    .exec(ServiceNodeA.getActualBalance(nodeA))
    .exec(ServiceNodeA.generatetoaddress(nodeA,nblocks))
    .exec(ServiceNodeA.getbalance(nodeA))
    .exec(ServiceNodeA.verifyBalance(nodeA))

  def sendBitCoin(amount:Double,nblocks:Int) = feed(Array(Map("AMOUNT"-> amount)).circular)
    .feed(Array(Map("AUTHORIZATION_NODE"-> authorization)).circular)
    .exec(ServiceNodeA.sendtoaddress(nodeA))
    .exec(ServiceNodeA.generatetoaddress(nodeA,1))
}