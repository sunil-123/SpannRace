package com.spann.actors

import akka.actor.{Props, ActorLogging, Actor}
import com.spann.models.{Speed, Station}

class ApplicationActor extends Actor with ActorLogging {

  override def preStart(): Unit = {
    val racer1 = context.actorOf(Props(new Racer(1, Station("a"), Speed(60), Station("b"))), "racer1")

    racer1 ! RacerMessages.Start
  }

  def receive = {
    case stop: String => {
      context.stop(self)
    }
  }

}
