package chains

import baseConfig.LoadConfiguration
import io.gatling.core.Predef._
import services.ServiceNodeB

object ChainBitCoinB{

  val nodeB = LoadConfiguration.baseUrlNodeB
  var authorization = "Basic bm9kZUI6c2VjcmV0cGFzc3dvcmQy"

  val createNewAddress = feed(Array(Map("AUTHORIZATION_NODE_B"-> authorization)).circular)
  .exec(ServiceNodeB.getnewaddress(nodeB))
}