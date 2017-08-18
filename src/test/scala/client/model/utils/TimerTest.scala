package client.model.utils

import java.util.{Observable, Observer}

import org.scalatest.FunSuite

/**
  * @author Margherita Pecorelli
  */
class TimerTest extends FunSuite {

  case class MyObserver() extends Observer {
    override def update(observable : Observable, arg: scala.Any): Unit = isObserverUpdate = true;
  }

  private val observer = MyObserver()
  private val timer = TimerImpl()
  private var isObserverUpdate = false

  timer.addObserver(observer)

  test("counts") {
    assert(!isObserverUpdate)
    timer.counts(TimerTest.millisecondsToWait)
    println("While it counts, I can do something else!")
    val time = System.currentTimeMillis
    while(System.currentTimeMillis <= time + TimerTest.millisecondsToWait + TimerTest.timeToWait) {}
    assert(isObserverUpdate)
  }

}

object TimerTest {

  private val millisecondsToWait = 5000
  private val timeToWait = 50

}