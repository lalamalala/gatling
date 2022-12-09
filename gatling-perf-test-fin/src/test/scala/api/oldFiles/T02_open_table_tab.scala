package api.oldFiles

import config.BaseHelpers.{host_name, host_name_port}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object T02_open_table_tab {

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
                                 "category": ${category(1)}
                             } """))
        .check(regex("products"))
        .check(jsonPath("$.products[*].id").findRandom.saveAs("product_id_table"))
        .check(jsonPath("$..[?(@.id==${product_id_table})]..refSku").saveAs("product_refSku_table")))

  }

  def manufacturers(): ChainBuilder = {
    exec(
      http("/api/v1/category/${category(1)}/manufacturers/")
        .get(host_name_port + "/api/v1/category/${category(1)}/manufacturers/")
        .body(StringBody(
          """{
                               "store": "DEFAULT",
                               "lang": en,
                           } """)))
  }

}
