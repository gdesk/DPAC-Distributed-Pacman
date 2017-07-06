package client

import client.gameElement. Pill
import client.utils.Point
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 26/06/2017.
  */
class PillTest extends FunSuite{
  test("A New Pill Position is correctly available") {
    val p: Pill = Pill(Point(5,6))

    assert(p.position.x == 5 && p.position.y == 6)
  }
}
