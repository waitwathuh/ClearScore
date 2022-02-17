package utils

import models.CreditCardsRequestObject
import org.scalatestplus.play.PlaySpec
import play.api.libs.json.Json

import java.io.FileInputStream

class UtilSpec extends PlaySpec {

  "Util class" should {

    "fetchCards" in {
      val stream = new FileInputStream("./test/data/creditcards.json")
      val dataAsJson = try {  Json.parse(stream) } finally { stream.close() }
      dataAsJson.validate[CreditCardsRequestObject].fold(
        error => println(error),
        obj => {
          val responseString = Util.fetchCards(obj).toString()
          responseString must include ("ScoredCard")
          responseString must include ("ScoredCard Builder")
          responseString must include ("apr")
          responseString must include ("cardScore")
        }
      )
    }

    "fetchCSCards" in {
      val stream = new FileInputStream("./test/data/creditcards.json")
      val dataAsJson = try {  Json.parse(stream) } finally { stream.close() }
      dataAsJson.validate[CreditCardsRequestObject].fold(
        error => println(error),
        obj => {
          val responseString = Util.fetchCSCards(obj.toPartnerAJsonString).toList.toString()
          responseString must include ("CSCards")
          responseString must include ("SuperSaver")
          responseString must include ("apr")
          responseString must include ("cardScore")
        }
      )
    }

    "fetchScoredCards" in {
      val stream = new FileInputStream("./test/data/creditcards.json")
      val dataAsJson = try {  Json.parse(stream) } finally { stream.close() }
      dataAsJson.validate[CreditCardsRequestObject].fold(
        error => println(error),
        obj => {
          val responseString = Util.fetchScoredCards(obj.toPartnerBJsonString).toList.toString()
          responseString must include ("ScoredCards")
          responseString must include ("ScoredCard")
          responseString must include ("apr")
          responseString must include ("cardScore")
        }
      )
    }

    "doRequest" in {
      val stream = new FileInputStream("./test/data/creditcards.json")
      val dataAsJson = try {  Json.parse(stream) } finally { stream.close() }
      dataAsJson.validate[CreditCardsRequestObject].fold(
        error => println(error),
        obj => {
          val responseString = Util.doRequest("https://app.clearscore.com/api/global/backend-tech-test/v2/creditcards", obj.toPartnerBJsonString)
          responseString must include ("ScoredCard")
          responseString must include ("apr")
          responseString must include ("approvalRating")
        }
      )
    }

  }

}
