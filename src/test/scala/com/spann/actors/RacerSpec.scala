package com.spann.actors

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import com.spann.utils.Messages
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class RacerSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem("RacerSpec"))

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "Racer Actor" must {
    "throw exception is msg is not recognized" in {
      val racer = TestActorRef[Racer](new Racer)
      val thrown = intercept[UnrecognizedMsgException] {
        racer.receive("something")
      }
      assert(thrown.getMessage === Messages.UnrecognizedMsg)
    }
  }
}
