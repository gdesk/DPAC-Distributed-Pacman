package client.model.peerCommunication
import java.util.{Observable, Observer}

import client.controller.ControllerMatch


/**
  * Created by Federica on 26/07/17.
  * class to communicate with controller match
  */

class ClientOutcomingMessageHandlerImpl extends Observable with ClientOutcomingMessageHandler {

  /**
    * method to register controller match
    * as observer of this class model (observable)
    * @param observer
    */
  def addObserver(observer: Any): Unit =
    if (observer.isInstanceOf[ControllerMatch]) {
      this.addObserver(observer)
    }

  /**
    * method to notify controller match that
    * every Peer has finished to be configured
    */
  def startMatch(): Unit =
    notifyObservers("StartMatch")




}
