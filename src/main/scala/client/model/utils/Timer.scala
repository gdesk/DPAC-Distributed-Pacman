package client.model.utils

import java.util.{Observable, Observer}

/**
  * Created by margherita on 18/07/17.
  */
case class Timer(val observer: Observer) extends Observable {

  val observable = this

  /**
    *Counts for X milliseconds (input parameter) without doing anything. When it ends, notify the observer.
    *
    * @param milliseconds - milliseconds to wait
    */
  def counts(milliseconds: Int): Unit = {
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
