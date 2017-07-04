package client.utils

/** The dimension of an Item in the playground.
  *
  * @constructor create a new Dimension object.
  * @param xDimension the dimension of the item on x axis.
  * @param yDimension the dimension of the item on y axis.
  *
  * @author manuBottax
  */
class Dimension (val xDimension: Int, val yDimension: Int)

/** Factory for [[client.utils.Dimension]] instances. */
object Dimension{

  /** Create a Dimension object with given x and y dimension.
    *
    * @param xDimension the dimension of the item on x axis.
    * @param yDimension the dimension of the item on y axis.
    */
  def apply(xDimension: Int, yDimension: Int): Dimension = new Dimension(xDimension, yDimension)
}
