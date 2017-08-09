package client.communication.model.actor

import java.io.File

import akka.actor.{ActorSelection, ActorSystem, UntypedAbstractActor}
import com.typesafe.config.{Config, ConfigFactory}

import scala.util.parsing.json.JSONObject

import client.utils.ControllerObservable

/**
  * This actor manages the massage to send to server
  *
  * @author Giulia Lucchi
  */
class ToServerCommunication extends UntypedAbstractActor{
  val config: Config = ConfigFactory.parseFile(new File("src/main/resources/communication/configuration.conf"))
  val system: ActorSystem = ActorSystem.create("ServerSystem", config)
  val server: ActorSelection = system.actorSelection("akka.tcp://MyServerSystem@127.0.0.1:2552/user/myServerActor")

  val controllerObservable = ControllerObservable

  override def onReceive(message: Any): Unit = message match{
    case msg :JSONObject => msg.obj("object") match {
      case "friendRequest" =>{
        val username = msg.obj("senderRequest").asInstanceOf[String]
        controllerObservable.gameRequest("GameRequest", username)
      }
      case "friendResponse" =>{
        val response = msg.obj("responseRequest").asInstanceOf[Boolean]
       controllerObservable.gameResponse("GameResponse", response)
      }
      case _ => server ! msg.asInstanceOf[JSONObject]
    }
  }


}
