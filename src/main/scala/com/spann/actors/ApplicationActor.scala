package com.spann.actors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.spann.actors.RacerMessages._
import com.spann.models.{Speed, Station}
import com.spann.{StationHandler, StatsHandler}

class ApplicationActor extends Actor with ActorLogging {

  var racerList = scala.collection.mutable.MutableList[ActorRef]()
  var numberOfRacerParked = 0

  override def preStart(): Unit = {
    StationHandler.initializeStations
    val statsHandler = StatsHandler.getInstance

    val racer1 = context.actorOf(Props(new Racer(1, Station.findByName("a").get, Speed(60), Station.findByName("j").get, statsHandler)), "racer1")
    val racer2 = context.actorOf(Props(new Racer(2, Station.findByName("a").get, Speed(120), Station.findByName("j").get, statsHandler)), "racer2")

    racerList += racer1
    racerList += racer2

    racerList.foreach(_ ! Start)

    val reportGenerator = context.actorOf(Props(new ReportGenerator(statsHandler)))
    reportGenerator ! ReportGenerator.Start
  }

  def receive = {
    case stop: String =>
      numberOfRacerParked = numberOfRacerParked + 1
      println("parked Racer"+numberOfRacerParked)
      if (numberOfRacerParked == racerList.length)
        context.stop(self)

  }

}
