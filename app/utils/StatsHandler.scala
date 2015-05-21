package utils

import models.{RacerStatus, Speed, Station}

import scala.collection.mutable


class StatsHandler {
  var statusOfRacers: mutable.HashMap[Int, RacerStatus] = new mutable.HashMap()

  def initializeRacer(racerId: Int, source: Station, destination: Station, speed: Speed) = {
    val distanceRemaining = StationHandler.getDistanceBetweenStations(source, destination)
    val status = RacerStatus(racerId = racerId, status = "warming up", speed = speed, distanceTravelled = 0,
    distanceRemaining = distanceRemaining, source = source, destination = destination)

    statusOfRacers += (racerId -> status)
    StationHandler.initializeStationStatus(racerId, source, destination)

//    println(s"initialized $racerId with status: $status")
  }

  def moreDistanceRemaining(racerId: Int) = {
    val racerStatus = statusOfRacers.get(racerId).get
    if (racerStatus.distanceRemaining == 0) {
      false
    } else {
      true
    }
  }

  def addDistanceForRacer(racerId: Int, distance: Int) = {
    val racerStatus = statusOfRacers.get(racerId).get
    val updatedStatus = racerStatus.copy(status = "driving", distanceTravelled = racerStatus.distanceTravelled + distance,
    distanceRemaining = racerStatus.distanceRemaining - distance)

    statusOfRacers(racerId) = updatedStatus
    StationHandler.updateStationStatus(racerId, racerStatus)

//    println(s"Racer $racerId drove ${racerStatus.distanceTravelled + distance}")
  }

  def parkedRacer(racerId: Int) = {
    val racerStatus = statusOfRacers.get(racerId).get
    val updatedStatus = racerStatus.copy(status = "parked", distanceRemaining = 0)

    statusOfRacers(racerId) = updatedStatus

    println("parked racer : "+racerId)
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
