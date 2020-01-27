object T10InversionOfControlDependencyInversion {
  def main(args: Array[String]): Unit = {
    code(args)
  }

  def code(args: Array[String]): Unit = {
    FnWithNamePrint.p(f, "f")  
    FnWithNamePrint.p(splitingTaskFarAway, "splitingTaskFarAway")  
  }

  def f(): Unit = {
    println(
      """
      |main calls code
      |code calls f
      |f calls a
      |a calls b
      |the dependency of a on b is called SOURCE CALLED DEPENDENCY
      |if the source code of b changes then the behavour of a changes
      |right now the SOURCE CODE goes one way and the FLOW OF CONTROL goes the SAME WAY
      |
      |will learn how to invert the control
      """.stripMargin
    )

    println(
      """
      |a is a HIGHER LEVEL POLICY 
      |a KNOWS MORE ABOUT THE BUSSINES
      |a is printing a finale message
      |
      |b is a LOW LEVEL POLICY
      |b contains an implementation detail
      |b is just decorating 
      """.stripMargin
    )

    a()

    def a(): Unit = {
      println(b(""""Hello "b". My name is a and I depende on you."""))
    }

    def b(message: String): String = 
      // anti scape codes
      Console.YELLOW + message + Console.RESET
  }

  def splitingTaskFarAway(): Unit = {
    ATeam.a()
  }
}