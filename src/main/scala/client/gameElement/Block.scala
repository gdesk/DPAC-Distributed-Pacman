package client.gameElement

import client.utils.{Dimension, Point}

/** A block element for the playground, used to build wall.
  *
  * @constructor create a new block with a position in the playground and a dimension.
  * @param position the position in the playground.
  * @param xDimension the dimension of the block on x axis. Default value is 1.
  * @param yDimension the dimension of the block on y axis. Default value is 1.
  *
  * @author manuBottax
  */
class Block (override val position: Point[Int, Int] , val xDimension: Int = 1, val yDimension: Int = 1) extends GameItem {

  val dimension: Dimension = Dimension (xDimension, yDimension)

  override def itemType: String = "block"
}

/** Factory for [[client.gameElement.Block]] instances. */
object Block{

  /** Create a Block with given position and dimension
    *
    * @param position its position
    * @param xDimension its dimension on x axis
    * @param yDimension its dimension on y axis
    */
  def apply( position: Point[Int, Int] , xDimension: Int, yDimension: Int ): Block = new Block( position, xDimension, yDimension )

  /** Create a Block with a given position
    *
    * @param position its position
    * @return a new Block with dimension's default value (1,1)
    */
  def apply( position: Point[Int, Int]) = new Block (position)
}