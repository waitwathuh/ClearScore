package controllers

import models.CreditCardsRequestObject
import play.api.mvc.{BaseController, ControllerComponents}

import javax.inject.{Inject, Singleton}
import utils.Util

@Singleton
class CreditController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

   def creditcards() = Action(parse.json) { implicit request =>
     request.body.validate[CreditCardsRequestObject].fold(
       error => BadRequest("TODO - Return JsonValidationError: " + error),
       obj => Ok(Util.fetchCards(obj))
     )
  }

}
