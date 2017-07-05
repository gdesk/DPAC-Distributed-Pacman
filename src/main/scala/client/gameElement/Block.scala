package client.gameElement

import client.utils.Position


/** A block element for the playground, used to build walls.
  *
  * @author manuBottax
  */
trait Block extends GameItem

/** Implementation of [[Block]] for the virtual version of the game.
  *
  * @constructor create a new block with a position in the playground and a dimension.
  * @param position the position in the playground.
  */
class VirtualBlock(override val position: Position[Int, Int]) extends Block {

  override def itemType: ItemType = ItemType.Block
}

/** Factory for [[client.gameElement.VirtualBlock]] instances. */
object VirtualBlock{

  /** Create a Block with a given position
    *
    * @param position its position
    * @return a new Block in the given position
    */
  def apply( position: Position[Int, Int]) = new VirtualBlock (position)
}