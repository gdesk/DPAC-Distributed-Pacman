package client.communication.model.actor

import akka.actor.UntypedAbstractActor
import client.utils.ActorUtils

import scala.util.parsing.json.JSONObject

/**
  * This actor manage the message, that it's received from server.
  *
  * @author Giulia Lucchi
  */
class FromServerCommunication extends UntypedAbstractActor{
  override def onReceive(message: Any): Unit = message match {
    case msg: JSONObject => {
      val receiver = context actorSelection ActorUtils.INBOX_ACTOR
      receiver ! msg.asInstanceOf[JSONObject]
    }
  }
}
