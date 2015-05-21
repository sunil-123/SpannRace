package com.spann.actors

import akka.actor.{Actor, ActorLogging}
import com.spann.actors.MonitorMessages.{Driving, Parked, Started}
import models.{Speed, Station}
import utils.StatsHandler

class Monitor extends Actor with ActorLogging {

  def receive = {
    case m: Started =>
      StatsHandler.initializeRacer(m.racerId, m.source, m.destination, m.speed)
      sender ! RacerMessages.Initialized

    case e: Driving =>
      StatsHandler.addDistanceForRacer(e.racerId, 1)

    case e: Parked =>
      StatsHandler.parkedRacer(e.racerId)
  }
}

object MonitorMessages {
  case class Started(racerId: Int, source: Station, destination: Station, speed: Speed)
  case class Parked(racerId: Int)
  case class Driving(racerId: Int)
}
