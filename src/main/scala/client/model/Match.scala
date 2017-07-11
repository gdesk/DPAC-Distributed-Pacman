package client.model

import client.model.character.Character

import scala.collection.mutable.HashMap

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
  def playground(): Playground

  /**
    * Sets the playground of the current match with the list of the coordinates of the steet.
    *
    * @param playground - the list of Point representing the coordinates of the steet.
    */
  def playground_=(playground: Playground): Unit

  /**
    * Returns the list of all characters who participate at the match.
    *
    * @return the list of all characters.
    */
  def characters(): List[Character[Int, Int]]

  /**
    * Returns the list of dead characters.
    *
    * @return the list of dead characters.
    */
  def deadCharacters(): List[Character[Int, Int]]

  /**
    * Add a dead characters to the list.
    *
    * @param deadCharacters - the dead characters.
    * @return the user's id of the player that was using that client.model.character.gameElement.character.
    */
  def addDeadCharacters(deadCharacters: Character[Int, Int]): String

}

case class MatchImpl private() extends Match {

  private var deadChars: List[Character[Int, Int]] = List.empty
  private val mapCharacterUser: HashMap[Character[Int, Int], String] = HashMap[Character[Int, Int], String]()

  override var playground: Playground = null

  /**
    * Returns the list of all characters who participate at the match.
    *
    * @return the list of all characters.
    */
  override def characters(): List[Character[Int, Int]] = (mapCharacterUser keySet) toList

  /**
    * Returns the list of dead characters.
    *
    * @return the list of dead characters.
    */
  override def deadCharacters(): List[Character[Int, Int]] = deadChars

  /**
    * Sets the list of dead characters.
    *
    * @param deadCharacters - the list of dead characters.
    */
  override def addDeadCharacters(deadCharacters: Character[Int, Int]) = {
    deadCharacters :: deadChars
    (mapCharacterUser remove  deadCharacters) get
  }
}

object MatchImpl {

  private var _instance: MatchImpl = null

  /**
    * Returns the only {@MatchImpl}'s instance. Pattern Singleton.
    *
    * @return the only MatchImpl's instance.
    */
  def instance(): Match = {
    if(_instance == null) _instance = MatchImpl()
    _instance
  }
}
