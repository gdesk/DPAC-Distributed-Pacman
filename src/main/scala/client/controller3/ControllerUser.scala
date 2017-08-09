package client.controller3

import client.communication.model.ToClientCommunicationImpl
import client.model._

/**
  * This trait represents the controller's interface with which the view can interact.
  */
trait ControllerUser {

  def registration(name: String, username: String, email: String, password: String, confirmPassword: String): Boolean

  def login(username: String, Password: String): Boolean

  def getAllMatchesResults(username: String): List[MatchResult] // ORA C'è PLAYER()

  def logout()

  def getPlayerUsername(): String // non c'è -> c'è players

  //def per settare e ritornare il modello

}

case class BaseControllerUser private(private val model: ToClientCommunicationImpl) extends ControllerUser {
  override def registration(name: String, username: String, email: String, password: String, confirmPassword: String) = model registration (name, username, email, password, confirmPassword)

  override def login(username: String, password: String) = true //model login (username, password) //TODO cambia

  override def getAllMatchesResults(username: String) = List(new MatchResultImpl(true, 210)) //model getAllMatchesResults username //TODO cambia

  override def logout() =  print("logout") //model logout

  override def getPlayerUsername() = "chia" //TODO cambia
}

object BaseControllerUser {
  private val _instance = new BaseControllerUser(new ToClientCommunicationImpl())
  def instance() = _instance
}