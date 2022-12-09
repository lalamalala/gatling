package api.common

import config.BaseHelpers.{host_name, host_name_port}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object contentPages {

  def contentPages(): ChainBuilder = {
    exec(
      http("/api/v1/content/pages/")
        .get(host_name_port + "api/v1/content/pages/")
        .queryParam("page","0")
        .queryParam("count","20")
        .queryParam("store","DEFAULT")
        .queryParam("lang","en")
        .check(regex("recordsTotal")))
  }

}