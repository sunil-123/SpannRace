package com.spann.actors

import akka.actor.{ActorLogging, Actor}
import com.spann.utils.Messages

class Racer extends Actor with ActorLogging {

  def receive = {
    case _ =>
      throw new UnrecognizedMsgException(Messages.UnrecognizedMsg)
  }
}
class UnrecognizedMsgException(msg: String) extends RuntimeException(msg)
