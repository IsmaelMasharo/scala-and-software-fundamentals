object Modification {
  trait Spoiler extends Base.Car {
    abstract override def topSpeedInKmPerHour: Int = 
      (super.topSpeedInKmPerHour * 1.02).toInt
  }

  trait EngineControlUnit extends Core.OrdinaryCar {
    override def topSpeedInKmPerHour: Int = 
      (super.topSpeedInKmPerHour * 1.5).toInt
  }

  trait TurboCharger extends Core.OrdinaryCar {
    override def topAccelerationInRpm: Int = 
      (super.topAccelerationInRpm * 1.25).toInt
  }
}