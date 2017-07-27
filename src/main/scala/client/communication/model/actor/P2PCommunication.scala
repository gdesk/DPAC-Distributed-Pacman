package client.communication.model.actor

import akka.actor.UntypedAbstractActor

/**
  * This actor synchronizes and configures to start the P2P Communication.
  *
  * @author Giulia Lucchi
  *         Federica Pecci
  */
class P2PCommunication extends UntypedAbstractActor {
  override def onReceive(message: Any): Unit = ???
}
