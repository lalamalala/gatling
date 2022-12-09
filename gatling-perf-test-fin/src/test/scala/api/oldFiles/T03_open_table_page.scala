package api.oldFiles

import config.BaseHelpers.{host_name, host_name_port}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object T03_open_table_page {

  def table_reviews(): ChainBuilder = {
    exec(
      http("/api/v1/products/${product_id_table}/reviews")
        .get(host_name_port + "/api/v1/products/${product_id_table}/reviews")
        .body(StringBody(
          """{
                                   "store": "DEFAULT"
                               } """)))
  }

  def table_all(): ChainBuilder = {
    exec(
      http("/api/v1/products/${product_id_table}")
        .get(host_name_port + "/api/v1/products/${product_id_table}")
        .body(StringBody(
          """{
                                 "store": "DEFAULT",
                                 "lang": en,
                             } """))
        .check(regex("originalPrice")))
  }

}
