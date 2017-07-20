package client.communication.model

import akka.actor.{ActorSystem, Props}
import client.communication.model.actor._
import client.model.MatchResult

import scala.util.parsing.json.JSONObject

/**
  * Created by lucch on 19/07/2017.
  */
case class ToClientCommunicationImpl() extends ToClientCommunication {
  val system = ActorSystem("ClientSystem")

  val accessManager = system actorOf(Props[AccessManager], "accessManager")
  //val gameManager = system.actorOf(Props[AccessManager], "gameManager")
  //val imagesManager = system.actorOf(Props[ImagesManager], "imagesManager")
  //val teamManager = system.actorOf(Props[TeamManager], "teamManager")
  //val toP2PCommunication = system.actorOf(Props[ToP2PCommunication], "toP2PCommunication")
  //val toServerCommunication = system.actorOf(Props[ToServerCommunication], "toServerCommunication")
  //val userManager = system.actorOf(Props[UserManager], "userManager")



  /**
    * Send the message to actor AccessManager with the registration's data and
    * receive from server the response.
    *
    * @param name
    * @param username
    * @param email
    * @param password
    * @param confirmPassword
    * @return true  if registration ended good
    *         false otherwise
    */
  override def registration(name: String, username: String, email: String, password: String, confirmPassword: String): Boolean = {
    println("entra registrazione")
    if (!(password equals(confirmPassword))){
      println("pass sbagliata")
      false
    }
    val message = JSONObject(Map[String, Any](
      "name" -> name,
      "username" -> username,
      "email" -> email,
      "password" -> password
    ))

    accessManager ! message
    true
  }

  /**
    * Send the message to actor AccessManager with the login's data and
    * receive from sever the response with also the MatchResult
    *
    * @param username
    * @param Password
    * @return list of MatchResult with data, result and score. If it's empty, the registration ended not good.
    */
  override def login(username: String, Password: String): List[MatchResult] = ???

  /**
    * Receives from server the available character.
    *
    * @return list of all character to choose in team's creation.
    */
override def getCharactersToChoose: List[Character] = ???

  /**
    * Receives from server the characters playing in the current match
    *
    * @return list of current match's characters
    */
override def getTeamCharacter: List[Character] = ???

  /**
    * Send to server the character chosen
    *
    * @param character character chosen from single player
    * @return true  if character has been already chosen
    *         false otherwise
    */
override def chooseCharacter(character: Character): Boolean = ???

  /**
    * Receives from server the List of available playgrounds.
    *
    * @return list of available playgrounds
    */
  override def getPlaygrounds: List[String] = ???

  /**
    * Send to server the playground chosen
    *
    * @param playground playground chosen for the current match.
    *
    */
  override def choosePlayground(playground: String): Unit = ???

  /**
    * Receives from server playgrond's string, corresponding to chosen playground.
    *
    * @return Playground chosen in current match
    */
  override def playgroundChosen(): String = ???

  /**
    * Send to server the match just ended.
    *
    * @param result The MatchResult with date and score of the ended match
    * @param user   id of characters.
    */
  override def MatchResult(result: MatchResult, user: String): Unit = ???


}

