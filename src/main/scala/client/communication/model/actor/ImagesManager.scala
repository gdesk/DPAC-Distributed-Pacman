package client.communication.model.actor

import java.awt.Image

import akka.actor.UntypedAbstractActor

/**
  * @author Giulia Lucchi
  */
class ImagesManager extends UntypedAbstractActor {
  override def onReceive(message: Any): Unit = {
    case msg: String => {
      val receiver = context.system actorSelection "user/toServerCommunication"
      receiver ! message.asInstanceOf[String]
    }
    case msg: Map[String, Image] =>{
      val receiver = context.system actorSelection "/system/dsl/inbox-1"
      receiver ! msg.asInstanceOf[Map[String, Image]]
    }
  }
}
