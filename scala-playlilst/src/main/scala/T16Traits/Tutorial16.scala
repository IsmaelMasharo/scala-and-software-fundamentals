object Tutorial16 {
  def main(args: Array[String]): Unit = {
    code(args)
  }

  def code(args: Array[String]): Unit = {
    FnWithNamePrint.p(f1, "f1")
    FnWithNamePrint.p(f2, "f2")
    FnWithNamePrint.p(f3, "f3")
    FnWithNamePrint.p(f4, "f4")
    FnWithNamePrint.p(f5, "f5")
  }

  def f1(): Unit = {
      trait Pet {
          def allowToSleepInBed: Boolean
          def disallowToSleepInBed: Boolean = 
            !allowToSleepInBed
      }

      class Cat extends Pet {
          val allowToSleepInBed: Boolean = true
      }

      class Turtle extends Pet {
          val allowToSleepInBed: Boolean = false
      }

      def show(pet: Pet): Unit = {
          println(pet.allowToSleepInBed)
      }

      show(new Cat)
      show(new Turtle)

  }

  def f2(): Unit = {

      trait Timestamp {
          val creationTime: String = {
              val formatter = 
                java.time.format
                    .DateTimeFormatter
                    .ofPattern("HH:mm:ss")
              
              java.time
                .LocalDateTime
                .now
                .format(formatter)
          }

      }

      class FileWithTimestamp(path: String) extends java.io.File(path) with Timestamp

      val path = "/home/ismael/Documents/my-repos/scala-and-software-fundamentals/scala-playlilst/scr/main/scala/T16Traits/Tutorial16.scala"
      val file = new FileWithTimestamp(path)

      println(file.getName)
      println(file.creationTime)
  }

  trait Timestamp {
      val creationTime: String = {
          val formatter = 
            java.time.format
                .DateTimeFormatter
                .ofPattern("HH:mm:ss")
          
          java.time
            .LocalDateTime
            .now
            .format(formatter)
      }

  }

  def f3(): Unit = {

      val path = "/home/ismael/Documents/my-repos/scala-and-software-fundamentals/scala-playlilst/scr/main/scala/T16Traits/Tutorial16.scala"
      
      type LazyToType = java.io.File with Timestamp
      val file: LazyToType = new java.io.File(path) with Timestamp

      def showName(file: java.io.File): Unit = {
        println(file.getName)
      }

      def showCreationTime(timestamp: Timestamp): Unit = {
        println(timestamp.creationTime)
      }

      showName(file)
      showCreationTime(file)
  }

  def f4(): Unit = {
    println(
      """
      |Finale classes
      |it is not posible to extend them
      |this will fail:
      |val string = new String("hello") with Timestamp
      """.stripMargin
    )
  }

  def f5(): Unit = {
    final class Lamborgini(override val model: String) 
      extends Core.SportsCar(model)
      with Modification.Spoiler {
        override def brand: String = "Lamborgini"
      }

    final class BMW(override val model: String) 
      extends Core.OrdinaryCar(model)
      with Modification.Spoiler
      with Modification.EngineControlUnit
      with Modification.TurboCharger {
        override def brand: String = "BMW"
      }

    println(new Lamborgini("Murcielago"))
    println()

  }
}

// traits: caracteristics

// different aspects of classes can be define in independent traits, this allows modular design

// thin trait: abstratcs

// rich trait: methods implemented

// useful for: add, modifiy, intercept functionality

// it is not possible to call super on val. stick to define def's