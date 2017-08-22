package client.model.utils

import scala.collection.mutable.ListBuffer
import java.util.{Observable, Observer}

/**
  * Represents a timer.
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

  /**
    * Adds an observer to the observers list.
    *
    * @param observer - the observer to add.
    */
  override def addObserver(observer: Observer): Unit

}

/**
  * Represents the implementation of a timer.
  *
  * @author Margherita Pecorelli
  */
case class TimerImpl() extends Timer{

  private val observers = ListBuffer.empty[Observer]
  private val observable = this

  /**
    *Counts for X milliseconds (input parameter) without doing anything. When it ends, notify the observer.
    *
    * @param milliseconds - milliseconds to wait
    */
  override def counts(milliseconds: Long) = {
    new Thread {
      override def run = {
        val time = System.currentTimeMillis
        while(System.currentTimeMillis < time + milliseconds) {}
        observers.foreach(o => o.update(observable,""))
      }
    }.start
  }

  /**
    * Adds an observer to the observers list.
    *
    * @param observer - the observer to add.
    */
  override def addObserver(observer: Observer) = observers += observer

}
