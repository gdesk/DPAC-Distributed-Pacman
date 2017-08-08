package client.model.peerCommunication
import java.util.{Observable, Observer}

import client.model.MatchImpl
import client.model.character.Character
import client.model.utils.{Lives, PointImpl}

/**
  * Created by Federica on 26/07/17.
  */
class ClientOutcomingMessageHandlerImpl extends Observable with ClientOutcomingMessageHandler {

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
    * about the current position of this peer
    *

    */
  override def notifyMove(ip: String, update: String, value: PointImpl[Integer, Integer]): Unit =
    notifyObservers(ip: String, update: String, value: PointImpl[Integer, Integer])

  /**
    * method to notify controller and other peers
    * about the current total score of this peer
    *

    */
  override def notifyScore(ip: String, update: String, value: Int): Unit =
    notifyObservers(ip: String, update: String, value: Int)

  /**
    * method to notify controller and other peers
    * about the number of lives left to this peer
    *
    */
  def notifyRemainingLives(ip: String, update: String, value: Lives): Unit =
    notifyObservers(ip: String, update: String, value: Lives)

  /**
    * method to notify controller and other peers
    * about the current state (dead or alive) of this peer

    */
  def notifyDeath(ip: String, update: String, value: Boolean): Unit =
    notifyObservers(ip: String, update: String, value: Boolean)


}
