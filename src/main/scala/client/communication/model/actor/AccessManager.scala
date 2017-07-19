package client.communication.model.actor

import akka.actor.Actor

import scala.util.parsing.json.JSONObject

/**
  * The actor manages the connection, registration, login and logout.
  *
  * @author Giulia Lucchi
  */
class AccessManager extends Actor {
  override def receive = {
    case JSONObject(message) => {
      println("ciaociaociao")
      context.actorSelection("user/toServerCommunication")! message.asInstanceOf[JSONObject]
    }
  }
}
