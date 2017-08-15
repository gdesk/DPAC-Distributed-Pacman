package client.model

import client.model.character.Character

import scala.collection.immutable.Map
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

/**
  * Represents the current match status, holding some information about the game.
  *
  * @author Margherita Pecorelli
  */
trait Match {

  /**
    * Returns the playground of the current match. The playground is composed by a list of the coordinates of the steet.
    *
    * @return a list of Point representing the coordinates of the steet.
    */
  def playground: Playground

  /**
    * Sets the playground of the current match with the list of the coordinates of the steet.
    *
    * @param playground - the list of Point representing the coordinates of the steet.
    */
  def playground_=(playground: Playground): Unit

  /**
    * Returns the character of the main user.
    *
    * @return - the character of the main user.
    */
  def myCharacter: Character

  def charactersAndPlayersIp: Map[Character, String]

  def addCharactersAndPlayersIp(character: Character, playerIp: String): Unit

  /**
    * Returns the list of all characters who participate at the match.
    *
    * @return the list of all characters.
    */
  def allCharacters: List[Character]

  /**
    * Returns the list of all players who participate at the match.
    *
    * @return the list of all players.
    */
  def allPlayersIp: List[String]

  def character(playerIp: String): Option[Character]

  def playerIp(character: Character): Option[String]

  /**
    * Returns the list of dead characters.
    *
    * @return the list of dead characters.
    */
  def deadCharacters: List[Character]

  /**
    * Adds a dead characters to the list.
    *
    * @param deadCharacters - the dead characters.
    * @throws CharacterDoesNotExistException when the character to add doesn't exist.
    * @return the user's id of the player that was using that client.model.character.gameElement.character.
    */
  def addDeadCharacters(deadCharacters: Character): Unit

}

/**
  *
  *
  *
  */
object MatchImpl extends Match {

  private var _deadCharacters: ListBuffer[Character] = ListBuffer empty
  private var charactersPlayersIp: HashMap[Character, String] = HashMap empty
  private var _myCharacter: Character = _

  override var playground: Playground = _

  override def myCharacter: Character = {
    if(_myCharacter == null) _myCharacter = character(PlayerImpl.ip).get
    _myCharacter
  }

  override def charactersAndPlayersIp = charactersPlayersIp toMap

  override def addCharactersAndPlayersIp(character: Character, playerIp: String) = charactersPlayersIp += character -> playerIp

  override def character(playerIp: String) = {
    val pair = charactersPlayersIp.filter(p => p._2 equals playerIp).headOption
    if(pair isEmpty) {Option empty} else {Option (pair.get _1)}
  }

  override def playerIp(character: Character) = charactersPlayersIp get character

  /**
    * Returns the list of all characters who participate at the match.
    *
    * @return the list of all characters.
    */
  override def allCharacters = (charactersPlayersIp keySet) toList

  /**
    * Returns the list of all players who participate at the match.
    *
    * @return the list of all players.
    */
  override def allPlayersIp: List[String] = (charactersPlayersIp values) toList

  /**
    * Returns the list of dead characters.
    *
    * @return the list of dead characters.
    */
  override def deadCharacters = _deadCharacters toList

  /**
    * Sets the list of dead characters.
    *
    * @param deadCharacter - the list of dead characters.
    * @throws CharacterDoesNotExistException when the character to add doesn't exist.
    */
  override def addDeadCharacters(deadCharacter: Character) = {
    if(!(charactersPlayersIp contains deadCharacter)) throw new CharacterDoesNotExistException(deadCharacter.name + " doesn't exist")
    _deadCharacters += deadCharacter
    charactersPlayersIp -=  deadCharacter
  }
}

case class CharacterDoesNotExistException(private val message: String = "") extends Exception(message)
