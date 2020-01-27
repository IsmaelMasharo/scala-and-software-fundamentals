object T3FlowControl {
  def main(args: Array[String]): Unit = {
    println("-" * 50)

    scope1
    scope2

    println("-" * 50)
  }

  def scope1 = {
    println("scope1")

    def isSunnyDay = true
    def isRainyDay = true

    def result = {
      if(isSunnyDay)
        10
      else if(isRainyDay)
        "I'm sad because it's raining"
      else
        "Not sure how I fell about today"
    }

    // println(result.reverse)
    println(
      """
      println(result.reverse) will fail 
      |during Compilation since result is 
      |taking Any as type
      """.stripMargin.replaceAll("\n", " ")
    )
  }

  def scope2 = {
    println("scope2")

    {
      println(FarAwayt3.hi)
    }
  }
}