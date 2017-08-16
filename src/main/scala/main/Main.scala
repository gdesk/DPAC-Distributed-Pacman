package main

import javax.swing.SwingUtilities

import client.communication.model.ToClientCommunicationImpl
import client.controller._
import client.model.PlayerImpl
import client.model.peerCommunication.{ClientIncomingMessageHandler, ClientIncomingMessageHandlerImpl, ClientOutcomingMessageHandler, ClientOutcomingMessageHandlerImpl}
import client.view.MainFrame
import network.client.P2P.bootstrap.ServerBootstrap
import network.client.P2P.game.{PeerRegisterHandlerImpl, ServerPlayingWorkerThread}
import network.client.P2P.utils.ExecutorServiceUtility

object Main extends App {
  println("[ DPACS - Distributed Pacman ]")
  println("[ Version 1.0.0 - August 2017 ]")
  println()
  println("[ Project for Software System Development course @ Unibo - Ingegneria e Scienze Informatiche - A.Y. 2016/17 ]")
  println("[ Developed by Manuel Bottazzi, Giulia Lucchi, Federica Pecci, Margherita Pecorelli & Chiara Varini ]")
  println()

  val controllerCharacter: ControllerCharacter = BaseControllerCharacter
  val controllerMatch: ControllerMatch = BaseControllerMatch
  val controllerUser: ControllerUser = BaseControllerUser

  val model = ToClientCommunicationImpl()

  val ci: ClientIncomingMessageHandler = new ClientIncomingMessageHandlerImpl

  controllerCharacter.setModel(new PeerRegisterHandlerImpl)
  controllerMatch.setModel(model)
  controllerUser.setModel(model)

  ci.addObserver(controllerCharacter)

  SwingUtilities.invokeLater(new Runnable() {
    override def run(): Unit = {
      MainFrame.getInstance
    }
  })

}
