object Core {

  class OrdinaryCar(override val model: String)
    extends Base.Car {
      override def topSpeedInKmPerHour: Int = 220
      override def topAccelerationInRpm: Int = 8000
    }
  
  class SportsCar(override val model: String)
    extends Base.Car {
      override def topSpeedInKmPerHour: Int = 330
      override def topAccelerationInRpm: Int = 11000
    }

}