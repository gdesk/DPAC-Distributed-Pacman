package client.communication.model

import java.awt.Image
<<<<<<< HEAD
import java.util.Observer
import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Inbox, Props}
import client.communication.model.actor.{FromServerCommunication, P2PCommunication, ToServerCommunication}
import client.model.{Direction, MatchResult}
import client.utils.ActorUtils

import scala.concurrent.duration.Duration
import scala.reflect.io.File
import scala.util.parsing.json.JSONObject
=======
import java.io.File
import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Inbox, Props}
import client.communication.model.actor._
import client.model.{Direction, MatchResult}

import scala.concurrent.duration.Duration
import scala.util.parsing.json.JSONObject
import java.util.Observer

import client.model.character.Character
>>>>>>> 68886a0350f42d164cd02a62837241401739b052

/**
  * This class is the model of communication, used to controller. It manages the interaction with the server,
  * through the actor paradigm.
  *
  * @author Giulia Lucchi
  */
<<<<<<< HEAD

=======
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
case class ToClientCommunicationImpl() extends ToClientCommunication{

  private val system = ActorSystem("ClientSystem")
  private val inbox = Inbox.create(system)
<<<<<<< HEAD

  private val toServerCommunication = system.actorOf(Props[ToServerCommunication], "toServerCommunication")
  private val fromServerCommunication = system.actorOf(Props[FromServerCommunication], "fromServerCommunication")
  private val P2PCommunication = system actorOf(Props[P2PCommunication], "P2PCommunication")
=======
  private val accessManager = system actorOf(Props[AccessManager], "accessManager")
  private val gameManager = system.actorOf(Props[GameManager], "gameManager")
  private val teamManager = system.actorOf(Props[TeamManager], "teamManager")
  private val imagesManager = system.actorOf(Props[ImagesManager], "imagesManager")
  private val toServerCommunication = system.actorOf(Props[ToServerCommunication], "toServerCommunication")
  private val toP2PCommunication = system.actorOf(Props[ToP2PCommunication], "toP2PCommunication")
>>>>>>> 68886a0350f42d164cd02a62837241401739b052

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
<<<<<<< HEAD
      "senderIP" -> ActorUtils.IP_ADDRESS,
=======
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
      "name" -> name,
      "username" -> username,
      "email" -> email,
      "password" -> password
    ))

<<<<<<< HEAD
    val response= getJSONMessage(message)
    response.obj("registration").asInstanceOf[Boolean]
  }

  /**
    * Send the message to actor ToServerCommunication with the login's data and
=======
    inbox.send(accessManager, message)
    inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[Boolean]

  }

  /**
    * Send the message to actor AccessManager with the login's data and
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
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
<<<<<<< HEAD
      "senderIP" -> ActorUtils.IP_ADDRESS,
=======
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
      "username" -> username,
      "password" -> password
    ))

<<<<<<< HEAD
    val response = getJSONMessage(message)
    response.obj("list").asInstanceOf[Option[List[MatchResult]]]
  }

  /**
    * Send to server the username to remove the user from online users' list.
    *
    * @param username user's username who wants to disconnect
    */
  override def logout(username: String): Unit = {
    val message = JSONObject(Map[String, String](
      "object" -> "logout",
      "username" -> username
    ))

    toServerCommunication ! message.asInstanceOf[JSONObject]
=======
    inbox.send(accessManager, message)
    inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[Option[List[MatchResult]]]
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
  }

  /**
    * Receives to server the list of range to play the match.
    *
    * @return list of range to players' game
    */
<<<<<<< HEAD
  override def getRanges: List[Range] = {
    val message = JSONObject(Map[String, String](
      "object" -> "rangesRequest",
      "senderIP" ->ActorUtils.IP_ADDRESS))

    val response = getJSONMessage(message)
    response.obj("list").asInstanceOf[List[Range]]
  }
=======
override def getRanges: List[Range] = {
  val message : String = "rangesRequest"

  inbox.send(gameManager, message)
  inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[List[Range]]
}
>>>>>>> 68886a0350f42d164cd02a62837241401739b052

  /**
    * Receives from server the available character.
    *
    * @return list of all character to choose in team's creation.
<<<<<<< HEAD
    */
  override def getCharactersToChoose: Map[String, Image] = {
    val message = JSONObject(Map[String, String](
      "object" -> "characterToChooseRequest",
      "senderIP" -> ActorUtils.IP_ADDRESS
    ))

    val rensponse = getJSONMessage(message)
    rensponse.obj("map").asInstanceOf[Map[String, Image]]
  }
=======
    *         String -> character's name
    *         Image -> character's image
    */
override def getCharactersToChoose: Map[String, Image] = {
  val message : String = "characterToChooseRequest"
  inbox.send(imagesManager, message)
  inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[Map[String, Image]]
}
>>>>>>> 68886a0350f42d164cd02a62837241401739b052

  /**
    * Send to server the character chosen. It's recall when the player choose him character.
    *
    * @param character character chosen from single player
    * @return true  if character has been already chosen
    *         false otherwise
    */
<<<<<<< HEAD
  override def chooseCharacter(character: String): Boolean = {
    val message = JSONObject(Map[String, String](
      "object" -> "chooseCharacter",
      "senderIP" -> ActorUtils.IP_ADDRESS,
      "character" -> character))

    val response = getJSONMessage(message)
    val isAvaible = response.obj("avaible").asInstanceOf[Boolean]
    if (isAvaible) {
      val images = response.obj("map").asInstanceOf[Map[String, Map[Direction, Image]]]
    }
    isAvaible
  }
=======
override def chooseCharacter(character: String): Boolean = {
  val message = JSONObject(Map[String, Any](
    "object" -> "chooseCharacter",
    "character" -> character
  ))
  inbox.send(gameManager, message)
  inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[Boolean]
}
>>>>>>> 68886a0350f42d164cd02a62837241401739b052

  /**
    * Receives from server the List of available playgrounds.
    *
    * @return list of available playgrounds
<<<<<<< HEAD
    *         Int -> playground'id
    *         Image -> playground transformed to image
    */
  override def getPlaygrounds: Map[Int, Image] = {
    val message = JSONObject(Map[String, String](
      "object" -> "playgrounds",
      "senderIP" -> ActorUtils.IP_ADDRESS))

    val response = getJSONMessage(message)
    response.obj("list").asInstanceOf[Map[Int, Image]]
=======
    */
  override def getPlaygrounds: List[File] = {
    val message : String = "playgroundsRequest"
    inbox.send(gameManager, message)
    inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[List[File]]
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
  }

  /**
    * Send to server the playground chosen. It's recall when the player choose the playground of current match.
    *
    * @param playground position of playground's in the file list.
    *
    */
  override def choosePlayground(playground: Int): Unit = {
    val message = JSONObject(Map[String, Any](
<<<<<<< HEAD
      "object" -> "chosenPlayground",
      "senderIP" -> ActorUtils.IP_ADDRESS,
      "playground" -> playground))

    val response = getJSONMessage(message)
    val playgroundFile = response.obj("playground").asInstanceOf[File] // initialize
=======
      "object" -> "choosePlayground",
      "character" -> playground
    ))
    gameManager ! message
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
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
<<<<<<< HEAD
      "senderIP" -> ActorUtils.IP_ADDRESS,
      "result" -> result, // vediamo poi se passare solo il punteggio o tutto l'oggetto
      "user" -> user
    ))
    toServerCommunication ! message.asInstanceOf[JSONObject]
  }

  /**
    * Receives from server playgrond's string, corresponding to chosen playground.
    * SONO  SERVE AL CONTROLLER
    *
    * @return Playground chosen in current match
    */
  override def playgroundChosen(): String ={""} // a questo punto non lo farei

  /**
=======
      "result" -> result,
      "user" -> user
    ))
    gameManager ! message
  }

  /**
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
    * Receives from server all the played matches of selected username
    *
    * @param username username of player
    * @return list of all match with its result
    */
  override def getAllMatchesResults(username: String): List[MatchResult] = {
    val message = JSONObject(Map[String, String](
      "object" -> "allMatchResult",
<<<<<<< HEAD
      "senderIP" -> ActorUtils.IP_ADDRESS,
      "username" -> username
    ))
    val response = getJSONMessage(message)
    response.obj("list").asInstanceOf[List[MatchResult]]
  }

  /**
    * Receives from server the characters playing in the current match
    * NON SONO RICHIAMATI DAL CONTROLLER
    *
    * @return list of current match's characters.
    *         The Map has the IP address of character as key and, as value, a Map with direction and Image.
    */
  override def getTeamCharacter: Map[String, Map[Direction, Image]] = {
    val message = JSONObject(Map[String, String](
      "object" -> "teamCharacterRequest ",
      "senderIP" -> ActorUtils.IP_ADDRESS
    ))

    val response = getJSONMessage(message)
    response.obj("map").asInstanceOf[Map[String, Map[Direction, Image]]]
  }

  /**
    * Send to server the request to information to configure and synchronize the P2P Communication.
    * Then start the game
    *
    **/
  override def startMatch(): Unit = {
    val message = JSONObject(Map[String,String](
      "object" -> "startGame",
      "senderIP" -> ActorUtils.IP_ADDRESS
    ))
    P2PCommunication ! message.asInstanceOf[JSONObject]
=======
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
    inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[String]
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
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
  }

  /**
    * Adds the observer.
    *
    * @param observer observer to add.
    */
  override def addObserver(observer: Observer): Unit = {
    observers.::(observer)
  }
<<<<<<< HEAD

  /**
    * This private method due to encapsulate the send and receive of the messages.
    *
    * @param message  message to send
    * @return         message to receive
    */
  private def getJSONMessage( message: JSONObject) : JSONObject = {
    inbox.send(toServerCommunication, message)
    inbox.receive(Duration.apply(10,TimeUnit.SECONDS)).asInstanceOf[JSONObject]
  }


}
=======
}

>>>>>>> 68886a0350f42d164cd02a62837241401739b052
