object T7RecursionEx {
  def main(args: Array[String]): Unit = {
    factorial(9)
  }

  def factorial(n: Int): Int = {
    var iterations = 0

    @scala.annotation.tailrec
    def loop(x: Int, acc: Int): Int = {
      iterations += 1

      if(x == 0) acc
      else loop(x - 1, acc*x)
    }

    val result = loop(n, 1)

    println("Iteration: " + iterations)

    result
  }
}