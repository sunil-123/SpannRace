package com.spann.actors

import akka.actor.{Actor, ActorLogging}
import com.spann.actors.MonitorMessages.{Driving, Parked, Started}
import models.{Speed, Station}
import utils.StatsHandler

class Monitor(statsHandler: StatsHandler) extends Actor with ActorLogging {

  def receive = {
    case m: Started =>
      statsHandler.initializeRacer(m.racerId, m.source, m.destination, m.speed)
      sender ! RacerMessages.Initialized

    case e: Driving =>
      statsHandler.addDistanceForRacer(e.racerId, 1)

    case e: Parked =>
      statsHandler.parkedRacer(e.racerId)
  }
}

object MonitorMessages {
  case class Started(racerId: Int, source: Station, destination: Station, speed: Speed)
  case class Parked(racerId: Int)
  case class Driving(racerId: Int)
}
