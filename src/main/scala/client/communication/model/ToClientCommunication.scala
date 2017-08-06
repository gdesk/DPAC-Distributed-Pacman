/*
package client.communication.model

import java.awt.Image
<<<<<<< HEAD
import java.util.Observer

import client.model.{Direction, MatchResult}
=======
import java.io.File
import java.util.Observer

import client.model.{Direction, MatchResult}
import client.model.character.Character
>>>>>>> 68886a0350f42d164cd02a62837241401739b052

/**
  * This class manages the model of communication between Client and Server.
  *
  * @author Giulia Lucchi
  */
trait ToClientCommunication {

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
  def registration(name: String, username: String, email: String, password: String, confirmPassword: String): Boolean

  /**
<<<<<<< HEAD
    * Send the message to actor ToServerCommunication with the login's data and
=======
    * Send the message to actor AccessManager with the login's data and
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
    * receive from sever the response with also the MatchResult
    * @param username
    * @param password
    * @return list of MatchResult with data, result and score.
    *         If it's 'None', the login ended not good.
    *         If it's Option.empty, this is the first login
    */
  def login(username: String, password: String): Option[List[MatchResult]]

  /**
<<<<<<< HEAD
    * Send to server the username to remove the user from online users' list.
    *
    * @param username user's username who wants to disconnect
    */
  def logout(username: String): Unit

  /**
=======
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
    *  Receives to server the list of range to play the match.
    *
    * @return list of range to players' game
    */
  def getRanges: List[Range]
  /**
    * Receives from server the available character.
    *
    * @return list of all character to choose in team's creation.
    */
  def getCharactersToChoose: Map[String, Image]

  /**
    * Receives from server the characters playing in the current match
    * NON SONO RICHIAMATI DAL CONTROLLER
    *
    * @return list of current match's characters.
    *         The Map has the name of character as key and, as value, a Map with direction and Image.
    */
  def getTeamCharacter: Map[String, Map[Direction, Image]]

  /**
    * Send to server the character chosen. It's recall when the player choose him character.
    *
    * @param character character chosen from single player
    *
    * @return true  if character has been already chosen
    *         false otherwise
    */
<<<<<<< HEAD
  def chooseCharacter(character: String): Boolean
=======
  def chooseCharacter(character: Character): Boolean
>>>>>>> 68886a0350f42d164cd02a62837241401739b052

  /**
    * Receives from server the List of available playgrounds.
    *
    * @return list of available playgrounds
    */
<<<<<<< HEAD
  def getPlaygrounds: Map[Int, Image]
=======
  def getPlaygrounds: List[File]
>>>>>>> 68886a0350f42d164cd02a62837241401739b052

  /**
    * Send to server the playground chosen. It's recall when the player choose the playground of current match.
    *
    * @param playground position of playground's in the file list.
    *
    */
  def choosePlayground(playground: Int): Unit


  /**
    * Send to server the match just ended.
    *
    * @param result  The MatchResult with date and score of the ended match
    * @param user id of characters.
    */
  def MatchResult(result: MatchResult, user: String): Unit

  /**
<<<<<<< HEAD
=======
    * Adds the observer.
    *
    * @param observer observer to add.
    */
  def addObserver(observer: Observer): Unit

  /**
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
    * Receives from server playgrond's string, corresponding to chosen playground.
    * SONO  SERVE AL CONTROLLER
    * @return Playground chosen in current match
    */
  def playgroundChosen(): String

  /**
    * Receives from server all the played matches of selected username
    *
    * @param username username of player
    * @return list of all match with its result
    */
  def getAllMatchesResults(username: String): List[MatchResult]
<<<<<<< HEAD

  /**
    * Adds the observer.
    *
    * @param observer observer to add.
    */
  def addObserver(observer: Observer): Unit

  /**
    * Send to server the request to information to configure and synchronize the P2P Communication.
    * Then start the game
    *
    * */
  def startMatch(): Unit
=======
>>>>>>> 68886a0350f42d164cd02a62837241401739b052
}
*/
