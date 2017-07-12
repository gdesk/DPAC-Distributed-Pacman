package client.model

import client.model.character.{BaseEatObjectStrategy, Character, Pacman, BasePacman}

import scala.collection.mutable.Map

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
  def myCharacter: Character[Int, Int]

  /**
    * Sets the character of the main user.
    *
    * @param character - the character of the main user.
    */
  def myCharacter_=(character: Character[Int, Int]): Unit

  /**
    * Adds all match's players, excluding the main one.
    *
    * @param players - the characters and users Map.
    */
  def addPlayers(players: Map[Character[Int, Int], String]): Unit

  /**
    * Returns the list of all characters who participate at the match.
    *
    * @return the list of all characters.
    */
  def characters: List[Character[Int, Int]]

  /**
    * Returns the list of dead characters.
    *
    * @return the list of dead characters.
    */
  def deadCharacters: List[Character[Int, Int]]

  /**
    * Adds a dead characters to the list.
    *
    * @param deadCharacters - the dead characters.
    * @return the user's id of the player that was using that client.model.character.gameElement.character.
    */
  def addDeadCharacters(deadCharacters: Character[Int, Int]): String

}

/**
  *
  *
  *
  */
case class MatchImpl private() extends Match {

  private var _deadCharacters: List[Character[Int, Int]] = List.empty
  private var mapCharacterUser: Map[Character[Int, Int], String] = Map empty

  override var playground: Playground = null
  override var myCharacter: Character[Int, Int] = BasePacman("pc", BaseEatObjectStrategy())

  /**
    * Adds all match's players, excluding the main one.
    *
    * @param players - the characters and users Map.
    */
  override def addPlayers(players: Map[Character[Int, Int], String]) = mapCharacterUser = players

  /**
    * Returns the list of all characters who participate at the match.
    *
    * @return the list of all characters.
    */
  override def characters = (mapCharacterUser keySet) toList

  /**
    * Returns the list of dead characters.
    *
    * @return the list of dead characters.
    */
  override def deadCharacters = _deadCharacters

  /**
    * Sets the list of dead characters.
    *
    * @param deadCharacters - the list of dead characters.
    */
  override def addDeadCharacters(deadCharacters: Character[Int, Int]) = {
    deadCharacters :: _deadCharacters
    val character = mapCharacterUser get deadCharacters
    mapCharacterUser -=  deadCharacters
    character get
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
