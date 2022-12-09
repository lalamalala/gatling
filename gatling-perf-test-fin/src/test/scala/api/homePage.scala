package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object homePage {

  def openHomePage(): ChainBuilder = {
    exec(
      http("/localhost")
        .get("http://" + host_name + "/")
        .header("Accept", headerHomePage)
        .check(regex("Imports from the world")))
  }



  def category(): ChainBuilder = {
    exec(
      http("/api/v1/category/")
        .get(host_name_port + "api/v1/category/")
        .body(ElFileBody("src/test/resources/body/json/categoryBody.json")).asJson
        .check(regex("categories"))
        // вот этот запрос отличие из папки common с категорией
        .check(jsonPath("$.categories[*].id").findAll.saveAs("category"))) //вот эту строку объеденить
  }

}