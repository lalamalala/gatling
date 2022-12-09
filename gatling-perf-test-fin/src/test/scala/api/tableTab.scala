package api


import config.BaseHelpers.host_name_port
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object tableTab {

  def openTableTab(): ChainBuilder = {
    exec(
      http("/api/v1/products/")
        .get(host_name_port + "/api/v1/products/")

        //как перенести часть queryParam в переменные, хотя может и не надо
        .queryParam("page", "0")
        .queryParam("count", "15")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")
        .queryParam("category", "${category(1)}")

        .check(regex("products"))
        .check(jsonPath("$.products[*].id").findRandom.saveAs("product_id_table"))
        .check(jsonPath("$..[?(@.id==${product_id_table})]..refSku").saveAs("product_refSku_table")))

  }

}
