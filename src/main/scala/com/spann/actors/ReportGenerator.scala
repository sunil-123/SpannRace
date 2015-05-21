package com.spann.actors

import akka.actor.{Actor, ActorLogging}
import com.spann.{StationHandler, StatsHandler}

class ReportGenerator(statsHandler: StatsHandler) extends Actor with ActorLogging {

  def receive = {
    case ReportGenerator.Start =>
      while(true) {
        Thread.sleep(1000)
        printRacerReport(statsHandler)
        printStationReport
        addTableSeparator
      }
  }

  private def printRacerReport(statsHandler: StatsHandler) = {
    println("\n################################## Racers ###################################")
    println("RacerId\tStatus\t\tSpeed\t\tTravelled\tRemaining\tSource\tDestination")
    statsHandler.statusOfRacers.toSeq.sortBy(_._1) foreach {
      case (key, value) =>
        println(s"$key\t\t${value.status}\t\t${value.speed.speed}\t\t${value.distanceTravelled}\t\t\t${value.distanceRemaining}\t\t\t${value.source.name}\t\t${value.destination.name}")
    }
  }

  private def addTableSeparator = println("############################################################################")

  private def printStationReport = {
    println("\n###### Station Report #######")
    println("Name\tApproaching\tPassed")
    StationHandler.stationStatus.toSeq.sortBy(_._1) foreach {
      case (key, value) =>
        println(s"$key\t\t${value.vehiclesApproaching.length}\t\t\t${value.vehiclesPassed.length}")
    }
  }
}

object ReportGenerator {
  object Start
}
