package client.model.peerCommunication

import java.util.Observer

/**
  * Created by Federica on 24/07/17.
  *
  * this trait is useful to handle communication
  * betweeen this peer and game controller (ControllerMatch)
  *
  */

trait ClientOutcomingMessageHandler  {

  /**
    * method to register controller match
    * as observer of this class model (observable)
    * @param observer is an objects whose class type is controllerMatch
    */
  def addObserver(observer: Observer): Unit

  /**
    * method to notify controller match that
    * every Peer has finished to be configured
    * and, thus, match can start
    */
  def startMatch(): Unit


}
