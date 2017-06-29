package client.character

import character.Direction
import client.gameElement.GameItem
import client.utils.{Lives, Point}

/**
  * The class manages the base model of character.
  *
  * Created by Giulia Lucchi on 28/06/2017.
  */
trait Character[X,Y] extends GameItem[X,Y]{
  def go(direction: Direction): Unit
  //def contactBlock(block: Block) non serve per come è stato fatto prolog CONTROLLA.
  //def contactCharacter(character: Character) Guardando la logica non serve e si protrebbe fare una classe generale che incapsula la parte di proolog fatta

  /**
    * set character's position
    *
    * @param point    a point of character within the game map
    * */
  def position_=(point: Point[X,Y]): Unit
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
    * @param  isAlive
    * */
  def isAlive_= (isAlive: Boolean): Unit

  /**
    *
    *
    * @return
    * */
  def isKillable(): Boolean

  /**
    *
    *
    *
    * */
  def isKillable_= (isKillable: Boolean): Unit
  def name(): String
  def lives: Lives
  //score (get e set???)
}

abstract class CharacterImpl (override var isKillable: Boolean, override val lives: Lives) extends Character[Int, Int]{
  override var isAlive: Boolean = true
  var pointPosition: Point[Int, Int] = Point.apply(30,30) // modificare in base a prolog
  override var direction: Direction = Direction.START

  //quando ho prolog
  override def go(direction: Direction): Unit = {}
  override def position_=(point: Point[Int, Int]): Unit = pointPosition = point
  override def position(): Unit = pointPosition
}
