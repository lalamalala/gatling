package scenarios

import config.BaseHelpers.thinkTimer
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._

object Store_cart {

  def scnStore_cart: ScenarioBuilder = {
    scenario("Store home page ")
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exitBlockOnFail(
        //уточнить по наймингу объекта и функции, нормально что api.common.contentPages.contentPages()) повторяют себя

        group("Home page") {
          exec(api.homePage.openHomePage())
            // как category объеденить или дополнить такой же запрос в папке common
            // но тут еще добавляется check(jsonPath( с сохранением переменной
            .exec(api.homePage.category())
            .exec(api.common.featuredItem.featuredItem())
            .exec(api.common.contentPages.contentPages())
            .exec(api.common.storeDefault.storeDefault())
            .exec(thinkTimer())
        }
          //спросить про json-body если с переменной, выносить в json файл или в basehelper или так оставить
          // openTableTab как перенести часть queryParam в переменные

          .group("Open table tab") {
            exec(api.tableTab.openTableTab())
              .exec(api.common.manufacturers.manufacturers("${category(1)}"))
              .exec(config.BaseHelpers.commonRequest())
          }

          .group("Open table page") {
            exec(api.tablePage.openTablePage())
              .exec(api.common.reviews.productReviews("${product_id_table}"))
              .exec(config.BaseHelpers.commonRequest())
          }

          .group("Add table to cart") {
            exec(api.addToCart.addToCart("${product_id_table}", "${product_refSku_table}"))
              .exec(config.BaseHelpers.commonRequest())
          }
          //
          .randomSwitch(50.0 ->
            group("Go to chairs") {
              //открываем вкладку стульев
              exec(api.chairTab.openChairTab())
                .exec(api.common.manufacturers.manufacturers("${category(2)}"))
                .exec(config.BaseHelpers.commonRequest())

                // открываем любой стул
                .exec(api.chairPage.openChairPage())
                .exec(api.common.reviews.productReviews("${product_id_chairs}"))
                .exec(config.BaseHelpers.commonRequest())

                //добавляем стул в карзину
                .exec(api.addToCart.addToCart("${product_id_chairs}", "${product_refSku_chairs}"))
                .exec(config.BaseHelpers.commonRequest())

            }
          )

          .randomSwitch(30.0 ->
            group("Go to cart") {
              //открываем корзину
              exec(api.cartPage.openCartPage())
                .exec(config.BaseHelpers.commonRequest())
                //нажимаем оплатить
                .exec(api.proceedCheckout.clickProceedCheckout())
                .exec(config.BaseHelpers.commonRequest())

            }
          )


      )
  }


}

//как перенести и использовать в постоянные check/body и остальное

//по настройке на yaml есть инструкция, или просто знать и потом искать?

//почему так завершается тест, в логах написано
//22:40:15.050 [GatlingSystem-akka.actor.default-dispatcher-7] ERROR i.g.c.stats.writer.ConsoleDataWriter - Internal error, scenario 'Store home page' has not been correctly initialized