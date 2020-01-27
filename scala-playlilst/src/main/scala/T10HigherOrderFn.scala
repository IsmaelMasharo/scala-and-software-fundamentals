object T10HigherOrderFn {
  def main(args: Array[String]): Unit = {
    
    // this sintax is less surprising
    // since we know we have to pass a function
    // as argument
    def loopLonger(code: () => Unit): Unit =
      1 to 10 foreach { _ => 
        code()
      }
    
    loopLonger { () =>
      println("longer")
    }

    println()
    
    // we can ommit the () here
    // but it will surprise the client
    // since without looking at the definition
    // we can't know the argument is a function
    def loopShorter(code: => Unit): Unit =
      1 to 10 foreach { _ => 
        // and here
        code
      }
    
    // and also when calling it 
    // we get rid of () =>
    loopShorter {
      println("shorter")
    }
    
  }
  
}