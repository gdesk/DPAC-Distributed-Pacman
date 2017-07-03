package client.gameElement

import client.utils.{Dimension, Point, Position}


/** A block element for the playground, used to build walls.
  *
  * @author manuBottax
  */
trait Block extends GameItem {

  def dimension: Dimension
}

/** Implementation of [[Block]] for the virtual version of the game.
  *
  * @constructor create a new block with a position in the playground and a dimension.
  * @param position the position in the playground.
  * @param xDimension the dimension of the block on x axis. Default value is 1.
  * @param yDimension the dimension of the block on y axis. Default value is 1.
  */
class VirtualBlock(override val position: Position[Int, Int], val xDimension: Int = 1, val yDimension: Int = 1) extends Block {

  override val dimension: Dimension = Dimension (xDimension, yDimension)

  override def itemType: ItemType = ItemType.Block
}

/** Factory for [[client.gameElement.VirtualBlock]] instances. */
object VirtualBlock{

  /** Create a Block with given position and dimension
    *
    * @param position its position
    * @param xDimension its dimension on x axis
    * @param yDimension its dimension on y axis
    */
  def apply( position: Position[Int, Int] , xDimension: Int, yDimension: Int ): VirtualBlock = new VirtualBlock( position, xDimension, yDimension )

  /** Create a Block with a given position
    *
    * @param position its position
    * @return a new Block with dimension's default value (1,1)
    */
  def apply( position: Position[Int, Int]) = new VirtualBlock (position)
}