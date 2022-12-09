package api.common

import config.BaseHelpers.{host_name, host_name_port}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object category {

  def category(): ChainBuilder = {
    exec(
      http("/api/v1/category/")
        .get(host_name_port + "api/v1/category/")
        .body(ElFileBody("src/test/resources/body/json/categoryBody.json")).asJson
        .check(regex("categories")))
  }


}