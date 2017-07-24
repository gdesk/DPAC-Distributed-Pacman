package client.model.peerCommunication

import java.util.Observer

/**
  * Created by Federica on 24/07/17.
  *
  * this trait is useful to handle two types of communication:
  * - peer to peer (so that, a peer receives info from others)
  * - peer to controller
  */
trait ClientIncomingMessageHandler {

  /**
    * this method allows to register controller
    * as model observer, so that:
    * - model is observable
    * - controller is observer of model
    * @param observer
    */
  def addObserver(observer: Observer): Unit

  /**
    * this method updates the remaining lives for this peer
    * @param character
    */
  def updateRemainingLives(character: Character): Unit

  /**
    * this method updates the current state (dead or alive) for this peer
    * @param character
    */
  def updateDead(character: Character): Unit

  /**
    * this method updates the current score for this peer
    * @param character
    */
  def updateScore (character: Character): Unit

  /**
    * this method updates the current position for this peer
    * @param character
    */
  def updatePosition(character: Character): Unit
}
