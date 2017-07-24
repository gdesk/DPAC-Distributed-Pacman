package client.model.peerCommunication

import java.util.Observer

/**
  * Created by Federica on 24/07/17.
  *
  * trait to handle
  */


trait ClientOutcomingMessageHandler {


  def addObserver(observer: Observer, arg: scala.Any): Unit

  def notifyRemainingLives(): Unit

  def notifyScore(): Unit

  def notifyPosition(): Unit

}
