package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DemoSimulation extends Simulation {
  val httpProtocol = http.baseUrl(s"http://www.mocky.io")

  val scn = scenario("Exemplo de request")
    .exec(http("Saudacoes").get("/v2/5ed4e85f3300005100f7a55b"))
  setUp(
    scn.inject(
      atOnceUsers(1)
    )
  ).protocols(httpProtocol)
}