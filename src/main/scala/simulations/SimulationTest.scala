package simulations

import baseConfig.BaseSimulation
import io.gatling.core.Predef._
import scenarios.{ScnBitCoin}

import scala.language.postfixOps

class SimulationTest extends BaseSimulation {

  setUp(

    ScnBitCoin.transferGroup.inject(atOnceUsers(1)

    ).protocols(httpConf))

}
