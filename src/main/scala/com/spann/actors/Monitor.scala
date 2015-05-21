package com.spann.actors

import akka.actor.{Actor, ActorLogging}
import com.spann.StatsHandler
import com.spann.actors.MonitorMessages.{Started, Driving}
import com.spann.models.{Speed, Station}

class Monitor extends Actor with ActorLogging {

  def receive = {
    case m: Started =>
      val stats = StatsHandler.getInstance
      stats.initializeRacer(m.racerId, m.source, m.destination, m.speed)
      sender ! RacerMessages.Initialized

    case e: Driving =>
      e.racerId
  }
}

object MonitorMessages {
  case class Started(racerId: Int, source: Station, destination: Station, speed: Speed)
  case class Parked(racerId: Int)
  case class Driving(racerId: Int)
}
