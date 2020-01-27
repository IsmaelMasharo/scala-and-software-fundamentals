object T8Literals {
  def main(args: Array[String]): Unit = {
    println("-" * 50)
    clock
  }

  def now: String = {
    val formater = 
      java.time.format
        .DateTimeFormatter
        .ofPattern("HH:mm:ss")
    
    java.time
      .LocalDateTime
      .now
      .format(formater)
  }

  def inOneLine(in: String): String = {
    val clearLine = "\u001b[2K"
    val carrieageReturn = "\r"

    clearLine + carrieageReturn + in
  }

  def clock = {
    var i = 0
    while (i < 5) {
      Thread.sleep(1 * 1000)
      print(inOneLine(now))

      i += 1
    }
  }
}