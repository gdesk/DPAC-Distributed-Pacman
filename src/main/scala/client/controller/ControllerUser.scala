package client.controller

import client.model._

/**
  * This trait represents the controller's interface with which the view can interact.
  */
trait ControllerUser {

  def registration(name: String, username: String, email: String, password: String, confirmPassword: String): Boolean

  def login(username: String, Password: String): List[MatchResult]

  def getAllMatchesResults(username: String): List[MatchResult]

}

case class BaseControllerUser() extends ControllerUser {

  override def registration(name: String, username: String, email: String, password: String, confirmPassword: String) = ToClientCommunication registrtion(name, username, email, password, confirmPassword)

  override def login(username: String, password: String) = ToClientCommunication login(username, password)

  override def getAllMatchesResults(username: String) = ToClientCommunication getAllMatchesResults(username)
}