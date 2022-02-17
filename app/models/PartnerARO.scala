package models

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

case class PartnerARO(
  cardName: String,
  apr: Double,
  eligibility: Double
)

object PartnerARO {

  implicit val creditControllerRequestObjectReads: Reads[PartnerARO] = (
    (JsPath \ "cardName").read[String] and
      (JsPath \ "apr").read[Double] and
      (JsPath \ "eligibility").read[Double]
    )(PartnerARO.apply _)

}
