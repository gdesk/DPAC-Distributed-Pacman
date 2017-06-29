package client.gameElement

import client.utils.{Dimension, Point}

/**
  * Created by ManuBottax on 25/06/2017.
  */
class Block (override val position: Point[Double, Double] , val xDimension: Int = 1, val yDimension: Int = 1) extends GameItem [Double,Double] {

  val dimension: Dimension = Dimension (xDimension, yDimension)

}

object Block{

  def apply( position: Point[Double, Double] , xDimension: Int, yDimension: Int ): Block = new Block( position, xDimension, yDimension )
  def apply( position: Point[Double, Double]) = new Block (position, 1, 1)
}