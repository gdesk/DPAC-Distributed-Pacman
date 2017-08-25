package client.model.peerCommunication
import java.util.{Observable, Observer}


/**
  * Created by Federica on 24/07/17.
  *
  * this trait is useful to handle communication
  * betweeen this peer and game controller (ControllerMatch)
  *
  */

class ClientOutcomingMessageHandlerImpl extends Observable with ClientOutcomingMessageHandler {

  var observers : List[Observer] = List.empty

  /**
    * method to register controller match
    * as observer of this class model (observable)
    * @param observer is an objects whose class type is controllerMatch
    */
  override def addObserver(observer: Observer): Unit = observers = observer :: observers

  /**
    * method to notify controller match that
    * every Peer has finished to be configured
    * and, thus, match can start
    */
  def startMatch(): Unit =
    observers.foreach(o => o.update(this, "StartMatch"))





}
