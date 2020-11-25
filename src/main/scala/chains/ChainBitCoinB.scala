package chains

import baseConfig.LoadConfiguration
import io.gatling.core.Predef._
import services.ServiceNodeB

object ChainBitCoinB {

  val nodeB = LoadConfiguration.baseUrlNodeB
  var authorization = "Basic bm9kZUI6c2VjcmV0cGFzc3dvcmQy"

  val createNewAddress = feed(Array(Map("AUTHORIZATION_NODE_B"-> authorization)).circular)
    .exec(ServiceNodeB.getnewaddress(nodeB))
    .feed(Array(Map("AUTHORIZATION_NODE"-> authorization)).circular)
    .exec(ServiceNodeB.getbalance(nodeB))
    .exec(ServiceNodeB.getActualBalance(nodeB))

  val verifyTransferReceive = feed(Array(Map("AUTHORIZATION_NODE"-> authorization)).circular)
    .doWhile(session => session("UNCONFIRMED_BALANCE").as[Double] > 0.00000000){
      exec(ServiceNodeB.validateUnconfirmBalance(nodeB))
    }
    .exec(ServiceNodeB.getbalance(nodeB))
  .exec(ServiceNodeB.verifyBalance(nodeB))
}