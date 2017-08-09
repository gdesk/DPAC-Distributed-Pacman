package client.controller

import client.communication.model.ToClientCommunication
import client.model._

/**
  * This trait represents the controller's interface with which the view can interact.
  */
trait ControllerUser {

  def registration(name: String, username: String, email: String, password: String, confirmPassword: String): Boolean

  def login(username: String, password: String): Boolean

  def logout(): Boolean

  def player(): Player

  def model(model: ToClientCommunication): Unit

}

case class BaseControllerUser private() extends ControllerUser {

  private var _model: ToClientCommunication = null

  override def registration(name: String, username: String, email: String, password: String, confirmPassword: String) = _model registration (name, username, email, password, confirmPassword)

  override def login(username: String, password: String) = _model login (username, password)

  override def logout = _model logout()

  override def player = PlayerImpl instance()

  override def model(model: ToClientCommunication) = _model = model
}

object BaseControllerUser {

  private var _instance: BaseControllerUser = null

  def instance(): BaseControllerUser = {
    if(_instance == null) _instance = BaseControllerUser()
    _instance
  }

}