import java.util._
import scala.concurrent.duration.FiniteDuration

object Scala {
  def loop(times: Int)(somecode: => Unit): Unit = {
    1 to times foreach { _ =>
      somecode
    }
  }

  def wait(delay: FiniteDuration): Unit = {
    Thread.sleep(delay.toMillis)
  }

  def tickUnitlEnterIsPressed(interval: FiniteDuration)(somecode: => Unit): Unit = {
    val timer: Timer = new Timer

    val delayInMilliseconds: Long = 0
    val periodInMilliseconds: Long = interval.toMillis

    // trait
    val task: TimerTask = new TimerTask {
      def run(): Unit = {
        somecode
      }
    }

    timer.scheduleAtFixedRate(
      task,
      delayInMilliseconds,
      periodInMilliseconds
    )

    io.StdIn.readLine // block current Thread until ENTER is pressed
    timer.cancel()
  }
}