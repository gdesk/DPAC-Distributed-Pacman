package client.communication.model.actor

<<<<<<< HEAD

=======
>>>>>>> f38ed80253e0f1f45844160f8f2652e380e426a2
import java.io.File
import java.util.{Observable, Observer}

import akka.actor.{ActorSelection, ActorSystem, UntypedAbstractActor}
<<<<<<< HEAD

=======
>>>>>>> f38ed80253e0f1f45844160f8f2652e380e426a2
import com.typesafe.config.{Config, ConfigFactory}

import scala.util.parsing.json.JSONObject

/**
<<<<<<< HEAD

  * This actor manages the massage to send to server

=======
  * This actor manages the massage to send to server
>>>>>>> f38ed80253e0f1f45844160f8f2652e380e426a2
  *
  * @author Giulia Lucchi
  */
class ToServerCommunication extends UntypedAbstractActor with Observable {
  val config: Config = ConfigFactory.parseFile(new File("src/main/resources/communication/configuration.conf"))
  val system: ActorSystem = ActorSystem.create("ServerSystem", config)
  val server: ActorSelection = system.actorSelection("akka.tcp://MyServerSystem@127.0.0.1:2552/user/myServerActor")

<<<<<<< HEAD
  override def onReceive(message: Any): Unit = message match{
    case msg :JSONObject => server ! msg.asInstanceOf[JSONObject]

=======
  var observers: List[Observer]= List.empty

  override def onReceive(message: Any): Unit = message match{
    case msg :JSONObject => msg.obj("object") match {
      case "friendRequest" =>{
        val username = msg.obj("senderRequest").asInstanceOf[String]
        observers.foreach(o =>{
          o.update(this,("GameRequest",username))
        })
      }
      case "friendResponse" =>{
        val response = msg.obj("responseRequest").asInstanceOf[Boolean]
        observers.foreach(o =>{
          o.update(this,("GameResponse", response))
        })
      }
      case _ => server ! msg.asInstanceOf[JSONObject]
    }
>>>>>>> f38ed80253e0f1f45844160f8f2652e380e426a2
  }


  override def addObserver(observer: Observer): Unit ={
    observer :: observers
  }
}
