package api.common


import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object manufacturers {

  def manufacturers(categoryNumber: String = "${category(1)"): ChainBuilder = {
    exec(
      http(s"/api/v1/category/$categoryNumber/manufacturers/")
        .get(host_name_port + s"/api/v1/category/$categoryNumber/manufacturers/")
        .body(StringBody(commonBody))
        .check(regex("metaDescription")))

  }

}
