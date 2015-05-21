package com.spann.actors

import akka.actor.ActorSystem
import akka.testkit.{TestActorRef, ImplicitSender, TestKit}
import com.spann.actors.MonitorMessages.Driving
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class MonitorSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem("MonitorSpec"))

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

//  "Monitor Actor" must {
//    "receive racer's status signal" in {
//      val statsHandler = Stats
//      val monitor = TestActorRef[Monitor](new Monitor)
//      monitor.receive(Driving(1))
//    }
//  }
}
