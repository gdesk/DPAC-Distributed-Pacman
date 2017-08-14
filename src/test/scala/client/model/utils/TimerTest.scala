package client.model.utils

import java.util.{Observable, Observer}

import org.scalatest.FunSuite

/**
  * Created by margherita on 18/07/17.
  */
class TimerTest extends FunSuite {

  case class MyObserver() extends Observer {
    override def update(observable : Observable, arg: scala.Any): Unit = isObserverUpdate = true;
  }

  val observer = MyObserver()
  val timer = TimerImpl()
  timer.addObserver(observer)
  var isObserverUpdate = false

  test("counts") {
    assert(!isObserverUpdate)
    timer.counts(TimerTest.millisecondsToWait)
    println("While it counts, I can do something else!")
    val time = System.currentTimeMillis()
    while(System.currentTimeMillis() < time + TimerTest.millisecondsToWait*2) {}
    assert(isObserverUpdate)
  }

}
 object TimerTest {
   private val millisecondsToWait = 1000
 }