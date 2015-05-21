package com.spann.actors

import akka.actor.{Props, ActorLogging, Actor}
import com.spann.models.{Speed, Station}
import com.spann.utils.Messages

class Racer(id: Int, source: Station, speed: Speed, destination: Station) extends Actor with ActorLogging {
  val monitor = context.actorOf(Props[Monitor], "monitor")

  def receive = {
    case RacerMessages.Start =>
      val startedMsg = MonitorMessages.Started(id, source, destination, speed)
      monitor ! startedMsg
      context.become(readyToRace)

    case _ =>
      throw new UnrecognizedMsgException(Messages.UnrecognizedMsg)
  }

  def readyToRace: Receive = {
    case RacerMessages.Initialized =>
      println("ready to race")
  }
}

object RacerMessages {
  object Start
  object Initialized
}

class UnrecognizedMsgException(msg: String) extends RuntimeException(msg)
