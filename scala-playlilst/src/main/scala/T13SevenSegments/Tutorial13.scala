import Constants._

object Tutorial13 {

  def main(args: Array[String]): Unit = {
    code(args)
  }

  def code(args: Array[String]): Unit = {
    FnWithNamePrint.p(tickFor3Seconds, "tickFor3Seconds")
    FnWithNamePrint.p(tickUnitlEnterIsPressed, "tickUnitlEnterIsPressed")
  }

  def tickFor3Seconds(): Unit = {
    Scala.loop(times = FewSeconds) {
      Terminal.clearCanvas()
      Terminal.goUp(LinesToGoUp)
      TwoDimensionalTime.showCurrent(TimePattern)
      Scala.wait(Interval)
    }
  }

  def tickUnitlEnterIsPressed(): Unit = {
    Scala.tickUnitlEnterIsPressed(Interval) {
      Terminal.clearCanvas()
      Terminal.goUp(LinesToGoUp)
      TwoDimensionalTime.showCurrent(TimePattern)
    }
  }
}