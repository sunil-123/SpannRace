package com.spann.actors

import akka.actor.{Props, ActorLogging, Actor}
import com.spann.StatsHandler
import com.spann.actors.MonitorMessages.{Parked, Driving}
import com.spann.models.{Speed, Station}
import com.spann.utils.Messages

class Racer(id: Int, source: Station, speed: Speed, destination: Station, statsHandler: StatsHandler) extends Actor with ActorLogging {
  val monitor = context.actorOf(Props(new Monitor(statsHandler)), "monitor")

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
      while(statsHandler.moreDistanceRemaining(id)) {
        monitor ! Driving(id)
        Thread.sleep(1000)
      }
      monitor ! Parked(id)
  }
}

object RacerMessages {
  object Start
  object Initialized
}

class UnrecognizedMsgException(msg: String) extends RuntimeException(msg)
