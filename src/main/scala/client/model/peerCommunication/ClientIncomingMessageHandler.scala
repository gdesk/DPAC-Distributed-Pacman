package client.model.peerCommunication

import java.util.Observer

/**
  * Created by Federica on 24/07/17.
  *
  * class to handle different type message
  * that a peer can send to others
  */
trait ClientIncomingMessageHandler {

  def addObserver(observer: Observer): Unit

  def setRemainingLives(character: Character): Unit

  def setDead(character: Character): Unit

  def setScore (character: Character): Unit

  def setPosition(character: Character): Unit
}
