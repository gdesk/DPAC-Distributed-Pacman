package client.utils

/** The position of an Item in the playground.
  *
  * @constructor create a new Point object.
  * @param x the position of the item on x axis.
  * @param y the position of the item on y axis.
  *
  * @tparam T the measure unit used on x axis.
  * @tparam W the measure unit used on y axis.
  *
  * @author manuBottax
  */
class Point [T,W] ( var x: T, var y: W){
  override def toString: String = "[" + this.x + " | " + this.y + "]"
}

/** Factory for [[client.utils.Dimension]] instances. */
object Point {

  /** Create a Dimension object with given x and y dimension.
    *
    * @param x the position of the item on x axis.
    * @param y the position of the item on y axis.
    *
    * @tparam T the measure unit used on x axis.
    * @tparam W the measure unit used on y axis.
    */
  def apply[T, W](x: T, y: W) = new Point (x, y)
}
