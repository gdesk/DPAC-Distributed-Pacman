package client.communication.model.actor

<<<<<<< HEAD



import java.io.File
import java.util.{Observable, Observer}

import akka.actor.{ActorSelection, ActorSystem, UntypedAbstractActor}



import com.typesafe.config.{Config, ConfigFactory}
=======
import akka.actor.{ActorSelection, UntypedAbstractActor}
>>>>>>> 95fe4562b271f2994c02bb491bf211214022ff35

import scala.util.parsing.json.JSONObject

/**


  * This actor manages the massage to send to server


  *
  * @author Giulia Lucchi
  */
<<<<<<< HEAD
class ToServerCommunication extends UntypedAbstractActor with Observable {
  val config: Config = ConfigFactory.parseFile(new File("src/main/resources/communication/configuration.conf"))
  val system: ActorSystem = ActorSystem.create("ServerSystem", config)
  val server: ActorSelection = system.actorSelection("akka.tcp://MyServerSystem@127.0.0.1:2552/user/myServerActor")

=======
class ToServerCommunication extends UntypedAbstractActor{
  val server: ActorSelection = context actorSelection "akka.tcp://DpacServer@10.201.100.206:2552/user/messageReceiver"
>>>>>>> 95fe4562b271f2994c02bb491bf211214022ff35

  override def onReceive(message: Any): Unit = message match{
    case msg :JSONObject => server ! msg.asInstanceOf[JSONObject]

<<<<<<< HEAD

=======
>>>>>>> 95fe4562b271f2994c02bb491bf211214022ff35
  }


}
