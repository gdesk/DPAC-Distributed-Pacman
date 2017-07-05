package client

import client.utils.Point

import scala.collection.immutable.HashMap

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
  def playground(): List[Point[Int,Int]]

  /**
    * Sets the playground of the current match with the list of the coordinates of the steet.
    *
    * @param streetPositionsList - the list of Point representing the coordinates of the steet.
    */
  def playground_=(streetPositionsList: List[Point[Int,Int]]): Unit

  /**
    * Returns the list of all characters who participate at the match.
    *
    * @return the list of all characters.
    */
  def characters(): List[Character]

  /**
    * Sets the list of all characters who participate at the match.
    *
    * @param allCharacters - the list of all characters.
    */
  def characters_=(allCharacters: List[Character]): Unit

  /**
    * Returns the list of dead characters.
    *
    * @return the list of dead characters.
    */
  def deadCharacters(): List[Character]

  /**
    * Sets the list of dead characters.
    *
    * @param deadCharacters - the list of dead characters.
    */
  def deadCharacters_=(deadCharacters: List[Character]): Unit

}

case class MatchImpl(override var playground: List[Point[Int,Int]], val mapUserCharacter: HashMap[String, Character]) extends Match {
  /**
    * Returns the list of all characters who participate at the match.
    *
    * @return the list of all characters.
    */
  override def characters(): List[Character] = ???

  /**
    * Sets the list of all characters who participate at the match.
    *
    * @param allCharacters - the list of all characters.
    */
  override def characters_=(allCharacters: List[Character]): Unit = ???

  /**
    * Returns the list of dead characters.
    *
    * @return the list of dead characters.
    */
  override def deadCharacters(): List[Character] = ???

  /**
    * Sets the list of dead characters.
    *
    * @param deadCharacters - the list of dead characters.
    */
  override def deadCharacters_=(deadCharacters: List[Character]): Unit = ???
}