package client.communication.model.actor

import akka.actor.UntypedAbstractActor

/**
  * The actore manages the creation of team.
  * @author Giulia Lucchi
  */
class TeamManager extends UntypedAbstractActor {
  override def onReceive(message: Any): Unit = message match {
    case msg : String => {
      val receiver = context.system actorSelection "user/toServerCommunication"
      receiver ! message.asInstanceOf[String]
    }
  }
}
