package simulations

import config.BaseHelpers._
import io.gatling.core.Predef._
import scenarios.Store_cart.{scnStore_cart}

class PerfTestSimulation extends  Simulation {
  //mvn gatling:test
  //mvn clean gatling:test почистит деректорую куда сохранять отчет
  // mvn clean gatling:test -DBlazeDemoUsers=5    запуск с потоком указыным в консоли
  // mvn clean gatling:test -DBlazeDemoUsers=5 -DCategotiesOnly=3
  //  mvn clean gatling:test -Ddefault.simulation.class=simulations.Regression (из файла pom) если хотим запустить другую симулацию
  //  mvn clean gatling:test -Ddefault.simulation.class=simulations.RecordedSimulation (из файла pom) если хотим запустить другую симулацию
//mvn clean gatling:test -D"gatling.simulationClass=simulations.PerfTestSimulations" (типо название)


  setUp(
    //  scnBlazeDemo.inject(atOnceUsers(1))
    scnStore_cart.inject(atOnceUsers(System.getProperty("StoreUsers","10").toInt)),
    //  scnBlazeDemoCategories.inject(atOnceUsers(System.getProperty("CategoriesOnly", "1").toInt))
  ).protocols(httpProtocol)
}
