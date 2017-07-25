package client.communication.model.actor

import java.awt.Image
import java.io.File

import akka.actor.UntypedAbstractActor
import client.model.MatchResult

import scala.util.parsing.json.JSONObject

/**
  * The actor manages the interaction within  userManager and TeamManager.
  *
  * @author Giulia Lucchi
  */
class GameManager extends UntypedAbstractActor{
  override def onReceive(message: Any): Unit = message match{
    case msg: String => {
      val receiver = context.system actorSelection "user/toServerCommunication"
      receiver ! message.asInstanceOf[String]
    }

    case msg: List[Range] =>{
      val receiver = context.system actorSelection "/system/dsl/inbox-1"
      receiver ! msg.asInstanceOf[List[Range]]
    }

    case msg: JSONObject => {
      val receiver = context.system actorSelection "user/toServerCommunication"
      receiver ! msg.asInstanceOf[JSONObject]
    }
    case msg: Boolean => {
      val receiver = context.system actorSelection "/system/dsl/inbox-1"
      receiver ! msg.asInstanceOf[Boolean]
    }
    case msg: List[File] =>{
      val receiver = context.system actorSelection "/system/dsl/inbox-1"
      receiver ! msg.asInstanceOf[List[File]]
    }
    case msg: List[MatchResult] =>{
      val receiver = context.system actorSelection "/system/dsl/inbox-1"
      receiver ! msg.asInstanceOf[List[MatchResult]]
    }
  }
}
