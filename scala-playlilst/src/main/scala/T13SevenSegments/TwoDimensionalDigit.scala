object TwoDimensionalDigit {
    val zero = TwoDimensionalString(
      top     = " _ ",
      middle  = "| |", 
      bottom  = "|_|"
    )
    val one = TwoDimensionalString(
      top     = "   ",
      middle  = "  |", 
      bottom  = "  |"
    )
    val two = TwoDimensionalString(
      top     = " _ ",
      middle  = " _|", 
      bottom  = "|_ "
    )
    val three = TwoDimensionalString(
      top     = " _ ",
      middle  = " _|", 
      bottom  = " _|"
    )
    val four = TwoDimensionalString(
      top     = "   ",
      middle  = "|_|", 
      bottom  = "  |"
    )
    val five = TwoDimensionalString(
      top     = " _ ",
      middle  = "|_ ", 
      bottom  = " _|"
    )
    val six = TwoDimensionalString(
      top     = " _ ",
      middle  = "|_ ", 
      bottom  = "|_|"
    )
    val seven = TwoDimensionalString(
      top     = " _ ",
      middle  = "  |", 
      bottom  = "  |"
    )
    val eight = TwoDimensionalString(
      top     = " _ ",
      middle  = "|_|", 
      bottom  = "|_|"
    )
    val nine = TwoDimensionalString(
      top     = " _ ",
      middle  = "|_|", 
      bottom  = " _|"
    )

    def apply(digit: Int): TwoDimensionalString = digit match {
      case 0 => zero
      case 1 => one
      case 2 => two
      case 3 => three
      case 4 => four
      case 5 => five
      case 6 => six
      case 7 => seven
      case 8 => eight
      case 9 => nine
      case _ => TwoDimensionalString.QuestionMarks
    }
}