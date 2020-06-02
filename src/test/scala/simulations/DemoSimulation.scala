package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DemoSimulation extends Simulation {
  val httpProtocol = http.baseUrl(s"http://www.mocky.io")

  val sentHeaders = Map(
    "content-type" -> "application/json",
    "authorization" -> "Basic 639b36ea-aff3-1b85-618e-c696734afc6e")

  val scnGet = scenario("Exemplo de request get")
    .exec(http("Saudacoes")
      .get("/v2/5ed4e85f3300005100f7a55b")
      .header("Keep-Alive", "150")
    )

  val scnPost = scenario("Exemplo de request post")
    .exec(http("Saudacoes")
      .post("/v2/5ed4e85f3300005100f7a55b")
      .headers(sentHeaders)
      .body(StringBody("""{ "myContent": "myHardCodedValue" }""")).asJson
    )

  setUp(
    scnGet.inject(
      atOnceUsers(1)
    ),
    scnPost.inject(
      atOnceUsers(1)
    )
  ).protocols(httpProtocol)
}