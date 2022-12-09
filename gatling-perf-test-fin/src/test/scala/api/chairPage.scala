package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object chairPage {


  def openChairPage(): ChainBuilder = {
    exec(
      http("/api/v1/products/{product_id_chairs}")
        .get(host_name_port + "/api/v1/products/${product_id_chairs}")
        .body(StringBody(commonBody))
        .check(regex("originalPrice")))
  }

}
