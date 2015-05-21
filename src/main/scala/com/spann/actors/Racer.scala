package com.spann.actors

import akka.actor.{ActorLogging, Actor}
import com.spann.models.{Speed, Station}
import com.spann.utils.Messages

class Racer(source: Station, speed: Speed, destination: Station) extends Actor with ActorLogging {

  def receive = {
    case _ =>
      throw new UnrecognizedMsgException(Messages.UnrecognizedMsg)
  }
}
class UnrecognizedMsgException(msg: String) extends RuntimeException(msg)
