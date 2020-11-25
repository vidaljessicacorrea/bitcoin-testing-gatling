# bitcoin-load-testing-gatling
============================
The following test allow to test

```
  As a bitcoin wallet owner
  I want to be able to transfer any amount of coins from my wallet account
  to any destination wallet account that I do not necessarily own
  So that to prove that I can securely purchase any service or goods by
  paying with BTC coins
  So that to prove that my transaction is secure and cannot be canceled or
  reverted by no one
```

## Setup Environment

We will configure and start a small local blockchain consisting of 2 interconnected bitcoin-core nodes
in our local environment. Once we start our nodes, we will be able to interact with their JSON-RPC API
via HTTP calls from our automated test project.
1. download the Bitcoin-core from https://bitcoin.org/en/download (Make sure to choose the
compressed file format: zip for windows or tar.gz for linux or macOS)

2. unzip / uncompress the content, you'll find a folder named bitcoin-0.X.X (i.e. bitcoin-0.20.1
)
3. Inside bitcoin-0.X.X/bin/ create an empty directory mydata1
4. Inside bitcoin-0.X.X/bin/ create an empty directory mydata2
5. Run inside bitcoin-0.X.X/bin/ the following commands in 2 separate consoles:
Console 1
start Node A:
```./bitcoin-qt -regtest -rpcuser=nodeA -rpcpassword=secretpassword1 -
rpcport=18443 -port=18444 -connect=127.0.0.1:28444 -datadir=./mydata1 -
printtoconsole -rpcallowip=0.0.0.0/0 -listen=1 -server -fallbackfee=0.0002
```
Console 2:
start Node B:
```
./bitcoin-qt -regtest -rpcuser=nodeB -rpcpassword=secretpassword2 -
rpcport=28443 -port=28444 -connect=127.0.0.1:18444 -datadir=./mydata2 -
printtoconsole -rpcallowip=0.0.0.0/0 -listen=1 -server -fallbackfee=0.0002
```

## How to run test

Before start please run 
    mvn clean install
    
Once the project is compiled and download all the libraries you can execute the test for validate one user simulations.SimulationTest
    
    mvn gatling:test -Dgatling.simulationClass=simulations.SimulationTest -Dbitcoin.aut=bitcoin.dev -Dtbitcoin.totalUsers=1 -Dbitcoin.totalTimeMin=1 -DrunMode=debug
    
Once you run the simulation you will find the results on 

    results\bitcoin....
        
If you need to run more than one user you can setup using simulations.SimulationConstantTest
    
    mvn gatling:test -Dgatling.simulationClass=simulations.SimulationConstantTest -Dbitcoin.aut=bitcoin.dev -Dtbitcoin.totalUsers=2 -Dbitcoin.totalTimeMin=2
        
### Maven Command Line options:

|  OPTION | DESCRIPTION  |
|---|---|
|-Dgatling.noReports=true | Runs simulation but does not generate html reports  |
|-Dgatling.reportsOnly="directoryName" | Generates the html report for the simulation log file located in  "directoryName"  |
|-Dgatling.resultsFolder="project.basedir/resultFolder"| Where results are stored|
|-Dgatling.simulationClass="simulationsFolder/SimulationName"| The name of the simulation to be run|
|-Dgatling.runDescription="description"| A short <description> of the run to include in the report|
|-Dbitcoin.aut|Represents the environment where the test is going to be executed. The value is choosen from bitcoin.conf file.
|-Dbitcoin.totalUsers| Total number that are going to be inject
|-Dbitcoin.totalTimeMin|Total time on minutes that the test is going to take
|-DrunMode|If you want to see the complete TRACE of the transactions you need to enable it

