package api.oldFiles

import config.BaseHelpers.{host_name, host_name_port}
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object T01_open_site {

  def localhost(): ChainBuilder = {
    exec(
      http("/localhost")
        .get("http://" + host_name + "/")
        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
        .check(regex("<!doctype html><html")))
  }

  def category(): ChainBuilder = {
    exec(
      http("/api/v1/category/")
        .get(host_name_port + "api/v1/category/")
        .body(StringBody(
          """{
                               "count": "20",
                               "page": "0",
                               "store": "DEFAULT",
                               "lang": en
                           } """))
        .check(regex("categories"))
        .check(jsonPath("$.categories[*].id").findAll.saveAs("category")))
  }


  def FEATURED_ITEM(): ChainBuilder = {
    exec(
      http("/api/v1/products/group/FEATURED_ITEM")
        .get(host_name_port + "api/v1/products/group/FEATURED_ITEM")
        .body(StringBody(
          """{
                               "count": "20",
                               "page": "0",
                               "store": "DEFAULT",
                               "lang": en
                           } """))
        .check(regex("products")))
  }

  def content_pages(): ChainBuilder = {
    exec(
      http("/api/v1/content/pages/")
        .get(host_name_port + "api/v1/content/pages/?page=0&count=20&store=DEFAULT&lang=en")
        .check(regex("recordsTotal")))
  }


  def store_DEFAULT(): ChainBuilder = {
    exec(
      http("/api/v1/store/DEFAULT")
        .get(host_name_port + "api/v1/store/DEFAULT")
        .check(regex("\"code\":\"DEFAULT\"")))
  }

}
