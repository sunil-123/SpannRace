package com.spann

import com.spann.models.Station

import scala.collection.mutable

class StationHandler {

}

object StationHandler {
  var stationList = mutable.MutableList[Station]()

  def initializeStations = {
    stationList += Station("a", None, 0)
    stationList += Station("b", Station.findByName("a"), 4)
    stationList += Station("c", Station.findByName("b"), 3)
  }

  def getDistanceBetweenStations(a: Station, b: Station): Int = {
    var distance: Int = 0

    val indexOfA = stationList.indexOf(a)
    val indexOfB = stationList.indexOf(b)

    if (indexOfA < indexOfB) {
      // move left
      var currentStation = b
      var previousStation = b.previousStation.get
      while(currentStation != a) {
        distance = distance + currentStation.distanceFromPreviousStation
        currentStation = previousStation
        if(previousStation.previousStation.isDefined)
          previousStation = previousStation.previousStation.get
      }
    } else {
      // move right
      var currentStation = a
      var previousStation = a.previousStation.get
      while(currentStation != b) {
        distance = distance + currentStation.distanceFromPreviousStation
        currentStation = previousStation
        if(previousStation.previousStation.isDefined)
          previousStation = previousStation.previousStation.get
      }
    }

    distance
  }

  def getStationByName(name: String) = {
    var stationFound: Option[Station] = None
    stationList.filter(_.name == name).foreach {
      station =>
        stationFound = Some(station)
    }
    stationFound
  }
}
