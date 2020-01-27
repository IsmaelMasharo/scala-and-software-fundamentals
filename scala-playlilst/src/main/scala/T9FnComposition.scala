object T9FnComposition {
  def main(args: Array[String]): Unit = {
    val f: Int => Int = _ + 5
    val g: Int => Int = _ / 2

    // direct rightThenLeft
    println(f(g(20))) // (20/2)+5=15

    // rightThenLeft as function
    def rightThenLeft(
      left: Int => Int,
      right: Int => Int
    ): Int => Int =
      input => left(right(input))

    val gThenf = rightThenLeft(f, g)
    println(gThenf(20))

    // already built in in the language
    val gThenf2 = f compose g
    println(gThenf2(20))

    // backwards
    val gThenf3 = g andThen f
    println(gThenf3(20))
  }
}