package services

import baseConfig.BodiesFiles
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ServiceNodeB extends ServiceBitCoin {

  def getnewaddress(node: String) = exec(http("getnewaddress")
    .post(node)
    .header("Authorization","${AUTHORIZATION_NODE_B}")
    .body(ElFileBody(BodiesFiles.getnewaddress)).asJson
    .check(status.is(200))
    .check(jsonPath("$.result").find.saveAs("ADDRESS_ID_NODE_B")))
    .exitHereIfFailed

}
