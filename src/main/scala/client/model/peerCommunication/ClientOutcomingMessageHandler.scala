package client.model.peerCommunication

import java.util.Observer

import client.model.character.Character
import client.model.utils.Lives

/**
  * Created by Federica on 24/07/17.
  *
  * this trait is useful to handle two types of communication:
  * - peer to peer (so that, a peer send info to others)
  * - peer to controller
  *
  */

trait ClientOutcomingMessageHandler  {

  /**
    * this method allows to register controller
    * as model observer, so that:
    * - model is observable
    * - controller is observer of model
    * @param observer
    */
  def addObserver(observer: Observer): Unit


  /**
    * method to notify controller and other peers
    * about the current position of this peer

    */
  def notifyMove(update: String, value: Point<Object, Object>): Unit


  /**
    * method to notify controller and other peers
    * about the current total score of this peer

    */

  def notifyScore(update: String, value: Int): Unit



  /**
    * method to notify controller and other peers
    * about the number of lives left to this peer

    */
  def notifyRemainingLives(update: String, value: Lives): Unit



  /**
    * method to notify controller and other peers
    * about the current state (dead or alive) of this peer

    */
  def notifyDeath(update: String, value: Boolean): Unit



}
