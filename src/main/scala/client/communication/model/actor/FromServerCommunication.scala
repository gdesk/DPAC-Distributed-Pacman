package client.communication.model.actor

import akka.actor.UntypedAbstractActor
import client.controller.BaseControllerMatch
import client.utils.{ActorUtils, ControllerObservable}

import scala.util.parsing.json.JSONObject

/**
  * This actor manage the message, that it's received from server.
  *
  * @author Giulia Lucchi
  */
class FromServerCommunication extends UntypedAbstractActor{
  val controllerObservable = ControllerObservable
  controllerObservable.addObserver(BaseControllerMatch.instance())

  override def onReceive(message: Any): Unit = message match {
    case msg: JSONObject => msg.obj("object") match {
      case "friendRequest" =>{
        val username = msg.obj("senderRequest").asInstanceOf[String]
        controllerObservable.gameRequest("GameRequest", username)
      }
      case "friendResponse" =>{
        val response = msg.obj("responseRequest").asInstanceOf[Boolean]
        controllerObservable.gameResponse("GameResponse", response)
        if(!response){
          val motivation = msg.obj("motivation").asInstanceOf[String]
          controllerObservable.exceptionMotivation("Motivation", motivation)
        }
      }
      case "playerInMatch" =>{
        val response = msg.obj("players").asInstanceOf[Int]
        controllerObservable.gameResponse("playerInMatch", Integer.valueOf(response))
      }
      case "notifySelection" =>{
        val response =  msg.obj("character").asInstanceOf[String]
        controllerObservable.gameResponse("notifySelection", response)

      }
      case _ => { val receiver = context actorSelection ActorUtils.INBOX_ACTOR
        receiver ! msg.asInstanceOf[JSONObject]

      }

    }
  }
}