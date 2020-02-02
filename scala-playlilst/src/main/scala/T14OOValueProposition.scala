object Tutorial14 {
  def main(args: Array[String]): Unit = {
    code(args)
  }

  def code(args: Array[String]): Unit = {
    FnWithNamePrint.p(f1, "f1")
    FnWithNamePrint.p(f2, "f2")
    FnWithNamePrint.p(f3, "f3")
    FnWithNamePrint.p(f4, "f4")
  }

  def f1() = {
    def f(g: Int => String): Unit = {
      println(g(1337))
    }

    def g(number: Int): String = {
      Console.MAGENTA + number.toString.reverse + Console.RESET
    }

    f(g)
  }

  def f2() = {
    class F(
        g: Int => String, 
        h: String => String, 
        i: String => Char
      ) {

      def doStuff(): Unit = {
        var resultOfG: String = g(1337)         // 7331
        var resultOfH: String = h(resultOfG)    // 7331
        var resultOfI: Char = i(resultOfH)      // 7

        println(resultOfI)
      }
    }

    def g(number: Int): String = {
      number.toString.reverse
    }

    def h(string: String): String = {
      string
    }

    def i(string: String): Char = {
      if(string.nonEmpty)
        string(0)
      else
        '?'
    }

    val f = new F(g, h, i)
    f.doStuff()
  }


  def f3() = {

    println(
      """
      |Dependency Inversion:
      |here occurs since EnergyMeter does not need to be changed when adding more devices
      """.stripMargin
    )

    class EnergyMeter(
      wattsPerSecond: Int,
      turnDeviceOn: () => Unit,
      turnDeviceOff: () => Unit
    ) {

      // ALTERNATIVE CONSTRUCTOR
      def this(device: LightBulb) = 
        this(
          wattsPerSecond  = device.wattsPerSecond,
          turnDeviceOn    = () => device.turnOn(),
          turnDeviceOff   = () => device.turnOff(),
        )

      private[this] var turnoOnAtMillis: Long = 0
      private[this] var _wattsConsumedInTotal: Double = 0

      // TAKE A LOOK HERE!!
      // SETTER
      def wattsConsumedInTotal: Double = _wattsConsumedInTotal
      // GETTER
      private[this] def wattsConsumedInTotal_=(newValue: Double): Unit = {
        _wattsConsumedInTotal = newValue
      }
      
      def startMeasuring(): Unit = {
        turnDeviceOn()
        turnoOnAtMillis = System.currentTimeMillis // miliseconds since january first 1970
      }
      
      def stopMeasuring(): Unit = {
        turnDeviceOff()
        
        val durationInMillis = System.currentTimeMillis - turnoOnAtMillis
        val durationInSeconds = durationInMillis.toDouble / 1000
        
        println(s"we turned on tv for $durationInSeconds seconds")
        
        // TAKE A LOOK HERE!! THAT IS CAUSED BY THE METHOD DEFINITION _=
        // PAIR OF METHODS TO AVOID WRITING:
        // _wattsConsumedInTotal += wattsPerSecond*durationInSeconds
        wattsConsumedInTotal += wattsPerSecond*durationInSeconds
      }
    }
    
    // something like a tv

    class TV {
      val wattsPerSecond: Int = 500
      
      def turnOn(): Unit = {
        println("tv on")
      }
      
      def turnOff(): Unit = {
        println("tv off")
      }
    }
    
    class LightBulb {
      val wattsPerSecond: Int = 100
      
      def turnOn(): Unit = {
        println("Light Bulb on")
      }
      
      def turnOff(): Unit = {
        println("Light Bulb off")
      }
    }

    val lightBulb: LightBulb = new LightBulb
    val tv: TV = new TV
    
    val energyMeter = new EnergyMeter(
      wattsPerSecond = tv.wattsPerSecond,
      // we do the eta exapansion ourselves to avoid the warnings
      turnDeviceOn  = () => tv.turnOn(),
      turnDeviceOff = () => tv.turnOff()
    )

    energyMeter.startMeasuring()
    Thread.sleep(1000)
    energyMeter.stopMeasuring()
    println(s"we used ${energyMeter.wattsConsumedInTotal} kW")
    
    println()

    energyMeter.startMeasuring()
    Thread.sleep(1000)
    energyMeter.stopMeasuring()
    println(s"we used ${energyMeter.wattsConsumedInTotal} kW")
  }

  def f4() = {

    println(
      """
      |Dependency Inversion:
      |here occurs since EnergyMeter does not need to be changed when adding more devices
      """.stripMargin
    )

    class EnergyMeter(device: Device) {

      private[this] var turnoOnAtMillis: Long = 0
      private[this] var _wattsConsumedInTotal: Double = 0

      // TAKE A LOOK HERE!!
      // SETTER
      def wattsConsumedInTotal: Double = _wattsConsumedInTotal
      // GETTER
      private[this] def wattsConsumedInTotal_=(newValue: Double): Unit = {
        _wattsConsumedInTotal = newValue
      }
      
      def startMeasuring(): Unit = {
        device.turnOn()
        turnoOnAtMillis = System.currentTimeMillis // miliseconds since january first 1970
      }
      
      def stopMeasuring(): Unit = {
        device.turnOff()
        
        val durationInMillis = System.currentTimeMillis - turnoOnAtMillis
        val durationInSeconds = durationInMillis.toDouble / 1000
        
        println(s"we turned on tv for $durationInSeconds seconds")
        
        // TAKE A LOOK HERE!! THAT IS CAUSED BY THE METHOD DEFINITION _=
        // PAIR OF METHODS TO AVOID WRITING:
        // _wattsConsumedInTotal += wattsPerSecond*durationInSeconds
        wattsConsumedInTotal += device.wattsPerSecond*durationInSeconds
      }
    }

    abstract class Device {
      // this are never called, so why defining it
      def wattsPerSecond: Int
      def turnOn(): Unit
      def turnOff(): Unit
    }

    class TV extends Device {

      println(
        """
        |its not required to have the override keyword if we extends an abstract
        |class but is better to declare it to avoid human errors: turn0ff
        """.stripMargin
      )

      println(
        """
        |Abstract classes and Traits
        |It is only possible to extend one abstract class.
        |It is posible to mixin Traits which are another abstract concept
        """.stripMargin
      )

      override val wattsPerSecond: Int = 500
      
      override def turnOn(): Unit = {
        println("tv on")
      }
      
      override def turnOff(): Unit = {
        println("tv off")
      }
    }
    
    class LightBulb extends Device {
      override val wattsPerSecond: Int = 100
      
      override def turnOn(): Unit = {
        println("Light Bulb on")
      }
      
      override def turnOff(): Unit = {
        println("Light Bulb off")
      }
    }

    println(
      """
      |HERE IS WHERE THE DYNAMIC DISPATCH HAPPENS
      |val lightBulb: Device = new LightBulb
      |ACTUALLY IT HAPPENS WHEN CALLING THE METHODS
      """.stripMargin
    )

    val lightBulb: Device = new LightBulb
    val tv: Device = new TV
    
    val energyMeter = new EnergyMeter(tv)

    energyMeter.startMeasuring()
    Thread.sleep(1000)
    energyMeter.stopMeasuring()
    println(s"we used ${energyMeter.wattsConsumedInTotal} kW")
    
    println()

    energyMeter.startMeasuring()
    Thread.sleep(1000)
    energyMeter.stopMeasuring()
    println(s"we used ${energyMeter.wattsConsumedInTotal} kW")
  }
}