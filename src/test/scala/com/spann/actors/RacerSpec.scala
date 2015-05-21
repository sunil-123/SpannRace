package com.spann.actors

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import com.spann.models.{Speed, Station}
import com.spann.utils.Messages
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class RacerSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem("RacerSpec"))

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

//  "Racer Actor" must {
//    val startStation = Station.findByName("a").get
//    val stopStation = Station.findByName("b").get
//    val speed = Speed(60)
//
//    "must be initialized with source, speed and destination" in {
//      val racer = new Racer(1, startStation, speed, stopStation)
//    }
//
//    "throw exception is msg is not recognized" in {
//      val racer = TestActorRef[Racer](new Racer(1, startStation, speed, stopStation))
//      val thrown = intercept[UnrecognizedMsgException] {
//        racer.receive("something")
//      }
//      assert(thrown.getMessage === Messages.UnrecognizedMsg)
//    }
//  }
}
