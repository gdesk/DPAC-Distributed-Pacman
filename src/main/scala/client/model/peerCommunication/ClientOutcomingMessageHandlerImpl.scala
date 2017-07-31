package client.model.peerCommunication
import java.util.{Observable, Observer}

import client.model.MatchImpl

/**
  * Created by Federica on 26/07/17.
  */
class ClientOutcomingMessageHandlerImpl extends Observable with ClientOutcomingMessageHandler {

  val character = MatchImpl.instance().myCharacter

  /**
    * this method allows to register controller
    * as model observer, so that:
    * - model is observable
    * - controller is observer of model
    *
    * @param observer
    */
  def addObserver(observer: Any): Unit =
    this.addObserver(observer)

  /**
    * method to notify controller and other peers
    * about the number of lives left to this peer
    *
    * @param arg
    */
  override def notifyRemainingLives(arg: Any): Unit = 
    notifyObservers("remainingLives", character)


  /**
    * method to notify controller and other peers
    * about the current total score of this peer
    *
    * @param arg
    */
  override def notifyScore(arg: Any): Unit =
    notifyObservers("score", character)


  /**
    * method to notify controller and other peers
    * about the current position of this peer
    *
    * @param arg
    */
  override def notifyMove(arg: Any): Unit =
    notifyObservers("move", character)

  /**
    * method to notify controller and other peers
    * about the current state (dead or alive) of this peer
    * @param arg
    */
  def notifyDeath(arg: scala.Any): Unit =
    notifyObservers("isDead", character)


}
