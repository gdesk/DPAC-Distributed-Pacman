package client.model.peerCommunication

import java.util.{Observable, Observer}

/**
  * Created by Federica on 31/07/17.
  */
class ClientIncomingMessageHandlerImpl extends Observable with ClientIncomingMessageHandler {


  override def addObserver(observer:Observer){
    this.addObserver(observer)
  }

  /**
    * this method updates the remaining lives for this peer
    *
    * @param character
    */
  override def updateRemainingLives(character: Character): Unit =
    notifyObservers("remainingLives", character)

  /**
    * this method updates the current score for this peer
    *
    * @param character
    */
  override def updateScore(character: Character): Unit =
    notifyObservers("score", character)

  /**
    * this method updates the current position for this peer
    *
    * @param character
    */
  override def updatePosition(character: Character): Unit =
    notifyObservers("move", character)

  /**
    * this method updates the current state for this peer
    *
    * @param character
    */
  override def updateIsDead(character: Character): Unit =
    notifyObservers("isDead", character)
}
