package api

import config.BaseHelpers.{host_name, host_name_port}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object proceedCheckout {

  def clickProceedCheckout(): ChainBuilder = {
    exec(
      http("/api/v1/cart/{id_to_cart}/total")
        .get(host_name_port + "/api/v1/cart/${id_to_cart}/total")
        .check(regex("total")))
  }


}
