package client.model

import client.model.character.Character

import scala.collection.immutable.Map
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

/**
  * Represents the current match. It holds some information about the game.
  *
  * @author Margherita Pecorelli
  */
trait Match {

  /**
    * Returns the playground of the current match.
    *
    * @return the playground of the current match.
    */
  def playground: Playground

  /**
    * Sets the playground of the current match.
    *
    * @param playground - the playground of the current match.
    */
  def playground_=(playground: Playground): Unit

  /**
    * Returns a map with all match's characters and their players' ip.
    *
    * @return a map with all match's characters and their players' ip.
    */
  def charactersAndPlayersIp: Map[Character, String]

  /**
    * Adds to the map of all match's characters and their players' ip a new couple of (character, player's ip).
    *
    * @param character - the new character to be added at the map.
    * @param playerIp - the ip of the character's player to be added at the map.
    */
  def addCharactersAndPlayersIp(character: Character, playerIp: String): Unit

  /**
    * Returns the list of all match's characters.
    *
    * @return the list of all match's characters.
    */
  def allCharacters: List[Character]

  /**
    * Returns the list of all ip belonging to all match's players.
    *
    * @return the list of all players' ip.
    */
  def allPlayersIp: List[String]

  /**
    * Return the character corresponding to the player's ip.
    *
    * @param playerIp - the player's ip.
    * @return an Option containing the character belonging to the player if it exists, None otherwise.
    */
  def character(playerIp: String): Option[Character]

  /**
    * Returns the character corresponding to the current user's ip.
    *
    * @return - the character belonging to the current user.
    */
  def myCharacter: Character

  /**
    * Return the player's ip corresponding to the character.
    *
    * @param character - the character.
    * @return an Option containing the ip of the player to which the character belongs if it exists, None otherwise.
    */
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
    * @param deadCharacter - the dead character.
    * @throws CharacterDoesNotExistException when the character to add doesn't exist.
    */
  def addDeadCharacters(deadCharacter: Character): Unit

}

/**
  * Represents the implementation of the current match. It holds some information about the game.
  *
  * @author Margherita Pecorelli
  */
object MatchImpl extends Match {

  private var _deadCharacters: ListBuffer[Character] = ListBuffer.empty
  private var _charactersAndPlayersIp: HashMap[Character, String] = HashMap.empty
  private var _myCharacter: Character = _

  override var playground: Playground = _

  /**
    * Returns a map with all match's characters and their players' ip.
    *
    * @return a map with all match's characters and their players' ip.
    */
  override def charactersAndPlayersIp = _charactersAndPlayersIp.toMap

  /**
    * Adds to the map of all match's characters and their players' ip a new couple of (character, player's ip).
    *
    * @param character - the new character to be added at the map.
    * @param playerIp - the ip of the character's player to be added at the map.
    */
  override def addCharactersAndPlayersIp(character: Character, playerIp: String) = _charactersAndPlayersIp += (character -> playerIp)

  /**
    * Returns the list of all match's characters.
    *
    * @return the list of all match's characters.
    */
  override def allCharacters = _charactersAndPlayersIp.keySet.toList

  /**
    * Returns the list of all ip belonging to all match's players.
    *
    * @return the list of all players' ip.
    */
  override def allPlayersIp: List[String] = _charactersAndPlayersIp.values.toList

  /**
    * Return the character corresponding to the player's ip.
    *
    * @param playerIp - the player's ip.
    * @return an Option containing the character belonging to the player if it exists, None otherwise.
    */
  override def character(playerIp: String) = {
    val pair = _charactersAndPlayersIp.filter(p => p._2 equals playerIp).headOption
    pair isEmpty match {
      case true => Option.empty
      case _ => Option(pair.get._1)
    }
  }

  /**
    * Returns the character corresponding to the current user's ip.
    *
    * @return - the character belonging to the current user.
    */
  override def myCharacter: Character = {
    if(_myCharacter == null) _myCharacter = character(PlayerImpl.ip).get
    _myCharacter
  }

  /**
    * Return the player's ip corresponding to the character.
    *
    * @param character - the character.
    * @return an Option containing the ip of the player to which the character belongs if it exists, None otherwise.
    */
  override def playerIp(character: Character) = _charactersAndPlayersIp.get(character)

  /**
    * Returns the list of dead characters.
    *
    * @return the list of dead characters.
    */
  override def deadCharacters = _deadCharacters.toList

  /**
    * Adds a dead characters to the list.
    *
    * @param deadCharacter - the dead character.
    * @throws CharacterDoesNotExistException when the character to add doesn't exist.
    */
  override def addDeadCharacters(deadCharacter: Character) = {
    if(!(_charactersAndPlayersIp contains deadCharacter)) throw new CharacterDoesNotExistException(deadCharacter.name + " doesn't exist")
    _deadCharacters += deadCharacter
    _charactersAndPlayersIp -= deadCharacter
  }

}

/**
  * Represents the excetpion throws when the character to be added to the dead characters list, doesn't exist.
  *
  * @param message - message throws by the exception.
  */
case class CharacterDoesNotExistException(private val message: String = "") extends Exception(message)
