package client.communication.model.actor

import akka.actor.UntypedAbstractActor

/**
  * Created by lucch on 09/08/2017.
  */
class FakeServer extends UntypedAbstractActor{
  override def onReceive(message: Any): Unit = {

  }
}
