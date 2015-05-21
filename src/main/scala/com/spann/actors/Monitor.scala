package com.spann.actors

import akka.actor.{Props, Actor, ActorLogging}
import com.spann.StatsHandler
import com.spann.actors.MonitorMessages.{Parked, Started, Driving}
import com.spann.models.{Speed, Station}

class Monitor(statsHandler: StatsHandler) extends Actor with ActorLogging {
//  val appActor = context.actorOf(Props[ApplicationActor])

  def receive = {
    case m: Started =>
      statsHandler.initializeRacer(m.racerId, m.source, m.destination, m.speed)
      sender ! RacerMessages.Initialized

    case e: Driving =>
      statsHandler.addDistanceForRacer(e.racerId, 1)

    case e: Parked =>
      statsHandler.parkedRacer(e.racerId)
//      appActor ! "stop"
  }
}

object MonitorMessages {
  case class Started(racerId: Int, source: Station, destination: Station, speed: Speed)
  case class Parked(racerId: Int)
  case class Driving(racerId: Int)
}
