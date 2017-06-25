package client.gameElement

import client.utils.Point
import client.utils.{Dimension, Point}

/**
  * Created by ManuBottax on 25/06/2017.
  */
class Block (override val position: Point[Double, Double] , val xDimension: Int, val yDimension: Int) extends GameItem [Double,Double] {

  val dimension: Dimension = Dimension (xDimension, yDimension)

}
