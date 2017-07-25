package client.communication.model.actor

import java.awt.Image

import akka.actor.UntypedAbstractActor
import client.model.Direction

import scala.util.parsing.json.JSONObject

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
    case msg: JSONObject =>msg.obj("object") match{
      case "teamCharacter" =>{
        val characters: Map[String, Map[Direction,Image]] = msg.obj("map").asInstanceOf[Map[String, Map[Direction,Image]]]
        val receiver = context.system actorSelection "/system/dsl/inbox-1"
        receiver ! characters
      }
    }

  }
}
