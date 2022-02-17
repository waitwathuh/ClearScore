package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.Play.materializer
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

import java.io.FileInputStream

class CreditControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "CreditController POST" should {

    "validate request and return JSON data" in {
      val stream = new FileInputStream("./test/data/creditcards.json")
      val dataAsJson = try {  Json.parse(stream) } finally { stream.close() }

      val controller = new CreditController(stubControllerComponents())
      val creditcards = controller.creditcards().apply(FakeRequest(POST, "/creditcards").withBody(dataAsJson))

      status(creditcards) mustBe OK
      contentType(creditcards) mustBe Some("application/json")
      contentAsString(creditcards) must include ("Welcome to Play")
    }

  }

}
