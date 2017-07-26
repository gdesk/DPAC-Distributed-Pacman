package client.controller

import client.communication.model.ToClientCommunicationImpl
import client.model._

/**
  * This trait represents the controller's interface with which the view can interact.
  */
trait ControllerUser {

  def registration(name: String, username: String, email: String, password: String, confirmPassword: String): Boolean

  def login(username: String, Password: String): Option[List[MatchResult]]

  def getAllMatchesResults(username: String): List[MatchResult]

}

case class BaseControllerUser() extends ControllerUser {

  private val model = ToClientCommunicationImpl()

  override def registration(name: String, username: String, email: String, password: String, confirmPassword: String) = model registration (name, username, email, password, confirmPassword)

  override def login(username: String, password: String) = model login (username, password)

  override def getAllMatchesResults(username: String) = model getAllMatchesResults username
}
