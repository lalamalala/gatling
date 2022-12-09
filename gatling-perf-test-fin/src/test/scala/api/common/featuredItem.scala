package api.common

import config.BaseHelpers.{host_name, host_name_port}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object featuredItem {

  def featuredItem(): ChainBuilder = {
    exec(
      http("/api/v1/products/group/FEATURED_ITEM")
        .get(host_name_port + "api/v1/products/group/FEATURED_ITEM")
        .body(ElFileBody("src/test/resources/body/json/categoryBody.json")).asJson
        .check(regex("products")))
  }

}