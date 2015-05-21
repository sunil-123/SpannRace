package com.spann

import akka.actor.ActorRef
import com.spann.actors.Racer
import com.spann.models.Station
import com.spann.utils.{RacerStatus, StationStatus}

import scala.collection.mutable

class StationHandler {

}

object StationHandler {

  var stationList = mutable.MutableList[Station]()
  var stationStatus = mutable.HashMap[String, StationStatus]()

  def initializeStations = {
    stationList += Station("a", None, 0)
    stationList += Station("b", Station.findByName("a"), 3)
    stationList += Station("c", Station.findByName("b"), 4)
    stationList += Station("d", Station.findByName("c"), 2)
    stationList += Station("e", Station.findByName("d"), 5)
    stationList += Station("f", Station.findByName("e"), 7)
    stationList += Station("g", Station.findByName("f"), 2)
    stationList += Station("h", Station.findByName("g"), 4)
    stationList += Station("i", Station.findByName("h"), 3)
    stationList += Station("j", Station.findByName("i"), 10)

    stationList.foreach {
      station =>
        stationStatus(station.name) = StationStatus(station, new mutable.MutableList[Int](), new mutable.MutableList[Int]())
    }
  }

  def initializeStationStatus(racerId: Int, source: Station, destination: Station) = {
    var i = stationList.indexOf(destination) - 1
    var station: Station = stationList.get(i).get
    while (source != station) {
      val presentStatus = stationStatus(station.name)
      stationStatus(station.name) = StationStatus(station, presentStatus.vehiclesApproaching += racerId, new mutable.MutableList[Int]())
      i = i - 1
      station = stationList.get(i).get
    }
    println("stationList status:"+stationStatus)
  }

  def getDistanceBetweenStations(a: Station, b: Station): Int = {
    var distance: Int = 0

    val indexOfA = stationList.indexOf(a)
    val indexOfB = stationList.indexOf(b)

    if (indexOfA < indexOfB) {
      // move left
      var currentStation = b
      var previousStation = b.previousStation.get
      while (currentStation != a) {
        distance = distance + currentStation.distanceFromPreviousStation
        currentStation = previousStation
        if (previousStation.previousStation.isDefined)
          previousStation = previousStation.previousStation.get
      }
    } else {
      // move right
      var currentStation = a
      var previousStation = a.previousStation.get
      while (currentStation != b) {
        distance = distance + currentStation.distanceFromPreviousStation
        currentStation = previousStation
        if (previousStation.previousStation.isDefined)
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

  def updateStationStatus(racerId: Int, racerStatus: RacerStatus) = {
    val previous = getPreviousStationsForRacer(racerStatus.source, racerStatus.distanceTravelled)
    updateStationReport(racerId, previous)
  }

  def updateStationReport(racerId: Int, previousStation: Option[Station]) = {
    if (previousStation.isDefined) {
      // updated previous station data
      val presentStatus = stationStatus(previousStation.get.name)

      if (presentStatus.vehiclesApproaching.contains(racerId)) {
        stationStatus(previousStation.get.name) = StationStatus(previousStation.get, presentStatus.vehiclesApproaching.drop(racerId),
          presentStatus.vehiclesPassed += racerId)

        println("stationStatus: "+stationStatus)
      }
    }
  }

  def getPreviousStationsForRacer(source: Station, distanceTravelled: Int) = {
    val indexOfSource = stationList.indexOf(source)
    var indexOfNextStation = indexOfSource + 1

    var distance = getDistanceBetweenStations(stationList.get(indexOfSource).get, stationList.get(indexOfNextStation).get)
    var previousStation: Option[Station] = None

    while (distance < distanceTravelled) {
      previousStation = stationList.get(indexOfNextStation)
      indexOfNextStation = indexOfNextStation + 1

      distance = distance + previousStation.get.distanceFromPreviousStation
    }

    previousStation
  }
}
