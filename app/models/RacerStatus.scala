package models

case class RacerStatus(racerId: Int, status: String, speed: Speed, distanceTravelled: Int, distanceRemaining: Int,
                       source: Station, destination: Station) extends Ordered[RacerStatus] {
  override def compare(that: RacerStatus) = {
    this.racerId - that.racerId
  }
}
