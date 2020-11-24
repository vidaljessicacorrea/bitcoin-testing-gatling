package baseConfig

import com.typesafe.config.ConfigFactory


object LoadConfiguration extends CustomLogging{

  private val bitcoinConfig = ConfigFactory.load("bitcoin")
  private val aut = bitcoinConfig.getString("bitcoin.aut")

  val baseUrlNodeA = "http://" + bitcoinConfig.getString("%s.NodeA.rpcuser".format(aut)) + ":" + bitcoinConfig.getString("%s.NodeA.rpcpassword".format(aut)) + "@" + bitcoinConfig.getString("%s.NodeA.host".format(aut)) + ":" + bitcoinConfig.getString("%s.NodeA.rpcport".format(aut)) + "/"
  val baseUrlNodeB = "http://" + bitcoinConfig.getString("%s.NodeB.rpcuser".format(aut)) + ":" + bitcoinConfig.getString("%s.NodeB.rpcpassword".format(aut)) + "@" + bitcoinConfig.getString("%s.NodeB.host".format(aut)) + ":" + bitcoinConfig.getString("%s.NodeB.rpcport".format(aut)) + "/"

  val totalUsers = bitcoinConfig.getString("bitcoin.totalUsers").toInt
  val totalTimeMin = bitcoinConfig.getString("bitcoin.totalTimeMin").toInt

  val totalTimeSec = (totalTimeMin * 60).toDouble
  val usersPerSec = BigDecimal(totalUsers / totalTimeSec).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

  val gatlingResourceFolder = "src/test/resources/data/"
  val gatlingBodiesFolder = "src/test/resources/bodies/"


  consoleLogger.info("############################################")
  consoleLogger.info("# Web Simulation Parameters ")
  consoleLogger.info("# Total Users: %s".format(totalUsers))
  consoleLogger.info("# Total Time: %s min".format(totalTimeMin))
  consoleLogger.info("# Total Time: %s sec".format(totalTimeSec))
  consoleLogger.info("# Users per sec: %s".format(usersPerSec))
  consoleLogger.info("############################################")

}
