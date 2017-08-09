package client.communication.model.actor

import akka.actor.UntypedAbstractActor
import client.utils.ActorUtils

import scala.util.parsing.json.JSONObject

/**
  * This actor synchronizes and configures to start the P2P Communication.
  *
  * @author Giulia Lucchi
  *         Federica Pecci
  */
class P2PCommunication extends UntypedAbstractActor {
  override def onReceive(message: Any): Unit = message match{
    case msg: JSONObject => msg.obj("object") match{
      case "startGame" => {
        //CONFIGURAZIONE SERVER DEL PEER (FEDE)
        context.actorSelection(ActorUtils.TOSERVER_ACTOR) ! msg.asInstanceOf[JSONObject]
      }
      case "otherPlayerIP" => {
        val IPList = msg.obj("playerList").asInstanceOf[List[String]] // lista con ip
        //poi fai le tue cose
      }

    }

  }
}

