package com.spann.models

case class Speed(speed: Float) {
  def inMeterPerSecond = {
    (speed * 1000 ) / 3600
  }
}
