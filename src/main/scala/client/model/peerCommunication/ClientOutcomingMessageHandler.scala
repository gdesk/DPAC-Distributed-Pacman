package client.model.peerCommunication

import java.util.Observer



/**
  * Created by Federica on 24/07/17.
  *
  * Model to communicate with match controller
  *
  * this trait is useful to handle two types of communication:
  * - peer to peer (so that, a peer send info to others)
  * - peer to controller
  *
  */

trait ClientOutcomingMessageHandler  {

  /**
    * method to register controller match
    * as observer of this class model (observable)
    * @param observer
    */
  def addObserver(observer: Observer): Unit

  /**
    * method to notify controller match that
    * every Peer has finished to be configured
    */
  def startMatch(): Unit


}
