package client.communication.model.actor

import java.io.File

import akka.actor.UntypedAbstractActor
import client.model.MatchResult

import scala.util.parsing.json.JSONObject

/**
  * The actor manages the game's information to send to server and to receive from server.
  *
  * @author Giulia Lucchi
  */
class GameManager extends UntypedAbstractActor{
  override def onReceive(message: Any): Unit = message match{
    case msg: String => {
      println("REQUEST RECEIVER "+ msg.asInstanceOf[String])
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
