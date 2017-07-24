package client.communication.model.actor

import akka.actor.UntypedAbstractActor

/**
  * The actor manages the interaction within  userManager and TeamManager.
  *
  * @author Giulia Lucchi
  */
class GameManager extends UntypedAbstractActor{
  override def onReceive(message: Any): Unit = message match{
    case "ranges" => {
      println("ci siamo")
      val receiver = context.system actorSelection "user/toServerCommunication"
      receiver ! message.asInstanceOf[String]
    }
    case msg: List[Range] =>{
      val receiver = context.system actorSelection "/system/dsl/inbox-1"
      receiver ! msg
    }
  }
}
