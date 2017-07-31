package client.communication.model.actor

<<<<<<< HEAD
import java.io.File

import akka.actor.{ActorSelection, ActorSystem, UntypedAbstractActor}
=======
import java.awt.Image
import java.io.File

import akka.actor.{ActorSelection, ActorSystem, UntypedAbstractActor}
import client.model.MatchResult
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
import com.typesafe.config.{Config, ConfigFactory}

import scala.util.parsing.json.JSONObject

/**
<<<<<<< HEAD
  * This actor manages the massage to send to server
=======
  * This actor manages the communication between all actor of client and the server.
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
  *
  * @author Giulia Lucchi
  */
class ToServerCommunication extends UntypedAbstractActor {
<<<<<<< HEAD
  val config: Config = ConfigFactory.parseFile(new File("src/main/resources/communication/configuration.conf"))
  val system: ActorSystem = ActorSystem.create("ServerSystem", config)
  val server: ActorSelection = system.actorSelection("akka.tcp://MyServerSystem@127.0.0.1:2552/user/myServerActor")

  override def onReceive(message: Any): Unit = message match{
    case msg :JSONObject => server ! msg.asInstanceOf[JSONObject]

  }
=======

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
        val receiver = context.system actorSelection "user/teamManager"
        receiver ! msg.asInstanceOf[JSONObject]
      }
      case "playgroundChosen"=>{
        val playground: String = msg.obj("playground").asInstanceOf[String]
        val receiver = context.system actorSelection "/system/dsl/inbox-1"
        receiver ! playground.asInstanceOf[String]
      }
    }
    case msg: String => {
      println("ARRIVATO .... TO SEND SERVER  " +msg)
      server ! msg
    }
    case msg: Boolean => {
      val receiver = context.system actorSelection "user/accessManager"
      receiver ! msg.asInstanceOf[Boolean]
    }

  }

>>>>>>> 68886a0350f42d164cd02a62837241401739b052
}
