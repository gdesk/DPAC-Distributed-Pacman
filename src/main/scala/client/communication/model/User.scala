package client.communication.model

/**
  * Class to memorize the base information of user.
  *
  * @author Giulia Lucchi
  */
trait User {

  def username_=(username: String): Unit
  def username(): String

  def password_=(password: String): Unit
  def password(): String

}

case class UserImpl( var username: String, var password: String ) extends User {}
