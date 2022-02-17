package models

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.{JsPath, Reads}

case class PartnerBRO(
  card: String,
  apr: Double,
  approvalRating: Double
)

object PartnerBRO {

  implicit val creditControllerRequestObjectReads: Reads[PartnerBRO] = (
    (JsPath \ "card").read[String] and
      (JsPath \ "apr").read[Double] and
      (JsPath \ "approvalRating").read[Double]
    )(PartnerBRO.apply _)

}
