package client.communication.model.actor

import akka.actor.UntypedAbstractActor

import scala.util.parsing.json.JSONObject

/**
  * Created by lucch on 27/07/2017.
  */
class FromServerCommunication2 extends UntypedAbstractActor{
  override def onReceive(message: Any): Unit = message match {
    case msg: JSONObject => {
      val receiver = context actorSelection "/system/dsl/inbox-1"
      receiver ! msg.asInstanceOf[JSONObject]
    }
  }
}
