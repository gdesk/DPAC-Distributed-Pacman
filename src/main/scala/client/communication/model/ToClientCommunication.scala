package client.communication.model

import java.awt.Image

import client.model.{Direction, MatchResult}

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
    * Send the message to actor ToServerCommunication with the login's data and
    * receive from sever the response with also the MatchResult
    * @param username
    * @param password
    * @return list of MatchResult with data, result and score.
    *         If it's 'None', the login ended not good.
    *         If it's Option.empty, this is the first login
    */
  def login(username: String, password: String): Boolean

  /**
    * Send to server the username to remove the user from online users' list.
    *
    */
  def logout(): Boolean

  /**
    *  Receives to server the list of range to play the match.
    *
    * @return list of range to players' game
    */
  def getRanges: List[Range]

  /**
    * Send to server the selected range
    *
    * @param range the range selected by player
    */
  def selectRange(range: Range): Unit

  /**
    * Receives from server the available character.
    *
    * @return list of all character to choose in team's creation.
    */
  def getCharactersToChoose: Map[String, Image]

  /**
    * Receives from server the characters playing in the current match
    *
    * @return list of current match's characters.
    *         The Map has the name of character as key and, as value, a Map with direction and Image.
    */
  def getTeamCharacter(ip: String):  Map[Direction, Image]

  /**
    * Send to server the character chosen. It's recall when the player choose him character.
    *
    * @param character character chosen from single player
    *
    * @return true  if character has been already chosen
    *         false otherwise
    */
  def chooseCharacter(character: String): Boolean

  /**
    * Receives from server the List of available playgrounds.
    */
  def getPlaygrounds: Int

  /**
    * Send to server the playground chosen. It's recall when the player choose the playground of current match.
    *
    * @param idPlayground position of playground's in the file list.
    *
    */
  def choosePlayground(idPlayground: Int): Unit

  /**
    * Send to server the match just ended.
    *
    * @param result  The MatchResult with date and score of the ended match
    * @param user id of characters.
    */
  def matchResult(result: MatchResult, user: String): Unit

  /**
    * Receives from server all the played matches of selected username
    *
    * @param username username of player
    * @return list of all match with its result
    */
  def getAllMatchesResults(username: String): List[MatchResult]


  /**
    * Send to server the request to information to configure and synchronize the P2P Communication.
    * Then start the game
    *
    * */
  def startMatch(): Unit

  /**
    * Send to server the request to invite the friend in current match
    *
    * @param username username of player to invite
    * @return boolean the invite's response
    */
  def sendRequest(username: String): Unit

  /**
    * Send to server the invite's response
    *
    * @param response the invite's response to current match
    */
  def sendResponse(response: Boolean): Unit

  /**
    * Send to server the request to take the list og player's ip.
    *
    * @return list of all the players's ip
    */
  def getPlayersIp(): List[String]

  def initializedCharatcter(): Unit
}