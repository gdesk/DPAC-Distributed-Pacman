package client.communication.model.actor

import akka.actor.UntypedAbstractActor

import scala.util.parsing.json.JSONObject

/**
  * The actor manages the connection, registration, login and logout.
  *
  * @author Giulia Lucchi
  */
object AccessManager{
  var reg : Option[Boolean] = None
}
class AccessManager extends UntypedAbstractActor {
  override def onReceive(message: Any): Unit = message match{
    case msg : JSONObject => {
      if(msg.obj("object").equals("user")) {
        println("ENTRA access manager JSON")
        val receiver = context.system actorSelection "user/toServerCommunication"
        receiver ! msg.asInstanceOf[JSONObject]
        //receiver ! true  //PER PROVARE FUNZIONAMENTO Inbox
      }
    }
    case msg: Boolean => {
      println("ENTRA access manager BOOLEAN")
      val receiver =  context.system actorSelection "/system/dsl/inbox-1"
      receiver ! msg.asInstanceOf[Boolean]
    }
  }

}