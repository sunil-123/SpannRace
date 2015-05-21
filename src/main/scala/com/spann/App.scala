package com.spann

import com.spann.actors.ApplicationActor

object App {

  def main(args: Array[String]): Unit = {
    val initialActor = classOf[ApplicationActor].getName
    akka.Main.main(Array(initialActor))
  }
}
