package api.oldFiles

import config.BaseHelpers.host_name_port
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object T04_add_table_to_cart {
  def table_to_cart(): ChainBuilder = {
    exec(
      http("/api/v1/cart")
        .post(host_name_port + "/api/v1/cart")

        .body(StringBody(
          """{
              "attributes":
              [
              {
                "id": ${product_id_table}
              }
              ], "product": "${product_refSku_table}"
              , "quantity": 1
            } """)).asJson
        .check(jsonPath("$.code").saveAs("id_to_cart")))
  }


}
