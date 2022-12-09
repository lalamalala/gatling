package api

import config.BaseHelpers.{host_name, host_name_port}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object cartPage {

  def openCartPage(): ChainBuilder = {
    exec(
      http("/api/v1/cart/{id_to_cart}")
        .get(host_name_port + "/api/v1/cart/${id_to_cart}")
        .body(StringBody("""{ "lang": "en" } """))
        .check(regex("quantity")))
  }


}
