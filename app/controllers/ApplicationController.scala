package controllers

import play.api.mvc.{Action, Controller}

object ApplicationController extends Controller {
  def index = Action {
    implicit request =>
      Ok(views.html.index())
  }
}
