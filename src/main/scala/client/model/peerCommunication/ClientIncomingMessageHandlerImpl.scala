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

    */
   def updateRemainingLives(arg: Any): Unit =
    notifyObservers("remainingLives", arg)

  /**
    * this method updates the current score for this peer
    *

    */
  //override def updateScore(character: Character): Unit =
   def updateScore(arg: Any): Unit =

    notifyObservers("score", arg)

  /**
    * this method updates the current position for this peer
    *

    */
   def updatePosition(arg: Any): Unit =
    notifyObservers("move", arg)

  /**
    * this method updates the current state for this peer
    *

    */
   def updateIsDead(arg: Any): Unit =
    notifyObservers("isDead", arg)
}
