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

  def canEqual(a: Any): Boolean = a.isInstanceOf[Point[T,W]]

  override def equals(that: Any): Boolean =
    that match {
      case that: Point[T,W] => that.canEqual(this) && ( this.x == that.x && this.y == that.y )
      case _ => false
    }

  override def hashCode: Int = {
    val prime = 7
    var result = 1
    result = prime * result + (if (x.isInstanceOf[Numeric[T]] && y.isInstanceOf[Numeric[W]] ) 3 else 0)
    result
  }
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
