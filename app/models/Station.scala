package models

import utils.StationHandler

case class Station(name: String, previousStation: Option[Station], distanceFromPreviousStation: Int)

object Station {
  def findByName(name: String): Option[Station] = {
    StationHandler.getStationByName(name)
  }
}
