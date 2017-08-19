package client.communication.model.actor

import akka.actor.{ActorSelection, UntypedAbstractActor}
import client.utils.ActorUtils
import client.view.Utils

import scala.util.parsing.json.JSONObject

/**
  * This actor manages the massage to send to server
  *
  * @author Giulia Lucchi
  */
class ToServerCommunication extends UntypedAbstractActor{
  var serverIP: String = ActorUtils.serverIP
  val server: ActorSelection = context actorSelection "akka.tcp://DpacServer@"+serverIP+":2552/user/messageReceiver"

  override def onReceive(message: Any): Unit = message match{
    case msg :JSONObject => server ! msg.asInstanceOf[JSONObject]
  }

}