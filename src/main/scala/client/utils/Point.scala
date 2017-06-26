package client.utils

/**
  * Created by ManuBottax on 25/06/2017.
  */

class Point [T,W] ( var x: T, var y: W){
  override def toString: String = "[" + this.x + " | " + this.y + "]"
}

object Point {
  def apply[T, W](x: T, y: W) = new Point (x, y)
}
