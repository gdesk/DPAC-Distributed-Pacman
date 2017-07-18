package client.communication


/**
  * Created by lucch on 18/07/2017.
  */
trait User {
  def username_=(username: String)
  def username(): Unit
  def password_=(password: String)
  def password(): Unit
  def email_=(email: String)
  def email(): Unit
}

abstract class UserImpl extends User{
  var username: String
  var password: String
  var email: String

  def username_=(username: String): Unit = this.username = username

  def password_=(password: String): Unit = this.password = password

  def email_=(email: String): Unit = this.email = email
}
