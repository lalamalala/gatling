package api

import config.BaseHelpers.host_name_port
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object chairTab {


  def openChairTab(): ChainBuilder = {
    exec(
      http("/api/v1/products/")
        .get(host_name_port + "/api/v1/products/")
        //как перенести часть queryParam в переменные, хотя может и не надо

        .queryParam("page", "0")
        .queryParam("count", "15")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")
        .queryParam("category", "${category(2)}")

        .check(regex("products"))
        .check(jsonPath("$.products[*].id").findRandom.saveAs("product_id_chairs"))
        .check(jsonPath("$..[?(@.id==${product_id_chairs})]..refSku").saveAs("product_refSku_chairs")))

  }
}
