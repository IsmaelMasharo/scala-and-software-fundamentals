object T6Recursion {
  def main(args: Array[String]): Unit = {
    defRecursion
    lazyValRecursion
    reason
  }

  def defRecursion = {
    println("-" * 20 + "defRecursion" + "-"*20)
    
    @scala.annotation.tailrec
    def b: Boolean = {
      println("in b")
      b
    }

    // println(b)
    println("println(b) will call b for ever")
    println()
  }

  def lazyValRecursion = {
    println("-" * 20 + "lazyValRecursion" + "-"*20)
    
    lazy val b: Boolean = {
      println("in b")
      b
    }

    // println(b)
    println("lazyValRecursion will crash recursion")
    println()
  }

  def reason = {
    println("-" * 20 + "reason" + "-"*20)
    val r = """
    |In java the method defRecursion will fail: stack overflow error
    |In scala no. 
    |WHY?
    |something called TAIL RECURSION
    |this comes from LAMBDA CALCULUS
    """.stripMargin.replaceAll("\n", " ")
    
    println(r)
    println()
  }
 
}