package com.spann.actors

import akka.actor.{Props, ActorLogging, Actor}
import com.spann.{StatsHandler, StationHandler}
import com.spann.actors.RacerMessages._
import com.spann.models.{Speed, Station}

class ApplicationActor extends Actor with ActorLogging {

  override def preStart(): Unit = {
    StationHandler.initializeStations

    val statsHandler = StatsHandler.getInstance

    val racer1 = context.actorOf(Props(new Racer(1, Station.findByName("a").get, Speed(60), Station.findByName("c").get, statsHandler)), "racer1")
    val racer2 = context.actorOf(Props(new Racer(2, Station.findByName("b").get, Speed(60), Station.findByName("c").get, statsHandler)), "racer2")

    racer1 ! Start
    racer2 ! Start
  }

  def receive = {
    case stop: String => {
      context.stop(self)
    }
  }

}
