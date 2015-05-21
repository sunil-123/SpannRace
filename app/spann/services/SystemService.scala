package spann.services

import com.spann.actors.ApplicationActor

object SystemService {

  def start = {
    val initialActor = classOf[ApplicationActor].getName
    akka.Main.main(Array(initialActor))
  }
}
