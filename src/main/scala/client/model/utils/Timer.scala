package client.model.utils

import java.util.{Observable, Observer}

/**
  * This trait represent a timer.
  *
  * @author Margherita Pecorelli
  */
trait Timer {

  /**
    *Counts for X milliseconds (input parameter) without doing anything.
    *
    * @param milliseconds - milliseconds to wait
    */
  def counts(milliseconds: Int): Unit
}

/**
  * This class represent a timer. When it finish to count, he notify the observer registered.
  *
  * @author Margherita Pecorelli
  */
case class TimerImpl(val observer: Observer) extends Observable with Timer{

  val observable = this

  /**
    *Counts for X milliseconds (input parameter) without doing anything. When it ends, notify the observer.
    *
    * @param milliseconds - milliseconds to wait
    */
  override def counts(milliseconds: Int): Unit = {
    val thread = new Thread {
      override def run = {
        val time = System.currentTimeMillis()
        while(System.currentTimeMillis() < time + milliseconds) {}
        observer.update(observable,"")
      }
    }
    thread.start
  }

}
