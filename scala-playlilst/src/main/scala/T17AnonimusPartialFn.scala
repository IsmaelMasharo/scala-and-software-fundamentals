object Tutorial17 {
  def main(args: Array[String]): Unit = {
    code(args)
  }

  def code(args: Array[String]): Unit = {
    FnWithNamePrint.p(CreatingOwnFunction1, "CreatingOwnFunction1")
    FnWithNamePrint.p(FnWithAndWithoutSintactSugar, "FnWithAndWithoutSintactSugar")
    FnWithNamePrint.p(SingleAbstractMethodsSAM, "SingleAbstractMethodsSAM")
    FnWithNamePrint.p(f4, "f4")
    FnWithNamePrint.p(f5, "f5")
    FnWithNamePrint.p(partialFunctionWithoutSyntacticSugar, "partialFunctionWithoutSyntacticSugar")
    FnWithNamePrint.p(PartialFunctionOnMapFilterCollect, "PartialFunctionOnMapFilterCollect")
    FnWithNamePrint.p(PartialFunctionWithSintaticSugar, "PartialFunctionWithSintaticSugar")
    FnWithNamePrint.p(PatternMatchingWithPartialFns, "PatternMatchingWithPartialFns")
    FnWithNamePrint.p(PatternMatchingWithMatch, "PatternMatchingWithMatch")
  }

  def CreatingOwnFunction1(): Unit = {
    trait SimpleFunction1[-Input, +Output] {
      def apply(argument: Input): Output
    }

    val totalFunction: Int => String = 
      argument => "\"" + argument +  "\""

    val totalFunctionWithoutSyntacticSugar: SimpleFunction1[Int, String] = 
      new SimpleFunction1[Int, String] {
        override def apply(argument: Int): String = 
          "\"" + argument +  "\""
      }
    
    val randomNumber = scala.util.Random.nextInt
    println(randomNumber)
    println(totalFunction(randomNumber))
    println(totalFunctionWithoutSyntacticSugar(randomNumber))
  }

  def FnWithAndWithoutSintactSugar(): Unit = {

    // funciton literal = lambda = anonimus function
    val totalFunction: Int => String = 
      argument => "\"" + argument +  "\""

    val totalFunction2 = 
      (argument: Int) => "\"" + argument +  "\""

    // lambda = anonimus function
    val totalFunctionWithoutSyntacticSugar/*: Function1[Int, String]*/ = 
      new Function1[Int, String] {
        override def apply(argument: Int): String = 
          "\"" + argument +  "\""
      }
    
    val randomNumber = scala.util.Random.nextInt
    println(randomNumber)
    println(totalFunction(randomNumber))
    println(totalFunctionWithoutSyntacticSugar(randomNumber))
  }

  def SingleAbstractMethodsSAM(): Unit = {

    // this is a SAM
    trait EventListener {
      def eventOcurred(): Unit
    }

    class Button(name: String, listener: EventListener) {
      def click(): Unit = {
        listener.eventOcurred()
      }
    }

    // it can be instantiate like this
    val listener: EventListener = new EventListener {
      override def eventOcurred(): Unit = {
        println("event ocurred")
      }
    }

    // or with this sintactic sugar
    val listener1: EventListener = () => println("event ocurred")

    val button: Button = new Button("submit", listener1)

    button.click()
  }

  def f4(): Unit = {
    class Button(name: String, eventOcurred: () => Unit) {
      def click(): Unit = {
        eventOcurred()
      }
    }

    val button: Button = new Button("submit", () => println("event ocurred"))
    button.click()
  }

  def f5(): Unit = {
    class Button(name: String, eventOcurred: => Unit) {
      def click(): Unit = {
        eventOcurred
      }
    }

    val button: Button = new Button("submit", println("event ocurred"))
    button.click()
  }

  def partialFunctionWithoutSyntacticSugar(): Unit = {

    // PARTIAL FUNCTION CAN SPECIFIED INPUTS THAT THE FUNCTION WON HANDLE
    // IT DOES THIS WITH THE FUNCTION: isDefinedAt
    val partialFunctionWithoutSyntacticSugar: PartialFunction[Int, String] = 
      new PartialFunction[Int, String] {
        override def apply(argument: Int): String = 
          if (argument == 4)
            "\"" + argument +  "\""
          else
            sys.error(s"Dude, you were supposed to call isDefinedAt($argument) before calling apply($argument)")

        override def isDefinedAt(argument: Int): Boolean = 
          if (argument == 4)
            true
          else
            false
      }
    
    val randomNumber = scala.util.Random.nextInt
    println(randomNumber)

    // the idea is not the creator of the partial function should 
    // check if apply is defined at the argmunet being passed, 
    // but the called: 
    if (partialFunctionWithoutSyntacticSugar.isDefinedAt(5))
      println(partialFunctionWithoutSyntacticSugar(randomNumber))
      else
      println("ain't defined at 5")
    // this will fail
    // println(partialFunctionWithoutSyntacticSugar(7))
  }

  def PartialFunctionOnMapFilterCollect(): Unit = {
    val partialFunctionWithoutSyntacticSugar: PartialFunction[Int, String] = 
      new PartialFunction[Int, String] {
        override def apply(argument: Int): String = 
          if (argument == 4)
            "\"" + argument +  "\""
          else
            s"Dude, you were supposed to call isDefinedAt($argument) before calling apply($argument)"

        override def isDefinedAt(argument: Int): Boolean = 
          if (argument == 4)
            true
          else
            false
      }
    
    println(5)
    println(partialFunctionWithoutSyntacticSugar(5))

    val totalFunction: Int => String = 
      argument => "\"" + argument +  "\""

    val range = 1 to 10

    range.foreach(println)

    val function: Int => Int = _ + 1

    // map requires a function
    // range map totalFunction foreach println

    val predicate: Int => Boolean = _ == 4

    // range filter predicate map totalFunction foreach println

    // // collect gets applied to PARTIAL FUNCTIONS
    // range collect partialFunctionWithoutSyntacticSugar foreach println

    // the above is equivalent to:
    // range foreach { number =>
    //   if (partialFunctionWithoutSyntacticSugar.isDefinedAt(number))
    //     println(partialFunctionWithoutSyntacticSugar(number))
    // }

    // // PARTIAL FUNCTION EXTEND FUNCTION 1
    // // SO WE CAN PASS IT TO MAP
    // // but it will complain
    // range map partialFunctionWithoutSyntacticSugar foreach println
  }

  def PartialFunctionWithSintaticSugar(): Unit = {

    // this
    type ~>[-Input, +Output] = PartialFunction[Input, Output]

    val partialFunction: Int ~> String = {
      case 4 => "\"" + 4 +  "\""
    }

    // get tranlated into this at compile time
    val partialFunctionWithoutSyntacticSugar: PartialFunction[Int, String] = 
      new PartialFunction[Int, String] {
        override def apply(argument: Int): String = 
          if (argument == 4)
            "\"" + argument +  "\""
          else
            throw new MatchError(argument)

        override def isDefinedAt(argument: Int): Boolean = 
          if (argument == 4)
            true
          else
            false
      }

    // both are equivalent
    println(partialFunction(4))
    println(partialFunctionWithoutSyntacticSugar(4))
  }

  def PatternMatchingWithPartialFns(): Unit = {
    type ~>[-Input, +Output] = PartialFunction[Input, Output]

    def toRomanNumberDigit(number: Int): String = {
      val partialFunction: Int ~> String = {
        case 0 => "0: N"
        case 1 => "1: I"
        case 2 => "2: II"
        case 3 => "3: III"
        case 4 => "4: IV"
        case 5 => "5: V"
        case 6 => "6: VI"
        case 7 => "7: VII"
        case 8 => "8: VIII"
        case 9 => "9: IX"
      }

      partialFunction(number)
    }

    0 to 9 map toRomanNumberDigit foreach println
  }

  def PatternMatchingWithMatch(): Unit = {

    def toRomanNumberDigit(number: Int): String = number match {
        case 0 => "0: N"
        case 1 => "1: I"
        case 2 => "2: II"
        case 3 => "3: III"
        case 4 => "4: IV"
        case 5 => "5: V"
        case 6 => "6: VI"
        case 7 => "7: VII"
        case 8 => "8: VIII"
        case 9 => "9: IX"
      }

    0 to 9 map toRomanNumberDigit foreach println
  }
}