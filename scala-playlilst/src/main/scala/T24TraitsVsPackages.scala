object Tutorial24 {
  def main(args: Array[String]): Unit = {
    code(args)
  }

  def code(args: Array[String]): Unit = {
    FnWithNamePrint.p(f1, "f1")
    FnWithNamePrint.p(f2, "f2")
  }

  def f1(): Unit = {
    trait Common {
      def showCommonThing(): Unit = {
        println("Common Thing")
      }
    }

    trait FirstVersion extends Common {
      def showFirstVersionThing(): Unit = {
        showCommonThing()
        println("First Version Thing")
      }      
    }

    trait SecondVersion {
      // this is a way to tell the compilar 
      // whenever an `SecondVersion` is mixed into an Object
      // the compiler will make sure that we also mixin the `Common`
      this: Common =>

      def showSecondVersionThing(): Unit = {
        showCommonThing()
        println("Second Version Thing")
      }      
    }

    object FV extends FirstVersion
    object SV extends SecondVersion with Common
  
    FV.showFirstVersionThing()

    println()

    SV.showSecondVersionThing()
  }

  def f2(): Unit = {
    trait Chicken {
      this: Egg =>

      println("chicken")

      def onChicken(): Unit = {
        println("onChicken")
      }

      onEgg()
    }

    trait Egg {
      this: Chicken =>
  
      println("egg")

      def onEgg(): Unit = {
        println("onEgg")
      }

      onChicken()
    }

    object FirstChickenThenEgg extends Chicken with Egg
    object FirstEggThenChicken extends Egg with Chicken

    println(FirstChickenThenEgg)
    println(FirstEggThenChicken)
  }
}