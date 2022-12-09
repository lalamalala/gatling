package api.common

import config.BaseHelpers.host_name_port
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object reviews {

  def productReviews(productId: String= "${product_id_table}"): ChainBuilder = {

    exec(
      http(s"/api/v1/products/${productId}/reviews")
        .get(host_name_port + s"/api/v1/products/${productId}/reviews")
        .body(StringBody("""{"store": "DEFAULT"} """)))
  }
}

