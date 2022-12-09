package api

import config.BaseHelpers.host_name_port
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object addToCart {
  def addToCart(productId:String = "${product_id_table}", productRefSku: String ="${product_refSku_table}" ): ChainBuilder = {
    exec(
      http("/api/v1/cart")
        .post(host_name_port + "/api/v1/cart")
        .body(StringBody(
          s"""{"attributes":[{ "id": "${productId}"}], "product": "${productRefSku}", "quantity": 1} """)).asJson
        .check(jsonPath("$.code").saveAs("id_to_cart")))
  }


}
