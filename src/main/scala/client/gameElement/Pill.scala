package client.gameElement

import client.utils.{Point, ScoreUtils}

/** A pill, used in the game as special item that Pacman can eat to scare the ghosts and make them vulnerable.
  * Is a subtype of trait [[client.gameElement.Eatable]]
  *
  * @constructor create a new pill with a position in the playground.
  * @param position the position in the playground.
  *
  * @author manuBottax
  */
class Pill (override val position: Point[Int, Int]) extends Eatable {

  override val score: Int = ScoreUtils.PILL_SCORE

  override def itemType: ItemType = ItemType.Pill

}

/** Factory for [[client.gameElement.Pill]] instances. */
object Pill {

  /** Create a Pill with a given position
    *
    * @param position its position
    */
  def apply (position: Point[Int, Int]) : Pill = new Pill(position)
}

