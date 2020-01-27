object FnWithNamePrint {
  def p(fn: () => Unit, fnName: String): Any = {
    val prefix = "-" * 20 + fnName + "-" * 20
    println(prefix)
    fn()
    println()
  }
}