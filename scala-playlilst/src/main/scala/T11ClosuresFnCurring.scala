object T11ClosuresFnCurring {
  def main(args: Array[String]): Unit = {
    definition
    curringProgramatically
    curringBuiltIn
  }

  def definition = {
    println("-" * 20 + "definition" + "-"*20)
    val freeVariable: Int = 7

    def openTermFn(boundedVariable: Int): Int = freeVariable + boundedVariable
    def closedTermFn(boundedVariable: Int): Int = 1 + boundedVariable
  }

  def curringProgramatically = {
    println("-" * 20 + "programatically" + "-"*20)

    // by injecting an argument (fixed freeVariable) it produces a closedTermFn
    def closedTerm(freeVariable: Int): Int => Int =
      // bellow will be an openTermFn
      boundedVariable => freeVariable + boundedVariable

    val closure = closedTerm(freeVariable = 8)
    val partialComputation = closure

    println(partialComputation(5))

  }

  def curringBuiltIn = {
    println("-" * 20 + "curringBuiltIn" + "-"*20)

    def uncurringClosedTerm(freeVariable: Int, boundedVariable: Int): Int =
      freeVariable + boundedVariable
    
    def curringClosedTerm(freeVariable: Int)(boundedVariable: Int) =
      freeVariable + boundedVariable
    
    val closure = curringClosedTerm(freeVariable = 8) _
    println(closure(10))
    println()
  }
}