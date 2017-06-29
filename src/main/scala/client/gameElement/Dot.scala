package client.gameElement

import client.Match
import client.utils.{Point, ScoreUtils}

/** A dot, used in the game as object that Pacman has to eat to win the game.
  * Is a subtype of trait [[client.gameElement.Eatable]]
  *
  * @constructor create a new dot with a position in the playground.
  * @param position the position in the playground.
  *
  * @author manuBottax
  */
class Dot (override val position: Point[Double, Double]) extends Eatable {

  override val score: Int = ScoreUtils.DOT_SCORE
  override def effect (x: Match) : Unit = x.score = x.score + score

  //private def incrementScore = (x: Match) => x.score = x.score + score
}

/** Factory for [[client.gameElement.Dot]] instances. */
object Dot {

  /** Create a Dot with a given position
    *
    * @param position its position
    */
  def apply(position: Point[Double, Double]): Dot = new Dot(position)
}