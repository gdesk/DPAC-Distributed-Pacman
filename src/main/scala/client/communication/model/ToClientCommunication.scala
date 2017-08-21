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
    * Sends the message to actor AccessManager with the registration's data and
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
    * Sends the message to actor ToServerCommunication with the login's data and
    * receive from sever the response with also the MatchResult
    *
    * @param username
    * @param password
    * @return list of MatchResult with data, result and score.
    *         If it's 'None', the login ended not good.
    *         If it's Option.empty, this is the first login
    */
  def login(username: String, password: String): Boolean

  /**
    * Sends the username to remove the user from online users' list.
    *
    * @return true  if logout ended good
    *        false otherwise
    */
  def logout(): Boolean

  /**
    * Receives the list of range to play the match.
    *
    * @return list of range to players' game
    */
  def getRanges: List[Range]

  /**
    * Sends the selected range,
    * that is the max and min number of players.
    *
    * @param range the range selected by player
    */
  def selectRange(range: Range): Unit

  /**
    * Receives the available character.
    *
    * @return list of all character to choose in team's creation.
    */
  def getCharactersToChoose: Map[String, Image]

  /**
    * Receives from server the characters playing in the current match
    * @param name the username of the player
    *
    * @return list of current match's characters.
    *         The Map has the direction and, as value, a image related to a key.
    */
  def getTeamCharacter(name: String):  Map[Direction, Image]

  /**
    * Sends the character chosen. It's recall when the player choose him character.
    * The images .png are saved in the resources directory.
    *
    * @param character character chosen from single player
    * @return true  if character has been already chosen
    *         false otherwise
    */
  def chooseCharacter(character: String): Boolean

  /**
    * Receives the list of available playgrounds, saving to resources directory
    * the image.
    *
    * @return number of available playground
    */
  def getPlaygrounds: Int

  /**
    * Sends the playground chosen. It's recall when the player choose the playground of current match.
    *
    * @param idPlayground the unique id of playground
    */
  def choosePlayground(idPlayground: Int): Unit

  /**
    * Sends the match just ended.
    *
    * @param result  The MatchResult with date and score of the ended match
    * @param user id of characters.
    */
  def matchResult(result: MatchResult, user: String): Unit

  /**
    * Receives all the played matches of selected username
    *
    * @param username username of player
    * @return list of all match with its result
    */
  def getAllMatchesResults(username: String): List[MatchResult]


  /**
    * Sends the request to information to configure and synchronize the P2P Communication.
    * After that start the game.
    **/
  def startMatch(): Unit

  /**
    * Sends the request to invite the friend in current match
    *
    * @param username username of player to invite
    */
  def sendRequest(username: String): Unit

  /**
    * Sends the invite's response
    *
    * @param response the invite's response to current match
    */
  def sendResponse(response: Boolean): Unit

  /**
    * Sends the request to take the list og player's ip.
    *
    * @return list of all the players's ip
    */
  def getPlayersIp(): List[String]

  /**
    * Initialize the character of current match.
    */
  def initializedCharatcter(): Unit

  /**
    * Sends the match to save to server.
    *
    * @param matchResult the match just ended, with score, date and result
    */
  def saveMatch(matchResult: MatchResult): Unit
}