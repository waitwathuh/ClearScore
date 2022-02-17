package models

case class CreditCardsResponseObject(
  provider: String,
  name: String,
  apr: Double,
  cardScore: Double
) {

  override def toString() = {
    "{" +
    "\"provider\":\"" + provider + "\"," +
    "\"name\":\"" + name + "\"," +
    "\"apr\":" + apr + "," +
    "\"cardScore\":" + cardScore +
    "}"
  }

}