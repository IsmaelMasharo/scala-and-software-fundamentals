object Base {
  abstract class Car {
    def model: String
    def topSpeedInKmPerHour: Int
    def topAccelerationInRpm: Int
    // only educational
    def brand: String = ""

    override def toString: String = {
      val brand = getClass.getName

      brand + " " + model + " " + topSpeedInKmPerHour + " " + topAccelerationInRpm
    }

  }
}