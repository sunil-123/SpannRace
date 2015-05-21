package com.spann

import com.spann.models.{Speed, Station}

class StatsHandler {
  def initializeRacer(racerId: Int, source: Station, destination: Station, speed: Speed) = {
    println("initialized racer")
  }
  def addDistanceForRacer(racerId: Int, distance: Int) = {

  }
  def parkedRacer(racerId: Int) = {

  }
}

object StatsHandler {
  var handler: Option[StatsHandler] = None

  def getInstance = {
    if (handler.isEmpty)
      handler = Some(new StatsHandler)
    handler.get
  }
}
