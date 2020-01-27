object T12MessagePassingStyle {
  def main(args: Array[String]): Unit = {
    code(args)
  }

  def code(args: Array[String]): Unit = {
    FnWithNamePrint.p(baOperationNotChooseable, "baOperationNotChooseable")
    FnWithNamePrint.p(baOperationChooseable, "baOperationChooseable")
    FnWithNamePrint.p(removeTheSideEfect, "removeTheSideEfect")
    FnWithNamePrint.p(addGetters, "addGetters")
    FnWithNamePrint.p(bankAccountAsType, "bankAccountAsType")
    FnWithNamePrint.p(bankAccountWithTransfer, "bankAccountWithTransfer")
    FnWithNamePrint.p(namingAccordingToMessagePassingStyle, "namingAccordingToMessagePassingStyle")
    FnWithNamePrint.p(messagePassingStyleToOO, "messagePassingStyleToOO")
  }

  def baOperationNotChooseable(): Unit = {
    def bankAccount(initialBalance: Int): Int => Int = {
      var balance: Int = initialBalance

      val withdraw: Int => Int = amount =>
        if (balance >= amount) {
          balance = balance - amount
          balance
        } else
          sys.error("insufficient funds")
          
      val deposit: Int => Int = amount =>
        if (balance >= 1) {
          balance = balance + amount
          balance
        } else
          sys.error("it's only possible to deposit positive amounts")

      deposit
    }

    val bankAccount1 = bankAccount(initialBalance = 100)

    println(bankAccount1(10))
    println(bankAccount1(10))
  }

  def baOperationChooseable(): Unit = {
    def bankAccount(initialBalance: Int): Symbol => (Int => Int) = {
      var balance: Int = initialBalance

      val withdraw: Int => Int = amount =>
        if (balance >= amount) {
          balance = balance - amount
          balance
        } else
          sys.error("insufficient funds")
          
      val deposit: Int => Int = amount =>
        if (balance >= 1) {
          balance = balance + amount
          balance
        } else
          sys.error("it's only possible to deposit positive amounts")

      val dispatch: Symbol => (Int => Int) = operation =>
        if (operation == 'withdraw) withdraw
        else if (operation == 'deposit) deposit
        else sys.error(s"Unknown operation: $operation")

      dispatch
    }

    val bankAccount1 = bankAccount(initialBalance = 100)

    println("message passing style")
    println(bankAccount1('withdraw)(10))
    println(bankAccount1('withdraw)(15))
    println(bankAccount1('deposit)(125))
    // println(bankAccount1('bla)(125)) // will through error
  }

  def removeTheSideEfect(): Unit = {
    def bankAccount(initialBalance: Int): Symbol => (Int => Unit) = {
      var balance: Int = initialBalance

      val withdraw: Int => Unit = amount =>
        if (balance >= amount) {
          balance = balance - amount
        } else
          sys.error("insufficient funds")
          
      val deposit: Int => Unit = amount =>
        if (balance >= 1) {
          balance = balance + amount
        } else
          sys.error("it's only possible to deposit positive amounts")

      val dispatch: Symbol => (Int => Unit) = operation =>
        if (operation == 'withdraw) withdraw
        else if (operation == 'deposit) deposit
        else sys.error(s"Unknown operation: $operation")

      dispatch
    }

    val bankAccount1 = bankAccount(initialBalance = 100)

    println("message passing style")
    println(bankAccount1('withdraw)(10))
    println(bankAccount1('withdraw)(15))
    println(bankAccount1('deposit)(125))
  }

  def addGetters(): Unit = {
    def bankAccount(initialBalance: Int): Symbol => Any = {
      var balance: Int = initialBalance

      val withdraw: Int => Unit = amount =>
        if (balance >= amount) {
          balance = balance - amount
        } else
          sys.error("insufficient funds")
          
      val deposit: Int => Unit = amount =>
        if (balance >= 1) {
          balance = balance + amount
        } else
          sys.error("it's only possible to deposit positive amounts")


      val getBalance: () => Int = () => balance

      val dispatch: Symbol => Any = operation =>
        if (operation == 'withdraw) withdraw
        else if (operation == 'deposit) deposit
        else if (operation == 'getBalance) getBalance
        else sys.error(s"Unknown operation: $operation")

      dispatch
    }

    val bankAccount1 = bankAccount(initialBalance = 100)
    val withdraw1 = bankAccount1('withdraw).asInstanceOf[Int => Unit]
    val deposit1 = bankAccount1('deposit).asInstanceOf[Int => Unit]
    val getBalance1 = bankAccount1('getBalance).asInstanceOf[() => Int]

    withdraw1(10)
    println(getBalance1())
    withdraw1(15)
    println(getBalance1())
    deposit1(125)
    println(getBalance1())
  }

  def bankAccountAsType(): Unit = {
    type bankAccount = Symbol => Any

    def bankAccount(initialBalance: Int): bankAccount = {

      val dispatch: bankAccount = operation =>
        println()
      dispatch
    }
    println("I;m a bankAcount definition with a bankAccount Symbol")
  }

  def bankAccountWithTransfer(): Unit = {
    type bankAccount = Symbol => Any

    def bankAccount(initialBalance: Int): bankAccount = {
      var balance: Int = initialBalance

      val withdraw: Int => Unit = amount =>
        if (balance >= amount) {
          balance = balance - amount
        } else
          sys.error("insufficient funds")
          
      val deposit: Int => Unit = amount =>
        if (balance >= 1) {
          balance = balance + amount
        } else
          sys.error("it's only possible to deposit positive amounts")


      val getBalance: () => Int = () => balance

      val dispatch: bankAccount = operation =>
        if (operation == 'withdraw) withdraw
        else if (operation == 'deposit) deposit
        else if (operation == 'getBalance) getBalance
        else sys.error(s"Unknown operation: $operation")

      dispatch
    }

    def makeTransfer(from: bankAccount, amount: Int, to: bankAccount): Unit = {
      
      def getBalance(account: bankAccount): Int = 
        account('getBalance).asInstanceOf[() => Int]()

      def showBothAccount(): Unit = {
        println(s"from: ${getBalance((from))}")
        println(s"to: ${getBalance((to))}")
      }

      println("before")
      showBothAccount()

      from('withdraw).asInstanceOf[Int => Unit](amount)
      to('deposit).asInstanceOf[Int => Unit](amount)

      println()
      println(s"transfering $amount")
      println()

      println("after")
      showBothAccount()
    }

    val thisOne: bankAccount = bankAccount(initialBalance = 50)
    val thatOne: bankAccount = bankAccount(initialBalance = 15)

    makeTransfer(
      from = thisOne,
      amount = 20,
      to = thatOne
    )
  }

  def namingAccordingToMessagePassingStyle(): Unit = {
    type BankAccount = Symbol => Any

    def BankAccount(initialBalance: Int): BankAccount = {
      var balance: Int = initialBalance

      val withdraw: Int => Unit = amount =>
        if (balance >= amount) {
          balance = balance - amount
        } else
          sys.error("insufficient funds")
          
      val deposit: Int => Unit = amount =>
        if (balance >= 1) {
          balance = balance + amount
        } else
          sys.error("it's only possible to deposit positive amounts")


      val getBalance: () => Int = () => balance

      val dispatch: BankAccount = operation =>
        if (operation == 'withdraw) withdraw
        else if (operation == 'deposit) deposit
        else if (operation == 'getBalance) getBalance
        else sys.error(s"Unknown operation: $operation")

      dispatch
    }

    def makeTransfer(from: BankAccount, amount: Int, to: BankAccount): Unit = {
      
      def getBalance(account: BankAccount): Int = 
        account('getBalance).asInstanceOf[() => Int]()

      def showBothAccount(): Unit = {
        println(s"from: ${getBalance((from))}")
        println(s"to: ${getBalance((to))}")
      }

      println("before")
      showBothAccount()

      from('withdraw).asInstanceOf[Int => Unit](amount)
      to('deposit).asInstanceOf[Int => Unit](amount)

      println()
      println(s"transfering $amount")
      println()

      println("after")
      showBothAccount()
    }

    val thisOne: BankAccount = BankAccount(initialBalance = 50)
    val thatOne: BankAccount = BankAccount(initialBalance = 15)

    makeTransfer(
      from = thisOne,
      amount = 20,
      to = thatOne
    )
  }

  def messagePassingStyleToOO(): Unit = {
    println(
      """
      |removing type BankAccount = Symbol => Any
      |we can not call directly ('function) since now bank account is an instance of a class
      |the function needs to be called from the instance
      |if we call the function dispatch then we can do the same with withdraw and deposit
      |so we can remove dispatch and call the other functions directly
      |and we can remove also the casting asInstanceOf.
      |we can remove the helper getBalance and use the instance method directly since the calling is so small now.
      |Since we are using class we now can define withdraw and deposit as regular defs instead of lambdas.
      |We also need to take care of balance in order not to be modify calling directle the instance parameter,
      |we do this with the private[this] words.
      |We also can give an extra name to this if necessary (ex. for distinguish shadowing variables in nested enviroments).
      |Following the convention, nameing privates with an underscore: _balance
      """.stripMargin
    )

    println(
      """
      |Enviroment Model of Computation
      |allows us to name things. 
      |We could wrap makeTransfer in its own Environment: OperationsWithMultipleBankAccounts
      """.stripMargin
    )

    println(
      """
      |Companion Object
      |If we have an object called the same as a class it is called a companion object.
      |It is allowed to access public members from its companion class
      """.stripMargin
    )

    println(
      """
      |Infix Notation:
      |If a method takes only one parameter we could call it with spaces instead of dots
      |ex. from withdraw amount
      |it is also recommended not us it that often
      """.stripMargin
    )

    println(
      """
      |Java API
      |we can use java functions, dont forget it
      |ex: println("some string").replace('s', 'S')
      """.stripMargin
    )

    println(
      """
      |Class constructor
      |if we define constructor just with the name variable, by default it will be private.
      |class BankAccount(initialBalance: Int) === class BankAccount(private[this] val initialBalance: Int)
      |We can make it public by explicitly defining it as a val
      |class BankAccount(val initialBalance: Int)
      """.stripMargin
    )

    object BankAccount {
      def f(b: BankAccount): Unit = {
        println(b.balance)
        // this will fail since _balance is private
        // println(b.balance)
      }
    }

    class BankAccount(initialBalance: Int) {

      // if we use the following we can call balance with self.balance
      self =>

      private[this] var _balance: Int = initialBalance

      def withdraw(amount: Int): Unit =
        if (this._balance >= amount) {
          this._balance = this._balance - amount
        } else
          sys.error("insufficient funds")
          
      def deposit(amount: Int): Unit =
        if (this._balance >= 1) {
          this._balance = this._balance + amount
        } else
          sys.error("it's only possible to deposit positive amounts")

      def balance: Int = this._balance
    }

    object OperationsWithMultipleBankAccounts { 
      def makeTransfer(from: BankAccount, amount: Int, to: BankAccount): Unit = {
        
        def showBothAccount(): Unit = {
          println(s"from: ${from.balance}")
          println(s"to: ${to.balance}")
        }

        println("before")
        showBothAccount()

        from.withdraw(amount)
        to.deposit(amount)

        println()
        println(s"transfering $amount")
        println()

        println("after")
        showBothAccount()
      }
    }

    val thisOne: BankAccount = new BankAccount(initialBalance = 50)
    val thatOne: BankAccount = new BankAccount(initialBalance = 15)

    OperationsWithMultipleBankAccounts.makeTransfer(
      from = thisOne,
      amount = 20,
      to = thatOne
    )

    // this will fail since balance is private
    //thisOne.balance = 100
  }
}