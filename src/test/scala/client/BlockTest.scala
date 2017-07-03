package client

import client.gameElement.VirtualBlock
import client.utils.Point
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 26/06/2017.
  */
class BlockTest extends FunSuite{

  test("A New Block without dimension specification is 1x1 wide") {
    val b: VirtualBlock = new VirtualBlock(Point(5,6))

    assert(b.dimension.xDimension == 1 && b.dimension.yDimension == 1)
  }

  test("A New Block with dimension specification has correct dimension") {
    val b: VirtualBlock = new VirtualBlock(Point(1,2), 3, 4)

    assert(b.dimension.xDimension == 3 && b.dimension.yDimension == 4)
  }

}
