package client.controller

import java.util.Date

import client.communication.model.ToClientCommunication
import client.model._

/**
  * Represents the controller for the user management.
  *
  * @author Margherita Pecorelli
  */
trait ControllerUser {

  /**
    * Tells the model all parameters for a new registration.
    *
    * @param name - the user'a name.
    * @param username - the user's username.
    * @param email - the user'a mail.
    * @param password - the user's accont password.
    * @param confirmPassword - the confirm of the password.
    *
    * @return true if the registration is successful, false otherwise.
    */
  def registration(name: String, username: String, email: String, password: String, confirmPassword: String): Boolean

  /**
    * Tells the model all parameters for a login.
    *
    * @param username - the account's username.
    * @param password - the account's password.
    *
    * @return true if the login is successful, false otherwise.
    */
  def login(username: String, password: String): Boolean

  /**
    * Tells the model to logout the user.
    *
    * @return true if the logout is successful, false otherwise.
    */
  def logout: Boolean

  /**
    * Tells the model the last match's result to save.
    *
    * @param lastMatchResult - the last match's result to save.
    */
  def saveMatch(lastMatchResult: MatchResult)

  /**
    * Returns the player of the user.
    *
    * @return the player of the user.
    */
  def player: Player

  /**
    * Setsthe model to be called.
    *
    * @param model - the model to be called.
    */
  def setModel(model: ToClientCommunication): Unit

}

/**
  * Represents the implementation of the controller for the user management.
  *
  * @author Margherita Pecorelli
  */
object BaseControllerUser extends ControllerUser {

  private var _model: ToClientCommunication = null

  /**
    * Tells the model all parameters for a new registration.
    *
    * @param name - the user'a name.
    * @param username - the user's username.
    * @param email - the user'a mail.
    * @param password - the user's accont password.
    * @param confirmPassword - the confirm of the password.
    *
    * @return true if the registration is successful, false otherwise.
    */
  override def registration(name: String, username: String, email: String, password: String, confirmPassword: String) = _model.registration(name, username, email, password, confirmPassword)

  /**
    * Tells the model all parameters for a login.
    *
    * @param username - the account's username.
    * @param password - the account's password.
    *
    * @return true if the login is successful, false otherwise.
    */
  override def login(username: String, password: String) = _model.login(username, password)

  /**
    * Tells the model to logout the user.
    *
    * @return true if the logout is successful, false otherwise.
    */
  override def logout = _model.logout

  /**
    * Tells the model the last match's result to save.
    *
    * @param lastMatchResult - the last match's result to save.
    */
  override def saveMatch(lastMatchResult: MatchResult): Unit = _model.saveMatch(lastMatchResult)

  /**
    * Returns the player of the user.
    *
    * @return the player of the user.
    */
  override def player = PlayerImpl

  /**
    * Setsthe model to be called.
    *
    * @param model - the model to be called.
    */
  override def setModel(model: ToClientCommunication) = _model = model
}