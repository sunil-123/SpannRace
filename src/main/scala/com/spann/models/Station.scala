package com.spann.models

import com.spann.StationHandler

case class Station(name: String, previousStation: Option[Station], distanceFromPreviousStation: Int)

object Station {
  def findByName(name: String): Option[Station] = {
    StationHandler.getStationByName(name)
  }
}
