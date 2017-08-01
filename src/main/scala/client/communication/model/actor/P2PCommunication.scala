package client.communication.model.actor

import akka.actor.UntypedAbstractActor
import client.utils.ActorUtils
import network.client.P2P.bootstrap.{ClientWorkerThread, ServerBootstrap, ServerWorkerThread}
import network.client.P2P.game.ServerPlayingWorkerThread
import network.client.P2P.main.{Main, MatchHandler}

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
      case "startGame" =>
        new ServerBootstrap
        new ServerPlayingWorkerThread
        context.actorSelection(ActorUtils.TOSERVER_ACTOR) ! msg.asInstanceOf[JSONObject]

      case "otherPlayerIP" =>
        val IPList = msg.obj("playerList").asInstanceOf[List[String]] // lista con ip

        /*foreach su IPList{
         configuro i client di questo peer
        }*/

        IPList.foreach(new ClientWorkerThread(_))

        new MatchHandler

    }

  }
}

