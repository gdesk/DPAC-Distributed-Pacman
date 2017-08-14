package client.model.utils

import scala.collection.mutable.ListBuffer
import java.util.{Observable, Observer}

/**
  * This trait represent a timer.
  *
  * @author Margherita Pecorelli
  */
trait Timer extends Observable{

  /**
    *Counts for X milliseconds (input parameter) without doing anything.
    *
    * @param milliseconds - milliseconds to wait
    */
  def counts(milliseconds: Long): Unit

  override def addObserver(o: Observer): Unit
}

/**
  * This class represent a timer. When it finish to count, he notify the observer registered.
  *
  * @author Margherita Pecorelli
  */
case class TimerImpl() extends Timer{

  private val observers = ListBuffer.empty[Observer]

  val observable = this

  /**
    *Counts for X milliseconds (input parameter) without doing anything. When it ends, notify the observer.
    *
    * @param milliseconds - milliseconds to wait
    */
  override def counts(milliseconds: Long): Unit = {
    val thread = new Thread {
      override def run = {
        val time = System.currentTimeMillis()
        while(System.currentTimeMillis() < time + milliseconds) {}
        observers.foreach(o => o.update(observable,""))
      }
    }
    thread.start
  }

  override def addObserver(observer: Observer) =  observers += observer

}
