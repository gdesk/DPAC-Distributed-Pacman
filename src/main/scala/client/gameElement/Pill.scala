package client.gameElement

import client.Match
import client.utils.{Point, ScoreUtils}

/** A pill, used in the game as special item that Pacman can eat to scare the ghosts and make them vulnerable.
  * Is a subtype of trait [[client.gameElement.Eatable]]
  *
  * @constructor create a new pill with a position in the playground.
  * @param position the position in the playground.
  *
  * @author manuBottax
  */
class Pill (override val position: Point[Double, Double]) extends Eatable {

  override val score: Int = ScoreUtils.PILL_SCORE
  override def effect (x: Match) : Unit  = scareGhostAndIncrement (x)

  private def scareGhostAndIncrement = (x: Match) => {
    x.score = x.score + score
    x.scareGhost()
  }
}

/** Factory for [[client.gameElement.Pill]] instances. */
object Pill {

  /** Create a Pill with a given position
    *
    * @param position its position
    */
  def apply (position: Point[Double, Double]) : Pill = new Pill(position)
}

