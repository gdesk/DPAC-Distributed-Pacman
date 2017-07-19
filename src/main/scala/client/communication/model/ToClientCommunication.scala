package client.communication.model

import client.model.MatchResult

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
    * Send the message to actor AccessManager with the login's data and
    * receive from sever the response with also the MatchResult
    * @param username
    * @param Password
    * @return list of MatchResult with data, result and score. If it's empty, the registration ended not good.
    */
  def login(username: String, Password: String): List[MatchResult]

  /**
    *  NON HO BEN CAPITO COME FARLO.
    *
    * @return list of range to players' game
    */
  //def getRanges: List<Pair(min: Int, max: Int)
  /**
    * Receives from server the available character.
    *
    * @return list of all character to choose in team's creation.
    */
  def getCharactersToChoose: List[Character]

  /**
    * Receives from server the characters playing in the current match
    *
    * @return list of current match's characters
    */
  def getTeamCharacter: List[Character]

  /**
    * Send to server the character chosen
    * @param character character chosen from single player
    *
    * @return true  if character has been already chosen
    *         false otherwise
    */
  def chooseCharacter(character: Character): Boolean

  /**
    * Receives from server the List of available playgrounds.
    *
    * @return list of available playgrounds
    */
  def getPlaygrounds: List[String]

  /**
    * Send to server the playground chosen
    *
    * @param playground playground chosen for the current match.
    *
    */
  def choosePlayground(playground: String): Unit

  /**
    * Receives from server playgrond's string, corresponding to chosen playground.
    *
    * @return Playground chosen in current match
    */
  def playgroundChoosen(): String

  /**
    * Send to server the match just ended.
    *
    * @param result  The MatchResult with date and score of the ended match
    * @param user id of characters.
    */
  def MatchResul(result: MatchResult, user: String): Unit

}
