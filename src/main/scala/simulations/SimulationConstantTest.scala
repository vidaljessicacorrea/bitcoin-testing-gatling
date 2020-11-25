package simulations

import baseConfig.BaseSimulation
import io.gatling.core.Predef._
import scenarios.ScnBitCoin

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class SimulationConstantTest extends BaseSimulation {

  setUp(
    ScnBitCoin.transferGroup.inject(
      constantConcurrentUsers(totalUsers) during (totalTimeMin minutes)
    ).protocols(httpConf)
  )

}
