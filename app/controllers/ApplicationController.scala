package controllers

import play.api.mvc.{Action, Controller}
import spann.services.SystemService

object ApplicationController extends Controller {
  def index = Action {
    implicit request =>
      Ok("hello")
  }

  def startSystem = Action {
    implicit request =>
      SystemService.start
      Ok(views.html.status())
  }
}
