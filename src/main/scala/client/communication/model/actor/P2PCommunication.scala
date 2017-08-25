package client.communication.model.actor

import akka.actor.UntypedAbstractActor
import client.controller.BaseControllerMatch
import client.model.peerCommunication.ClientOutcomingMessageHandlerImpl
import client.model.{MatchImpl, PlayerImpl}
import client.utils.ActorUtils
import network.client.P2P.utils.ExecutorServiceUtility
import scala.util.parsing.json.JSONObject

/**
  * This actor is useful to configure peer to peer
  * communication: it receives messages from server
  *
  * @author Giulia Lucchi
  *         Federica Pecci
  */
class P2PCommunication() extends UntypedAbstractActor {

  val executor: ExecutorServiceUtility = ExecutorServiceUtility.getIstance

  /**
    * this method contains two type of message:
    * - first one (startGame) to configure server side of this peer
    * - second one (clientCanConnect) to configure client sides of this peer
    * (this message will be received only when every other peer has finished to configure
    * its server side)
    * @param message
    */
  override def onReceive(message: Any): Unit = message match{

    case msg: JSONObject => msg.obj("object") match{

      case "startGame" => {
        val ip = PlayerImpl.ip

        executor.initServerPlayingWorkerThread()
        context.actorSelection(ActorUtils.TOSERVER_ACTOR) ! JSONObject(Map[String, String]("object" -> "serverIsRunning", "senderIP" -> ip))
      }

      case "clientCanConnect" => {

        val matchHandler = new ClientOutcomingMessageHandlerImpl
        matchHandler.addObserver(BaseControllerMatch)

        val IPList = MatchImpl.allPlayersIp
        val filteredIpLis = IPList.filter(clientIp => clientIp != PlayerImpl.ip)

        filteredIpLis.foreach(clientIp => {
          executor.initClientPlayingWorkerThread(clientIp)

        })

        matchHandler.startMatch()

      }

      case _ =>{}
    }

  }
}

