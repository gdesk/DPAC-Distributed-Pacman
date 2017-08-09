package client.communication.model.actor


import java.net.InetAddress
import java.rmi.registry.{LocateRegistry, Registry}

import akka.actor.UntypedAbstractActor
import client.model.peerCommunication.ClientIncomingMessageHandlerImpl
import client.utils.ActorUtils
import network.client.P2P.bootstrap.{ClientBootstrap, ServerBootstrap}
import network.client.P2P.utils.ExecutorServiceUtility
import network.client.rxJava.OtherCharacterInfo


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
        //ricevo messaggio contente l'IP con cui configurare server

        val ip = msg.obj("myPlayerIp").asInstanceOf[String] //todo prendi dalla classe player
        val singleton = ServerBootstrap.getIstance(ip)

        executor.initServerPlayingWorkerThread(ip, singleton.getRegistry, singleton.getRmiPort)

        context.actorSelection(ActorUtils.TOSERVER_ACTOR) ! msg.asInstanceOf[JSONObject]

      case "otherPlayerIP" =>
        val IPList = msg.obj("playerList").asInstanceOf[Set[String]] //todo prendi dalla classe player
        val info = new OtherCharacterInfo
        val handler = new ClientIncomingMessageHandlerImpl


        for(ip <- IPList){
          val registry = LocateRegistry.getRegistry(ip)
          new ClientBootstrap(ip)
          executor.initClientPlayingWorkerThread(ip, registry)

        }
        //todo per dire a marghe che la scermata view (loading...), può essere sostituita da (playgroung)
        //this.handler.startMatch("StartMatch");


    }

  }
}

