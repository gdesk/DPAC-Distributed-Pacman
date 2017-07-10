package client

import client.gameElement.VirtualDot
import client.utils.PointImpl
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 26/06/2017.
  */


class DotTest extends FunSuite {

  test("A New Dot Position is correctly available") {
    val d: VirtualDot = VirtualDot(PointImpl(5,6))

    assert(d.position.x == 5 && d.position.y == 6)
  }

}


