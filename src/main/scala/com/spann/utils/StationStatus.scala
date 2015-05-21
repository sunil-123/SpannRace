package com.spann.utils

import com.spann.models.Station

case class StationStatus(station: Station, vehiclesApproaching: scala.collection.mutable.MutableList[Int],
                         vehiclesPassed: scala.collection.mutable.MutableList[Int])
