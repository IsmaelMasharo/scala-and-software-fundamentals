object T5SubroutinesAndFunctions {
  def main(args: Array[String]): Unit = {
    defWithParameters
    functionLiteral
    freezingFarAwayLazyVal
    freezingFarAwayMethodWithParams
    freezingFarAwayMethodWithOutParams
    methodWOParamsConvention
    freezingFarAwayMethodWithParamsWOCrashing
  }

  def defWithParameters = {
    println("-" * 20 + "defWithParameters" + "-"*20)
    val isSunny = false
    val isRainy = true
    println("calling from different scope: Injection of arguments")
    println("FarAwayT5.code(isSunny, isRainy)")
    println("FarAwayT5.code2(isSunny, isRainy)")
    println("FarAwayT5.code3(isSunny, isRainy)")
    println(FarAwayT5.code(isSunny, isRainy))
    println()
  }

  def functionLiteral = {
    println("-" * 20 + "functionLiteral" + "-"*20)
    
    val f = (first: Boolean, second: Boolean) => {}
    println("val f = (first: Boolean, second: Boolean) => {}")
    println()
  }

  def freezingFarAwayLazyVal = {
    println("-" * 20 + "freezingFarAwayLazyVal" + "-"*20)
    println("runs ok")
    val frozencodeLazyValFunction = FarAwayT5.codeLazyValFunction
    println()     
  }

  def freezingFarAwayMethodWithParams = {
    println("-" * 20 + "freezingFarAwayMethodWithParams" + "-"*20)
    
    // val codeMethod = FarAwayT5.codeMethod    
    println("val codeMethod = FarAwayT5.codeMethod")
    println("will fail")
    println()     
  }

  def freezingFarAwayMethodWithParamsWOCrashing = {
    println("-" * 20 + "freezingFarAwayMethodWithParamsWOCrashing" + "-"*20)
    println("-" * 20 + "THIS IS ETA EXPATION" + "-"*20)

    val freezed1 = FarAwayT5.codeMethod _
    val freezed2: (Boolean, Boolean) => String = FarAwayT5.codeMethod
    println("val freezed1 = FarAwayT5.codeMethod _")
    println("val freezed2: (Boolean, Boolean) => String = FarAwayT5.codeMethod")
    println("both will run ok")
    println()     
  }

  def freezingFarAwayMethodWithOutParams = {
    println("-" * 20 + "freezingFarAwayMethodWithOutParams" + "-"*20)
    
    val codeMethodWOParams = FarAwayT5.codeMethodWOParams    
    println("val codeMethodWOParams = FarAwayT5.codeMethodWOParams")
    println("will run ok")
    println()     
  }

  def methodWOParamsConvention = {
    println("-" * 20 + "methodWOParamsConvention" + "-"*20)
    println("FarAwayT5.codeMethodWOParamsConvention()")
    FarAwayT5.codeMethodWOParamsConvention()
    println()
  }
}

object FarAwayT5 {
  def code = 
    (first: Boolean, second: Boolean) => {
      if (first)
        "I'm happy cause is sunny"
      else if (second)
        "I'm sad cause is rainy"
      else
        "Dont know how i feel"
    }

  // def with types specified
  def code2: (Boolean, Boolean) => Unit = {
    (first: Boolean, second: Boolean) => {
      if (first)
        "I'm happy cause is sunny"
      else if (second)
        "I'm sad cause is rainy"
      else
        "Dont know how i feel"
    }
  }

  // as Type Description (possible but not recomended)
  def code3 = {
    (first: Boolean, second: Boolean) => {
      if (first)
        "I'm happy cause is sunny"
      else if (second)
        "I'm sad cause is rainy"
      else
        "Dont know how i feel"
    }
  }: (Boolean, Boolean) => Unit

  // def with parameters
  def codeMethod(first: Boolean, second: Boolean): String =  {
    println("im about to create a subroutine")
    if (first)
      "I'm happy cause is sunny"
    else if (second)
      "I'm sad cause is rainy"
    else
      "Dont know how i feel"
  }

  // lazy val function
  lazy val codeLazyValFunction = {
    println("im about to create a subroutine")
    (first: Boolean, second: Boolean) => {
      if (first)
        "I'm happy cause is sunny"
      else if (second)
        "I'm sad cause is rainy"
      else
        "Dont know how i feel"
    }
  }

  // def without parameters
  def codeMethodWOParams =  {
    println("im a side efect")
    10
  }

  // def without parameters 
  def codeMethodWOParamsConvention(): Unit =  {
    println("im a side efect and im telling you with my EMPTY PARENTHESIS AND SPECIFYING IM A UNIT")
  }

}