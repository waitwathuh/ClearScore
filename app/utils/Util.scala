package utils

import models.CreditCardsRequestObject
import models.PartnerARO
import models.PartnerBRO
import models.CreditCardsResponseObject
import play.api.libs.json._
import scalaj.http.Http

import scala.collection.mutable.ListBuffer

object Util {

  def fetchCards(requestObject: CreditCardsRequestObject) = {
    val cardList = new ListBuffer[CreditCardsResponseObject]()

    fetchCSCards( requestObject.toPartnerAJsonString ).foreach(card => cardList += card)
    fetchScoredCards( requestObject.toPartnerBJsonString ).foreach(card => cardList += card)

    val sortedList = cardList.toList.sortBy(_.cardScore)
    Json.parse("[" + sortedList.mkString(",") + "]")
  }

  def fetchCSCards(data: String): ListBuffer[CreditCardsResponseObject] = {
    val cardList = new ListBuffer[CreditCardsResponseObject]()
    val reqA = doRequest("https://app.clearscore.com/api/global/backend-tech-test/v1/cards", data)

    Json.parse(reqA).validate[List[PartnerARO]].fold(
      error => println("error: ", error),
      obj => obj.foreach(card => {
        cardList += new CreditCardsResponseObject(provider="CSCards", name=card.cardName, apr=card.apr, cardScore=card.eligibility)
      })
    )

    cardList
  }

  def fetchScoredCards(data: String): ListBuffer[CreditCardsResponseObject] = {
    val cardList = new ListBuffer[CreditCardsResponseObject]()
    val reqB = doRequest("https://app.clearscore.com/api/global/backend-tech-test/v2/creditcards", data)

    Json.parse(reqB).validate[List[PartnerBRO]].fold(
      error => println("error: ", error),
      obj => obj.foreach(card => {
        cardList += new CreditCardsResponseObject(provider="ScoredCards", name=card.card, apr=card.apr, cardScore=card.approvalRating)
      })
    )

    cardList
  }

  def doRequest(url: String, data: String): String = {
    Http(url)
      .postData(data)
      .header("Content-Type", "application/json")
      .asString
      .body
  }
}
