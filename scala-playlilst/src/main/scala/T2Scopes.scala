object T2Scopes {
  def main(args: Array[String]): Unit = {

    /*
     * Everything inside brackets {} is in its own *scope*
     * Flow of control : the order in which statements are executed, ex top to bottom
     * with scopes we can manipulate the flow of control

    Initialization = binding = definition :
    var abc = 10

    Assignation:
    abc = 20

     * val is a value (stored in memory)
     * def is a definition ()only the computation is stored
     * lazy val behaves like a def the first time, and like a val afterwords
     * var is variable (stored in memory like val), but can be reassigned

     */

    scope1()

    scope2()

    scope3()

    scope4()

    scope5()

    scopewird()

  }

  def scope1(): Unit = {
    println()

    // we can create a full scope like this
    {
      val line3 = println("line 3")
      println(line3)

      /* it will print :
            line 3
            ()
       */

      // the above is equivalent to:
      println({})
      // BRACKETS {} INDICATE SCOPES
    }

    // the following will print nothing, not even the ()
    println()
  }

  def scope2(): Unit = {
    println()

    {
      val line = {
        println("useless")
        println("useless again")
        println("useless end")
        // in This scope the last thing that is happening is 1337
        // so, THIS IS WHAT IS RETURNED IN THIS SCOPE
        // AND IS ONLY PRINTED ONCE
        // this is an expression, it evaluates to something
        1337
      }

      println(s"Last printed line $line")

      // is the same as
      val line2 = 1338
      println(line2)
    }

  }

  def scope3(): Unit = {
    println()

    {
      // here the compiler knows he doesnt need to store the
      // reference to the value (1339)
      // BUT THE REFERENCE TO THE ENTIRE SCOPE
      def line = {
        println("Will I Run First?")
        1339
      }

      // Here as we discuss it is storing THE VALUE RETURNED
      // BY THE EXPRESSION WITHIN {}. That's why the line
      // "will I" gets printed, because its being evalueated
      // and 1340 is not printed because is only beign returned
      // and not evalueated
      val line4 = {
        println("Will I?")
        1340
      }

      // here what gets executed first is "line"
      // gets printed first and then it prints "Returned ..."
      println(s"Returned here $line")
      println(s"Returned again $line")

    }

  }

  def scope4(): Unit = {
    println()

    {
      // it behaves like a def the first time is executed.
      // the second time it behives like a val because the expression
      // has been already evaluated
      lazy val linelazy = {
        println("lazy")
        1340
      }

      // this is what gets executed first
      val lineval = {
        println("val")
        1341
      }

      def linedef = {
        println("def")
        1342
      }

      println(s"Returned lazy first time: $linelazy")
      println(
        s"Returned lazy second time, does it prints lazy above? $linelazy"
      )

      println(s"Returned def first time: $linedef")
      println(s"Returned def second time: $linedef")

      println(s"Returned val first time: $lineval")
      println(s"Returned val second time: $lineval")

    }
  }

  def scope5(): Unit = {

    println()

    var linex = 1337

    println(s"Returned var the first time: $linex")

    linex = 7331

    println(s"Returned var the second time: $linex")

  }

  def scopewird(): Unit = {

    println()

    println(s"Returned lazyvalwird before name it: $lazyvalwird")
    lazy val lazyvalwird = 1999

    println(s"Returned deflinewird before name it: $deflinewird")
    def deflinewird = 1999

    println(
      "Trying to return var or val before name it crashes and wont compile"
    )

    // crash
    // println(s"Returned varlinewird before name it: $varlinewird")
    // var varlinewird = 1999

    // crash
    // println(s"Returned vallinewird before name it: $vallinewird")
    // val vallinewird = 1999
  }

}