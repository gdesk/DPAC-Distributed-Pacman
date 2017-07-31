package client.model.peerCommunication

import java.util.Observer

/**
  * Created by Federica on 31/07/17.
  */
trait ClientIncomingMessageHandler {
   def addObserver(observer:Observer): Unit
  /**
    * this method updates the remaining lives for this peer
    *
    * @param character
    */
  def updateRemainingLives(character: Character): Unit
  /**
    * this method updates the current score for this peer
    *
    * @param character
    */
   def updateScore(character: Character): Unit
  /**
    * this method updates the current position for this peer
    *
    * @param character
    */
   def updatePosition(character: Character): Unit

  /**
    * this method updates the current state for this peer
    *
    * @param character
    */
   def updateIsDead(character: Character): Unit
}
