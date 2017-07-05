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
<<<<<<< HEAD

  test ("method effect increment the current Match score and scare ghost"){
    /*val m: Match = new Match
    val p: Pill = Pill(Point(5,6))

    assert(m.score == 0 && m.ghostState)

    p.effect(m)

    assert(m.score == p.score)
    assert(!m.ghostState)*/
  }
=======
>>>>>>> 365060356dfa7ad4f3d318a5322a3c8940a37172
}
