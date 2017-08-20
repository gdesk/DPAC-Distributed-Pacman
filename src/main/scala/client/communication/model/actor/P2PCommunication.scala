package client.communication.model.actor


import java.rmi.registry.LocateRegistry

import akka.actor.UntypedAbstractActor
import client.controller.BaseControllerMatch
import client.model.peerCommunication.ClientOutcomingMessageHandlerImpl
import client.model.{MatchImpl, PlayerImpl}
import client.utils.ActorUtils
import network.client.P2P.bootstrap.{ClientBootstrap}
import network.client.P2P.utils.ExecutorServiceUtility

import scala.util.parsing.json.JSONObject

/**
  * This actor synchronizes and configures to start the P2P Communication.
  *
  * @author Giulia Lucchi
  *         Federica Pecci
  */
class P2PCommunication() extends UntypedAbstractActor {


  val executor = ExecutorServiceUtility.getIstance


  override def onReceive(message: Any): Unit = message match{

    case msg: JSONObject => msg.obj("object") match{
      case "startGame" => {
        //ricevo messaggio contente l'IP con cui configurare server
        val ip = PlayerImpl.ip
        //val server = ServerBootstrap.getIstance(ip)

        executor.initServerPlayingWorkerThread(ip)

        context.actorSelection(ActorUtils.TOSERVER_ACTOR) ! JSONObject(Map[String, String]("object" -> "serverIsRunning", "senderIP" -> ip))
      }
      case "clientCanConnect" => {

        //val info = new OtherCharacterInfo
        val matchHandler = new ClientOutcomingMessageHandlerImpl
        matchHandler.addObserver(BaseControllerMatch)
        val IPList = MatchImpl.allPlayersIp

        val filteredIpLis = IPList.filter(clientIp => clientIp != (PlayerImpl.ip))


        System.out.println("--LISTA IP--")
        filteredIpLis.foreach(println)

        filteredIpLis.foreach(clientIp => {
          //new ClientBootstrap(clientIp)
          //val registry = LocateRegistry.getRegistry(clientIp)
          executor.initClientPlayingWorkerThread(clientIp)

        })

        //notifico controller match
        matchHandler.startMatch()

      }
      case _ =>{}
    }

  }
}

