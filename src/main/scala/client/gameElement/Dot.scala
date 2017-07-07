package client.gameElement

import client.utils.{Point, ScoreUtils}

/**
  * A dot, used in the game as object that Pacman has to eat to win the game.
  * Is a subtype of trait [[client.gameElement.Eatable]]
  *
  * @constructor create a new dot with a position in the playground.
  * @param position the position in the playground.
  *
  * @author manuBottax
  */
case class Dot(override val position: Point[Int, Int]) extends Eatable {

  /**
    * Returns the value that is given as score when Pacman eat that item.
    *
    * @return the score value.
    */
  override val score: Int = ScoreUtils.DOT_SCORE

  /**
    * Returns the family to which the eatable object belongs
    *
    * @return object's family
    */
  override def belonginFamily: String = Eatables.Dot getFamily
}

/**
  * Factory for [[client.gameElement.Dot]] instances.
  */
object Dot {

  /**
    * Create a Dot with a given position.
    *
    * @param position its position.
    */
  def apply(position: Point[Int, Int]): Dot = new Dot(position)
}