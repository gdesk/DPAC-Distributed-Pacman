package client.model

import client.model.character.Character

import scala.collection.immutable.Map
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

/**
  * Represent the current match status, holding some information about the game.
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

  def charactersAndPlayers: Map[Character, Player]

  def addCharactersAndPlayers(character: Character, player: Player): Unit

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
  def allPlayers: List[Player]

  def character(player: Player): Option[Character]

  def player(character: Character): Option[Player]

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
case class MatchImpl private() extends Match {

  private var _deadCharacters: ListBuffer[Character] = ListBuffer empty
  private var charactersPlayers: HashMap[Character, Player] = HashMap empty
  private var _myCharacter: Character = null

  override var playground: Playground = null

  override def myCharacter: Character = {
    if(_myCharacter == null) _myCharacter = character(PlayerImpl instance()).get
    _myCharacter
  }

  override def charactersAndPlayers = charactersPlayers toMap

  override def addCharactersAndPlayers(character: Character, player: Player) = charactersPlayers += character -> player

  override def character(player: Player) = {
    val pair = charactersPlayers.filter(p => p._2 equals player).headOption
    if(pair isEmpty) {Option empty} else {Option (pair.get _1)}
  }

  override def player(character: Character) = charactersPlayers get character

  /**
    * Returns the list of all characters who participate at the match.
    *
    * @return the list of all characters.
    */
  override def allCharacters = (charactersPlayers keySet) toList

  /**
    * Returns the list of all players who participate at the match.
    *
    * @return the list of all players.
    */
  override def allPlayers: List[Player] = (charactersPlayers values) toList

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
    if(!(charactersPlayers contains deadCharacter)) throw new CharacterDoesNotExistException(deadCharacter.name + " doesn't exist")
    _deadCharacters += deadCharacter
    charactersPlayers -=  deadCharacter
  }
}

object MatchImpl {

  private var _instance: MatchImpl = null

  /**
    * Returns the only {@MatchImpl}'s instance. Pattern Singleton.
    *
    * @return the only MatchImpl's instance.
    */
  def instance(): MatchImpl = {
    if(_instance == null) _instance = MatchImpl()
    _instance
  }

}

case class CharacterDoesNotExistException(private val message: String = "") extends Exception(message)
