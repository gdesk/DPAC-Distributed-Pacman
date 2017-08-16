package client.model.peerCommunication
import java.util.{Observable, Observer}



/**
  * Created by Federica on 26/07/17.
  * class to communicate with controller match
  */

class ClientOutcomingMessageHandlerImpl extends Observable with ClientOutcomingMessageHandler {

  var observers : List[Observer] = List.empty

  /**
    * method to register controller match
    * as observer of this class model (observable)
    * @param observer
    */
  override def addObserver(observer: Observer): Unit = observers = observer :: observers

  /**
    * method to notify controller match that
    * every Peer has finished to be configured
    */
  override def startMatch() = observers.foreach(o => o.update(this, "StartMatch"))

}
