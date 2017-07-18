package client.communication.actor

import akka.actor.Actor
import akka.io.Tcp

/**
  * This actor manages the communication between all actor of client and the server.
  *
  * @author Giulia Lucchi
  */
class ToServerCommunication extends Actor {
  override def receive: Receive = {}
}
