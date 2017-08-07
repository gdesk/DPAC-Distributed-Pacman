package client.model.peerCommunication

import java.util.{Observable, Observer}

import client.controller.ControllerCharacter
import client.model.character.Character
import io.reactivex.Flowable


/**
  * Created by Federica on 31/07/17.
  */
class ClientIncomingMessageHandlerImpl extends Observable with ClientIncomingMessageHandler {


  override def addObserver(observer: Observer) {
    if (observer.isInstanceOf[ControllerCharacter]) {
      this.addObserver(observer)
    }
  }

  def updateCharacters(string: String): Unit = {
    notifyObservers(string)



    /**
    * this method updates the remaining lives for this peer
    *
    *
    */
  //def updateRemainingLives(arg: Any): Unit =
  //notifyObservers("remainingLives", arg)

  /**
    * this method updates the current score for this peer
    *
    *
    */
  //override def updateScore(character: Character): Unit =
  //def updateScore(arg: Any): Unit =

  //notifyObservers("score", arg)

  /**
    * this method updates the current position for this peer
    *
    *
    */
  //def updatePosition(arg: Any): Unit =
  //notifyObservers("move", arg)

  /**
    * this method updates the current state for this peer
    *
    *
    */
  //def updateIsDead(arg: Any): Unit =
  //notifyObservers("isDead", arg)


    /*val pair: (String, String) = if (arg.isInstanceOf[(String, String)]) {
      arg.asInstanceOf[(String, String)]
    } else {
      null
    }

    if (pair != null) {
      pair._1 match {

        case "currentPositionX" => notifyObservers("move", arg)
        case "currentPositionY" => notifyObservers("move", arg)
        case "currentScore" => notifyObservers("score", arg)
        case "currentLives" => notifyObservers("remainingLives", arg)
        case "currentIsDead" => notifyObservers("isDead", arg)
      }
    }(())*/

  }
}
