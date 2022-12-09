package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object tablePage {

  def openTablePage(): ChainBuilder = {
    exec(
      http("/api/v1/products/${product_id_table}")
        .get(host_name_port + "/api/v1/products/${product_id_table}")
        .body(StringBody(commonBody))
        .check(regex("originalPrice")))
  }

}

