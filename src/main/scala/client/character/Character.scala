package client.character

import character.Direction
import client.gameElement.GameItem
import client.utils.Point

/**
  * Manages the base model of character.
  *
  * @author Giulia Lucchi
  */
trait Character[X,Y] extends GameItem[X,Y]{
  /**
    *
    * Manages the character's movement and consequently the contact with other item of the game.
    *
    * @param direction    character's of direction
    */
  def go(direction: Direction): Unit

  /**
    * Manage the the strategy of game, that is based on who the killer is and who the killable
    */
  def death(): Unit

  /**
    * setter character's position
    *
    * @param point    a point of character within the game map
    * */
  def setPosition(point: Point[X,Y]): Unit

  /**
    * getter of character's direction
    *
    * @return direction   a direction of character
    * */
  def direction() : Direction

  /**
    * setter of character's direction
    *
    * @param direction    a direction of character
    * */
  def direction_=(direction: Direction): Unit // la direzione dobbiamao valutare se lasciarla perchè  incapsulato in move di prolog e quindi servirebbe solo per il cambio immagine che può essere fatto anche nel momento in cui cambi durezione dalla tastiera

  /**
    * getter of character's state of its life
    *
    * @return true    if character is alive
    *         false   otherwise
    * */
  def isAlive(): Boolean

  /**
    * setter of character's state of its life
    *
    * @param isAlive   true    if character is alive
    *                  false   otherwise
    * */
  def isAlive_= (isAlive: Boolean): Unit

  /**
    * getter if the character is killer or killable
    *
    * @return true    if character is killable of other character
    *         false   otherwise
    * */
  def isKillable(): Boolean

  /**
    * setter if the character is killer or killable
    *
    * @return true    if character is killable of other character
    *         false   otherwise
    * */
  def isKillable_= (isKillable: Boolean): Unit

  /**
    * getter of character's name
    *
    * @return name    character's name
    */
  def name(): String

  /**
    * getter of lives in the single match
    *
    * @return lives   lives of the game in the sigle match
    */
  def lives: Lives
}

abstract class CharacterImpl(override var isKillable: Boolean, override val lives: Lives) extends Character[Int, Int] {
  override var isAlive: Boolean = true
  var pointPosition: Point[Int, Int] = InitializedInfoImpl.getStartPosition()
  override var direction: Direction = Direction.START

  /**
    * setter character's position
    *
    * @param point a point of character within the game map
    **/
  override def setPosition(point: Point[Int, Int]): Unit = pointPosition = point

  /**
    * getter character's position
    *
    * @return position    a point of character within the game map
    * */
  override def position: Point[Int, Int] = pointPosition

}
