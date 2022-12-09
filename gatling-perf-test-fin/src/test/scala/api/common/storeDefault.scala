package api.common

import config.BaseHelpers.{host_name, host_name_port}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object storeDefault {

  def storeDefault(): ChainBuilder = {
    exec(
      http("/api/v1/store/DEFAULT")
        .get(host_name_port + "api/v1/store/DEFAULT")
        .check(regex("Default store")))
  }

}