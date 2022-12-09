package api.oldFiles

import config.BaseHelpers.{host_name, host_name_port}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object T05_go_to_chairs {


  def products(): ChainBuilder = {
    exec(
      http("/api/v1/products/")
        .get(host_name_port + "/api/v1/products/")
        .body(StringBody(
          """{
                                 "count": "15",
                                 "page": "0",
                                 "store": "DEFAULT",
                                 "lang": en,
                                 "category": ${category(2)}
                             } """))
        .check(regex("products"))
        .check(jsonPath("$.products[*].id").findRandom.saveAs("product_id_chairs"))
        .check(jsonPath("$..[?(@.id==${product_id_chairs})]..refSku").saveAs("product_refSku_chairs")))

  }

  def manufacturers(): ChainBuilder = {
    exec(
      http("/api/v1/category/${category(2)}/manufacturers/")
        .get(host_name_port + "/api/v1/category/${category(2)}/manufacturers/")
        .body(StringBody(
          """{
                               "store": "DEFAULT",
                               "lang": en,
                           } """)))
  }


  def chairs_reviews(): ChainBuilder = {
    exec(
      http("/api/v1/products/{product_id_chairs}/reviews")
        .get(host_name_port + "/api/v1/products/${product_id_chairs}/reviews")
        .body(StringBody(
          """{
                                   "store": "DEFAULT"
                               } """)))
  }

  def chairs_all(): ChainBuilder = {
    exec(
      http("/api/v1/products/{product_id_chairs}")
        .get(host_name_port + "/api/v1/products/${product_id_chairs}")
        .body(StringBody(
          """{
                                 "store": "DEFAULT",
                                 "lang": en,
                             } """))
        .check(regex("originalPrice")))
  }


  def chairs_to_cart(): ChainBuilder = {
    exec(
      http("/api/v1/cart")
        .post(host_name_port + "/api/v1/cart")

        .body(StringBody(
          """{
              "attributes":
              [
              {
                "id": ${product_id_chairs}
              }
              ], "product": "${product_refSku_chairs}"
              , "quantity": 1
            } """)).asJson
    )
  }
}
