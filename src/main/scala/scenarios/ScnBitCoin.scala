package scenarios

import io.gatling.core.Predef._
import chains.{ChainBitCoinA, ChainBitCoinB}

object ScnBitCoin {

  private val transferBitcoin = exec(ChainBitCoinA.generateBitCoin)
    .exec(ChainBitCoinB.createNewAddress)
    .exec(ChainBitCoinA.sendBitCoin(39.99999991))


  val transferGroup = scenario("Transfer Bitcoins")
    .exec(transferBitcoin)
}
