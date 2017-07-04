package client.character

import alice.tuprolog.Term
import characterjava.Direction
import client.gameElement.GameItem
import client.utils.{Point, ScalaProlog}

/**
  * Manages the base model of character.
  *
  * @author Giulia Lucchi
  */
trait Character[X,Y] extends GameItem[X,Y]{
  /**
    * Manages the character's movement and consequently the contact with other item of the game.
    *
    * @param direction    character's of direction
    */
  def go(direction: Direction): Unit

  /**
    * Manage the the strategy of game, that is based on who the killer is and who the killable
    */
  def checkAllPositions(): Unit

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
  def direction_=(direction: Direction): Unit

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

  /**
    * getter of current character's score
    *
    * @return current character's score
    */
  def score: Int

  /**
    * Setter of current chararcter's score
    *
    * @param score incremented score
    */
  def score_=(score: Int) : Unit
}

abstract class CharacterImpl(override var isKillable: Boolean, override val lives: Lives) extends Character[Int, Int] {
  override var isAlive: Boolean = true
  var pointPosition: Point[Int, Int] = InitializedInfoImpl.getStartPosition()
  override var direction: Direction = Direction.START
  override var score: Int = 0

  /**
    * setter character's position
    *
    * @param point a point of character within the game map
    *
    **/
  override def setPosition(point: Point[Int, Int]): Unit = pointPosition = point

  /**
    * getter character's position
    *
    * @return position    a point of character within the game map
    * */
  override def position: Point[Int, Int] = pointPosition

  /**
    * Manages the character's movement and consequently the contact with other item of the game.
    *
    * @param direction    character's of direction
    */
  override def go(direction: Direction): Unit = {
    if(ScalaProlog.solveWithSuccess(PrologConfig.ENGINE, Term.createTerm(s"move(${position x}, ${position y}, ${direction getDirection}, X1,Y1)"))){
      val point: Point[Int,Int] = this.move(direction)
      setPosition(point)
      checkAllPositions()
    }else{
      println("NO. it hit the wall.")
    }

  }

  def checkAllPositions(): Unit

  /**
    * Recall the predicate about the character's movement .
    * @param direction  the character's direction during the movement
    * @return           the new character's position after the movement
    */
  private def move (direction: Direction): Point[Int, Int] = {
    val solveInfo = PrologConfig.getPrologEngine().solve(s"move(${this.position.x}, ${this.position.y},${direction getDirection}, X, Y).")
    val x = Integer.valueOf(solveInfo.getTerm("X").toString)
    val y = Integer.valueOf(solveInfo.getTerm("Y").toString)
    Point[Int, Int](x, y)
  }
}
