package client.model.peerCommunication

import java.util.{Observable, Observer}

import client.controller.ControllerCharacter


/**
  * Created by Federica on 31/07/17.
  *
  * class to communicate with controller character,
  * so that game view and model can be updated with other
  * character info
  */
class ClientIncomingMessageHandlerImpl extends Observable with ClientIncomingMessageHandler {


  /**
    * method to register controller character
    * as observer of this class model (observable)
    * @param observer
    */
  override def addObserver(observer: Observer) {
    if (observer.isInstanceOf[ControllerCharacter]) {
      this.addObserver(observer)
    }
  }

  /**
    * method that notify controller character
    * with updated info (score, lives,...) of other character,
    * so that character game view and model can be updated
    * @param arg
    */
  def updateGameView(arg: Any): Unit =
    notifyObservers(arg: Any)


}
