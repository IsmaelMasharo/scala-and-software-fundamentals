object T4Types {
  def main(args: Array[String]): Unit = {
    inferedType
    specifiedType
    aliasType
    unitType
    anyType
    destroyingTypes
    compileSuccedButFailsRun
    typeSystemPrinciples
  }

  def inferedType = {
    println("-" * 20 + "inferedType" + "-"*20)

    val a = 1337
    val b = true
    val c = 't'
    val d = "true"

    println(a)
    println(b)
    println(c)
    println(d)
    println()
  }

  def specifiedType = {
    println("-" * 20 + "specifiedType" + "-"*20)

    // literal values since they are encoded directly
    val a: Int = 1337 : Int /* this is called Type Description*/
    val b: Boolean = true
    val c: Char = 't'
    val d: String = "true"

    println(a)
    println(b)
    println(c)
    println(d)
    println()
  }

  def aliasType = {
    println("-" * 20 + "aliasType" + "-"*20)
    type Conditional = Boolean
    val a: Conditional = true
    def b: Conditional = true
    println(a)
    println(b)
    println()
  }

  def unitType = {
    println("-" * 20 + "unitType" + "-"*20)
    def e {} // Bad practice
    def f: Unit = {} // Good

    println("Printing Unit results in the following:")
    println(s"f value: $f")
    println()
  }

  def anyType = {
    println("-" * 20 + "anyType" + "-"*20)

    val b: Boolean = true
    val f = if(b) 10 else "not 10" // f infered as type Any
    val g: Any = if(b) 10 else "not 10" // specifying it 

    println("if(b) 10 else 'not 10'")
    println("b has to be boolean for compilation")
    println(s"f value: $f")
    println()
  }

  def destroyingTypes = {
    println("-" * 20 + "destroyingTypes" + "-"*20)

    val b: Boolean = true
    val g = if(b) 20 else 30       // g type Int
    val h: Any = if(b) 40 else 50  // destroying infered Int type

    println("val h: Any = if(b) 40 else 50")
    println("destroying infered Int type for h")
    println("The rule'll be select the Least Specific Type")
    println()
  }

  def compileSuccedButFailsRun = {
    println("-" * 20 + "compileSuccedButFailsRun" + "-"*20)
    // val a: Int = 1000 / 0
    println("val a: Int = 1000 / 0")
    println("""above compiles because 0 could be anything
      |like a parameter, but will crash when 0 since
      |it is expecting an Int.""".stripMargin.replaceAll("\n", " ")
    )
    println()
  }

  def typeSystemPrinciples = {
    println("-" * 20 + "typeSystemPrinciples" + "-"*20)

    def progress = """
    |if an expression is well typed (it compiles)
    |it either evaluates or is already a value
    """.stripMargin.replaceAll("\n", " ")

    def preservation = """
    |if an expression is well typed and it evaluates
    |the result has the same type
    """.stripMargin.replaceAll("\n", " ")

    println(s"progress: $progress")
    println(s"preservation: $preservation")

    println()

    var x: Int = 1
    // x: String = "error"
    println("val x: Int = 1")
    println("x: String = 'error'")
    println("the above will produce an error")

  }
}