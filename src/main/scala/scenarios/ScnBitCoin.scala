package scenarios

import io.gatling.core.Predef._
import chains.{ChainBitCoinA, ChainBitCoinB}

object ScnBitCoin {

  private val transferBitcoin = exec(ChainBitCoinA.generateBitCoin(101))
    .exec(ChainBitCoinB.createNewAddress)
    .exec(ChainBitCoinA.sendBitCoin(39.99999991,1))
    .exec(ChainBitCoinB.verifyTransferReceive)

  val transferGroup = scenario("Transfer Bitcoins")
    .exec(transferBitcoin)
}
