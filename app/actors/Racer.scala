package com.spann.actors

import akka.actor.{Actor, ActorLogging, Props}
import com.spann.actors.MonitorMessages.{Driving, Parked}
import com.spann.utils.Messages
import models.{Speed, Station}
import utils.{StationHandler, StatsHandler}

class Racer(id: Int, source: Station, speed: Speed, destination: Station, statsHandler: StatsHandler) extends Actor with ActorLogging {
  val monitor = context.actorOf(Props(new Monitor(statsHandler)), "monitor")

  val totalDistance = StationHandler.getDistanceBetweenStations(source, destination)
  val totalTime = totalDistance / speed.speed
  val timeFor1KmInHour = totalTime / totalDistance
  val timeFor1KmInMillis = timeFor1KmInHour * 3600 * 1000

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
        Thread.sleep(timeFor1KmInMillis.toLong)
      }
      monitor ! Parked(id)
  }

  def getDestination = destination
  def getSource = source
}

object RacerMessages {
  object Start
  object Initialized
}

class UnrecognizedMsgException(msg: String) extends RuntimeException(msg)
