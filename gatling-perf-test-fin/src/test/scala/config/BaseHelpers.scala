package config

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

object BaseHelpers {

  val host_name = "localhost"
  val host_name_port = "http://localhost:8080/"
  val httpProtocol = http
    .acceptHeader("application/json, text/plain, */*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:106.0) Gecko/20100101 Firefox/106.0")

  val headerHomePage = "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8"
  val commonBody = """{ "store": "DEFAULT", "lang": en } """

  def thinkTimer(Min : Int = 2, Max: Int = 5): ChainBuilder = {
    pause(Min, Max)
  }

  def commonRequest(): ChainBuilder = {
    exec(api.common.category.category())
      .exec(api.common.contentPages.contentPages())
      .exec(api.common.storeDefault.storeDefault())
      .exec(thinkTimer())
  }


}