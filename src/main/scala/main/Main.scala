package main

import javax.swing.SwingUtilities

import client.communication.model.ToClientCommunicationImpl
import client.controller._
import client.model.peerCommunication.{ClientIncomingMessageHandler, ClientIncomingMessageHandlerImpl, ClientOutcomingMessageHandler, ClientOutcomingMessageHandlerImpl}
import client.view.MainFrame

object Main extends App {

  val controllerCharacter: ControllerCharacter = BaseControllerCharacter.instance
  val controllerMatch: ControllerMatch = BaseControllerMatch.instance
  val controllerUser: ControllerUser = BaseControllerUser.instance

  val model = ToClientCommunicationImpl()

  val ci: ClientIncomingMessageHandler = new ClientIncomingMessageHandlerImpl
  val co: ClientOutcomingMessageHandler = new ClientOutcomingMessageHandlerImpl

  controllerMatch.model(model)
  controllerUser.model(model)

  ci.addObserver(controllerCharacter)
  co.addObserver(controllerMatch)

  SwingUtilities.invokeLater(new Runnable() {
    override def run(): Unit = {
      MainFrame.getInstance
    }
  })

}
