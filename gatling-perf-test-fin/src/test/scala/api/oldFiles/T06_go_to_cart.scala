package api.oldFiles

import config.BaseHelpers.{host_name, host_name_port}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object T06_go_to_cart {

  def open_cart(): ChainBuilder = {
    exec(
      http("/api/v1/cart/{id_to_cart}")
        .get(host_name_port + "/api/v1/cart/${id_to_cart}")
        .body(StringBody(
          """{
                                   "lang": "en"
                               } """))
        .check(regex("quantity")))
  }

  def click_proceed_to_checkout(): ChainBuilder = {
    exec(
      http("/api/v1/cart/{id_to_cart}/total")
        .get(host_name_port + "/api/v1/cart/${id_to_cart}/total")
        .check(regex("total")))
  }


}
