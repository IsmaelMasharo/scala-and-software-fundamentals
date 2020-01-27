object Terminal {
  def clearCanvas(): Unit = {
    println("\u001b[2J")
  }

  def goUp(lines: Int): Unit = {
    print(s"\u001b[${lines}A")
  }
}