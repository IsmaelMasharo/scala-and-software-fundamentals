object T13TuplesFinalCaseClasses {
  def main(args: Array[String]): Unit = {
    code(args)
    println(
      """
      |java has a nominal type system
      |it create types by given them a name
      |so it compares names for type checking
      """.stripMargin
      )
  }

  def code(args: Array[String]): Unit = {
    FnWithNamePrint.p(tuples, "tuples")
    FnWithNamePrint.p(messingWithTypeSystem, "messingWithTypeSystem")
    FnWithNamePrint.p(definingEnvironment, "definingEnvironment")
    FnWithNamePrint.p(definingDataStructureWithCaseClass, "definingDataStructureWithCaseClass")
    FnWithNamePrint.p(tuplesFinalCaseClassesUnderTheHood, "tuplesFinalCaseClassesUnderTheHood")
  }

  def tuples(): Unit = {
    val tuple1 = new Tuple1("qwerty")
    println(s"tuple1: ${tuple1._1}")
    println()

    val tuple2 = new Tuple2(1337, "one three three seven")
    println(s"tuple2.1: ${tuple2._1}")    
    println(s"tuple2.2: ${tuple2._2}")
    println()
    
    val tuple3 = new Tuple3(1, "1", 'u')
    println(s"tuple3.1: ${tuple3._1}")    
    println(s"tuple3.2: ${tuple3._2}")
    println(s"tuple3.3: ${tuple3._3}")
    println()

    val tuple2Sintx = 1 -> "uno"
    println("val tuple2Sintx = 1 -> \"uno\"")
    println(s"tuple2Sintx.1: ${tuple2Sintx._1}")    
    println(s"tuple2Sintx.2: ${tuple2Sintx._2}")
    println()

    val anyTupleSintx = (1, "1", 'u')
    println("val anyTupleSintx = (1, \"1\", 'u')")
    println(s"anyTupleSintx.1: ${anyTupleSintx._1}")    
    println(s"anyTupleSintx.2: ${anyTupleSintx._2}")
    println(s"anyTupleSintx.3: ${anyTupleSintx._3}")
    println()

    val swapped = tuple2.swap
    println("val swapped = tuple2.swap")
    println(s"swapped.1: ${swapped._1}")    
    println(s"swapped.2: ${swapped._2}")
  }

  def messingWithTypeSystem(): Unit = {
    type StringToInt = Tuple2[String, Int]

    def addTuples(first: StringToInt, second:StringToInt): StringToInt = {
      (
        first._1 + second._1,
        first._2 + second._2
      )
    }

    def stringWithLength(in: String): StringToInt = 
      in -> in.length

    val result: StringToInt = 
      addTuples(
        first = stringWithLength("hello"),
        second = stringWithLength("world")
      )
    
    val resultBug: StringToInt = 
      addTuples(
        first = stringWithLength("length"),
        second = "twelve" -> 20
      )
    
    println()
    println(s"ok: helloworld: expected len: 10 - actual len: ${result._2}")
    println(s"bug: lengthtwelve: expected len: 12 - actual len: ${resultBug._2}")
  }

  def definingEnvironment(): Unit = {
    class StringWithLenght(val string: String) {
      val lenght: Int = string.length

      def add(that: StringWithLenght): StringWithLenght = {
        new StringWithLenght(this.string + that.string)
      }

      def +(that: StringWithLenght): StringWithLenght = {
        new StringWithLenght(this.string + that.string)
      }
    }

    val first: StringWithLenght = new StringWithLenght("hello")
    val second: StringWithLenght = new StringWithLenght("world")

    val result: StringWithLenght = first.add(second)

    val word1: StringWithLenght = new StringWithLenght("length")
    val word2: StringWithLenght = new StringWithLenght("twelve")

    val result2: StringWithLenght = word1 + word2
    
    println()
    println(s"result is a class: $result")
    println(s"${result.string}: expected len: 10 - actual len: ${result.lenght}")
    println(s"${result2.string}: expected len: 12 - actual len: ${result2.lenght}")
  }

  def definingDataStructureWithCaseClass(): Unit = {

    println(
      """
      |final case :
      |by specifying this kind of class it creates a data structure
      |it has a verbose name when the instance is printed
      |it creates a companion object with default methods such as apply
      |so when instantiating an objet we can use the object companion without the new keyword
      """.stripMargin
    )

    println(
      """
      |final case creates:
      | object StringWithLength {
      |   def apply(string: String): StringWithLength = 
      |     new StringWithLength(string)
      |
      |also apply is called by defaul
      |
      |now instantiating a class will be like this
      |StringWithLenght(this.string + that.string)
      """.stripMargin
    )

    final case class StringWithLenght(val string: String) {
      val lenght: Int = string.length

      def add(that: StringWithLenght): StringWithLenght = {
        StringWithLenght(this.string + that.string)
      }

      def +(that: StringWithLenght): StringWithLenght = {
        StringWithLenght(this.string + that.string)
      }
    }

    val first: StringWithLenght = new StringWithLenght("hello")
    val second: StringWithLenght = new StringWithLenght("world")

    val result: StringWithLenght = first.add(second)

    val word1: StringWithLenght = new StringWithLenght("length")
    val word2: StringWithLenght = new StringWithLenght("twelve")

    val result2: StringWithLenght = word1 + word2
    
    println()
    println(s"result is a data structure: $result")
    println(s"${result.string}: expected len: 10 - actual len: ${result.lenght}")
    println(s"${result2.string}: expected len: 12 - actual len: ${result2.lenght}")
  }

  def tuplesFinalCaseClassesUnderTheHood(): Unit = {
    println("bob as Person case class")
    println()

    final case class Person(
      name: String,
      age: Int,
      phone: String,
      email: String
    )

    val bob = 
      Person(
        name = "Bob",
        age = 20,
        phone = "987408109",
        email = "bob@gmail.com"
      )
    
    println(bob)
    println(bob.name)
    println(bob.age)
    println(bob.phone)
    println(bob.email)

    println()
    println("bob as T4 like tuple4")
    println()
    
    final case class T4(
      _1: String,
      _2: Int,
      _3: String,
      _4: String
    )

    val bobAsT4 = 
      T4(
        _1 = "Bob",
        _2 = 20,
        _3 = "987408109",
        _4 = "bob@gmail.com"
      )
    
    println(bobAsT4)
    println(bobAsT4._1)
    println(bobAsT4._2)
    println(bobAsT4._3)
    println(bobAsT4._4)
  }
}