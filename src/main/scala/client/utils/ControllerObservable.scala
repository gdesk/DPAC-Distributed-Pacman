package client.utils

import java.util.{Observable, Observer}

/**
  * Create to notify controller to manage the friend's invite in the current match
  *
  * @author Giulia Lucchi
  */
object ControllerObservable extends Observable{

  var observers: List[Observer]= List.empty

  override def addObserver(observer: Observer): Unit ={
    observers = observer :: observers
  }

  def gameRequest(message: String, username: String): Unit = {
    observers.foreach(o =>{
      o.update(this,(message,username))
    })
  }

  def gameResponse(message: String, response: Any): Unit = {
    observers.foreach(o =>{
      o.update(this,(message, response))
    })
  }

  def notifyOne(message: String): Unit = {
    observers.foreach(o =>{
      o.update(this,message)
    })
  }


  def exceptionMotivation(message: String, motivation: String): Unit ={
    observers.foreach(o =>{
      o.update(this,(message, motivation))
    })
  }

}
