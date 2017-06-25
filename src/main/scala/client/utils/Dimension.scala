package client.utils

/**
  * Created by ManuBottax on 25/06/2017.
  */
class Dimension (val xDimension: Int, val yDimension: Int)


object Dimension{
  def apply(xDimension: Int, yDimension: Int): Dimension = new Dimension(xDimension, yDimension)
}
