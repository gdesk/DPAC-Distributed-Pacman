package client.gameElement

import client.utils.{Position, ScoreUtils}

/** An eatable pill, used in the game as special item that Pacman can eat to scare the ghosts and make them vulnerable.
  *
  * @author manuBottax
  */
trait Pill extends Eatable



/** Implementation of [[Pill]] for the virtual version of the game.
  *
  * @constructor create a new pill with a position in the playground.
  * @param position the position in the playground.
  *
  */
class VirtualPill (override val position: Position[Int, Int]) extends Pill {

  override val score: Int = ScoreUtils.PILL_SCORE

  override def itemType: ItemType = ItemType.Pill

}

/** Factory for [[client.gameElement.VirtualPill]] instances. */
object VirtualPill {

  /** Create a Pill with a given position
    *
    * @param position its position
    */
  def apply (position: Position[Int, Int]) : VirtualPill = new VirtualPill(position)
}

