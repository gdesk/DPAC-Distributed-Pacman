package client

import client.gameElement.Dot
import client.utils.Point
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 26/06/2017.
  */


class DotTest extends FunSuite {

  test("A New Dot Position is correctly available") {
    val d: Dot = Dot(Point(5,6))

    assert(d.position.x == 5 && d.position.y == 6)
  }

  test ("method effect increment the current Match score"){
    val m: Match = new Match
    val d: Dot = Dot(Point(5,6))

    assert(m.score == 0)

    d.effect(m)

    assert(m.score == d.score)
  }

}


