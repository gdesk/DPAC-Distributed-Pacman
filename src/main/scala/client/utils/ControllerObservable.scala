package client.utils

import java.util.{Observable, Observer}

/**
  * Created to notify to the controller the messages incoming from server.
  *
  * @author Giulia Lucchi
  */
object ControllerObservable extends Observable{

  var observers: List[Observer]= List.empty

  /**
    * Adds the observer to list.
    *
    * @param observer controller
    */
  override def addObserver(observer: Observer): Unit ={
    observers = observer :: observers
  }

  /**
    * Notifies the message to the controller
    *
    * @param message message's type
    * @param request string related to the message
    */
  def gameRequest(message: String, request: String): Unit = {
    observers.foreach(o =>{
      o.update(this,(message,request))
    })
  }

  /**
    * Notifies a pair (string and any) as message to the controller
    *
    * @param message message's type
    * @param response object related to the message
    */
  def gameResponse(message: String, response: Any): Unit = {
    observers.foreach(o =>{
      o.update(this,(message, response))
    })
  }

  /**
    * Notifies a single message to the controller
    *
    * @param message
    */
  def notifyOne(message: String): Unit = {
    observers.foreach(o =>{
      o.update(this,message)
    })
  }
}
