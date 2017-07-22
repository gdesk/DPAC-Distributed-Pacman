package client.communication.model.actor

import akka.actor.UntypedAbstractActor

import scala.util.parsing.json.JSONObject

/**
  * The actor manages the connection, registration, login and logout.
  *
  * @author Giulia Lucchi
  */
class AccessManager extends UntypedAbstractActor {
  override def onReceive(message: Any): Unit = message match{
    case msg : JSONObject => {
      val receiver =  context.system actorSelection "user/toServerCommunication"
      receiver ! msg.asInstanceOf[JSONObject]
    }
  }
}
