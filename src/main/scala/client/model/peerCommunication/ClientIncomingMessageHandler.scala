package client.model.peerCommunication

import java.util.Observer

import io.reactivex.Flowable

/**
  * Created by Federica on 31/07/17.
  */


//observable
trait ClientIncomingMessageHandler {

  def addObserver(observer:Observer): Unit

  def updateCharacters(string: String): Unit


  /**
    * this method updates the remaining lives for this peer
    *

    */
  //def updateRemainingLives(arg:Any): Unit
  /**
    * this method updates the current score for this peer
    *

    */
   //def updateScore(character: Character): Unit

   //def updateScore(arg:Any):Unit
  /**
    * this method updates the current position for this peer
    *

    */
   //def updatePosition(arg:Any): Unit

  /**
    * this method updates the current state for this peer
    *

    */
   //def updateIsDead(arg:Any): Unit
}
