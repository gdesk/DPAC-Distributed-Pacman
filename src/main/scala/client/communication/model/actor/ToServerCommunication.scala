package client.communication.model.actor

import java.awt.Image
import java.io.File

import akka.actor.{ActorSelection, ActorSystem, UntypedAbstractActor}
import client.model.{Direction, MatchResult}
import com.typesafe.config.{Config, ConfigFactory}

import scala.util.parsing.json.JSONObject

/**
  * This actor manages the communication between all actor of client and the server.
  *
  * @author Giulia Lucchi
  */
class ToServerCommunication extends UntypedAbstractActor {

  val config: Config = ConfigFactory.parseFile(new File("src/main/resources/communication/configuration.conf"))
  val system: ActorSystem  = ActorSystem.create("ServerSystem", config)
  val server: ActorSelection = system.actorSelection("akka.tcp://MyServerSystem@127.0.0.1:2552/user/myServerActor")

  override def onReceive(message: Any): Unit = message match {
    case msg: JSONObject => msg.obj("object") match {
      case "newUser" =>  server ! msg.asInstanceOf[JSONObject]
      case "login" =>  server ! msg.asInstanceOf[JSONObject]
      case "chooseCharacter" =>  server ! msg.asInstanceOf[JSONObject]
      case "choosePlayground" => server ! msg.asInstanceOf[JSONObject]
      case "matchResult" => server ! msg.asInstanceOf[JSONObject]
      case "allMatchResult" => server ! msg.asInstanceOf[JSONObject]
      case "matches" =>{
        val receiver = context.system actorSelection "user/accessManager"
        receiver ! msg.asInstanceOf[JSONObject]
      }
      case "ranges" =>{
        val ranges = msg.obj("list").asInstanceOf[List[Range]]
        val receiver = context.system actorSelection "user/gameManager"
        receiver ! ranges
      }
      case "characterToChoose" =>{
        val characterToChoose: Map[String, Image] = msg.obj("map").asInstanceOf[Map[String, Image]]
        val receiver = context.system actorSelection "user/gameManager"
        receiver ! characterToChoose
      }
      case "playgrounds"=>{
        val playgrounds: List[File] = msg.obj("list").asInstanceOf[List[File]]
        val receiver = context.system actorSelection "user/gameManager"
        receiver ! playgrounds
      }
      case "listMatch" =>{
        val playgrounds: List[MatchResult] = msg.obj("list").asInstanceOf[List[MatchResult]]
        val receiver = context.system actorSelection "user/gameManager"
        receiver ! playgrounds
      }
      case "teamCharacter" =>{
        val characters: Map[String, Map[Direction,Image]] = msg.obj("map").asInstanceOf[Map[String, Map[Direction,Image]]]
        val receiver = context.system actorSelection "/system/dsl/inbox-1"
        receiver ! characters
      }
      case "playgroundChosen"=>{
        val playground: String = msg.obj("playground").asInstanceOf[String]
        val receiver = context.system actorSelection "/system/dsl/inbox-1"
        receiver ! playground.asInstanceOf[String]
      }
    }
    case msg: Boolean => {
      val receiver = context.system actorSelection "user/accessManager"
      receiver ! msg.asInstanceOf[Boolean]
    }
    case msg: String => {
      server ! msg
    }
  }

}
