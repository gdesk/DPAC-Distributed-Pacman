package client.communication.model

import java.awt.Image
import java.io.File
import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Inbox, Props}
import client.communication.model.actor._
import client.model.{Direction, MatchResult}

import scala.concurrent.duration.Duration
import scala.util.parsing.json.JSONObject
import java.util.Observer

/**
  * Created by lucch on 19/07/2017.
  */
case class ToClientCommunicationImpl() extends ToClientCommunication{

  private val system = ActorSystem("ClientSystem")
  private val inbox = Inbox.create(system)
  private val accessManager = system actorOf(Props[AccessManager], "accessManager")
  private val gameManager = system.actorOf(Props[GameManager], "gameManager")
  private  val teamManager = system.actorOf(Props[TeamManager], "teamManager")

  private val observers: List[Observer] = null

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
    if (!(password equals(confirmPassword))){
      println("PASSWORD SBAGLIATA.")
      false
    }
    val message = JSONObject(Map[String, String](
      "object" -> "newUser",
      "name" -> name,
      "username" -> username,
      "email" -> email,
      "password" -> password
    ))

    inbox.send(accessManager, message)
    inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[Boolean]

  }

  /**
    * Send the message to actor AccessManager with the login's data and
    * receive from sever the response with also the MatchResult
    *
    * @param username
    * @param password
    * @return list of MatchResult with data, result and score.
    *         If it's 'None', the login ended not good.
    *         If it's Option.empty, this is the first login
    */
  override def login(username: String, password: String): Option[List[MatchResult]] = {
    val message = JSONObject(Map[String, String](
      "object" -> "login",
      "username" -> username,
      "password" -> password
    ))

    inbox.send(accessManager, message)
    inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[Option[List[MatchResult]]]
  }

  /**
    * Receives to server the list of range to play the match.
    *
    * @return list of range to players' game
    */
override def getRanges: List[Range] = {
  val message : String = "rangesRequest"

  inbox.send(gameManager, message)
  inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[List[Range]]
}

  /**
    * Receives from server the available character.
    *
    * @return list of all character to choose in team's creation.
    *         String -> character's name
    *         Image -> character's image
    */
override def getCharactersToChoose: Map[String, Image] = {
  val message : String = "characterToChooseRequest"
  inbox.send(gameManager, message)
  inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[Map[String, Image]]
}

  /**
    * Send to server the character chosen. It's recall when the player choose him character.
    *
    * @param character character chosen from single player
    * @return true  if character has been already chosen
    *         false otherwise
    */
override def chooseCharacter(character: Character): Boolean = {
  val message = JSONObject(Map[String, Any](
    "object" -> "chooseCharacter",
    "character" -> character
  ))
  inbox.send(gameManager, message)
  inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[Boolean]
}

  /**
    * Receives from server the List of available playgrounds.
    *
    * @return list of available playgrounds
    */
  override def getPlaygrounds: List[File] = {
    val message : String = "playgroundsRequest"
    inbox.send(gameManager, message)
    inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[List[File]]
  }

  /**
    * Send to server the playground chosen. It's recall when the player choose the playground of current match.
    *
    * @param playground position of playground's in the file list.
    *
    */
  override def choosePlayground(playground: Int): Boolean = {
    val message = JSONObject(Map[String, Any](
      "object" -> "choosePlayground",
      "character" -> playground
    ))
    inbox.send(gameManager, message)
    inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[Boolean]
  }

  /**
    * Send to server the match just ended.
    *
    * @param result The MatchResult with date and score of the ended match
    * @param user   id of characters.
    */
  override def MatchResult(result: MatchResult, user: String): Unit = {
    val message = JSONObject(Map[String, Any](
      "object" -> "matchResult",
      "result" -> result,
      "user" -> user
    ))
    gameManager ! message
  }

  /**
    * Receives from server all the played matches of selected username
    *
    * @param username username of player
    * @return list of all match with its result
    */
  override def getAllMatchesResults(username: String): List[MatchResult] = {
    val message = JSONObject(Map[String, String](
      "object" -> "allMatchResult",
      "username" -> username
    ))

    inbox.send(gameManager, message)
    inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[List[MatchResult]]
  }

  /**
    * Receives from server playgrond's string, corresponding to chosen playground.
    * SONO  SERVE AL CONTROLLER
    *
    * @return Playground chosen in current match
    */
  override def playgroundChosen(): String = {
    val message : String = "playgroundChosen"

    inbox.send(teamManager, message)
    inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[String]]
  }

  /**
    * Receives from server the characters playing in the current match
    * NON SONO RICHIAMATI DAL CONTROLLER
    *
    * @return list of current match's characters.
    *         The Map has the name of character as key and, as value, a Map with direction and Image.
    */
  override def getTeamCharacter: Map[String, Map[Direction, Image]] = {
    val message : String = "teamCharacter"

    inbox.send(teamManager, message)
    inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[Map[String, Map[Direction,Image]]]
  }

  /**
    * Adds the observer.
    *
    * @param observer observer to add.
    */
  override def addObserver(observer: Observer): Unit = {
    observers.::(observer)
  }
}

