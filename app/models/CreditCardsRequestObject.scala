package models

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

case class CreditCardsRequestObject(
  name: String,
  creditScore: Int,
  salary: Int
) {

  def toPartnerAJsonString: String = {
    "{" +
      "\n" +
      "\t" +
      "\"name\": \"" + name + "\"," +
      "\n" +
      "\t" +
      "\"creditScore\": " + creditScore +
      "\n" +
      "}"
  }

  def toPartnerBJsonString: String = {
    "{" +
      "\n" +
      "\t" +
      "\"name\": \"" + name + "\"," +
      "\n" +
      "\t" +
      "\"score\": " + creditScore + "," +
      "\n" +
      "\t" +
      "\"salary\": " + salary +
      "\n" +
      "}"
  }
}

object CreditCardsRequestObject {

  implicit val creditControllerRequestObjectReads: Reads[CreditCardsRequestObject] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "creditScore").read[Int] and
      (JsPath \ "salary").read[Int]
  )(CreditCardsRequestObject.apply _)

}
