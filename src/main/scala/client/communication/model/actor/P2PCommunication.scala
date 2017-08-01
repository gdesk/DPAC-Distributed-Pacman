package client.communication.model.actor

import akka.actor.UntypedAbstractActor
import client.utils.ActorUtils
import network.client.P2P.bootstrap.{ClientBootstrap, ServerBootstrap}
import network.client.P2P.game.{ClientPlayingWorkerThread, ServerPlayingWorkerThread}
import network.client.P2P.main.MatchHandler
import network.client.P2P.utils.ExecutorServiceUtility

import scala.util.parsing.json.JSONObject

/**
  * This actor synchronizes and configures to start the P2P Communication.
  *
  * @author Giulia Lucchi
  *         Federica Pecci
  */
class P2PCommunication extends UntypedAbstractActor {

  val executor = new ExecutorServiceUtility

  override def onReceive(message: Any): Unit = message match{


    case msg: JSONObject => msg.obj("object") match{
      case "startGame" =>
        new ServerBootstrap
        executor.initServerPlayingWorkerThread()

        context.actorSelection(ActorUtils.TOSERVER_ACTOR) ! msg.asInstanceOf[JSONObject]

      case "otherPlayerIP" =>
        val IPList = msg.obj("playerList").asInstanceOf[Set[String]]

        IPList.foreach(_ -> { new ClientBootstrap(_)
          executor.initClientPlayingWorkerThread()
        });


    }

  }
}

