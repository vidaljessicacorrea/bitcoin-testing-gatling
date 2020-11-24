# bitcoin-load-testing-gatling
============================

Here you will find some examples of how to use gatling. If you have any question we are following the documentation of [Gatling](https://gatling.io/docs/current/)

## How to run test

Before start please run 
    mvn clean install
    
Once the project is compiled and download all the libraries you can execute the test
    
    mvn gatling:test -Dgatling.simulationClass=simulations.SimulationTest -Dbitcoin.aut=bitcoin.dev -Dtbitcoin.totalUsers=1 -Dbitcoin.totalTimeMin=1
    
Once you run the simulation you will find the results on 

    results\bitcoin....
        
This script will connect with the machines using ssh protocol and once you finished the reports is going to be copy to your local machine and open it. 
    
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
