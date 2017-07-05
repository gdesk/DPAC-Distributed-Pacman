package client

import client.gameElement.Fruit
import client.utils.Point
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 26/06/2017.
  */
class FruitTest extends FunSuite{

  test("score for a fruit is a random value (not always the same) "){

    var score: Int = 0

    val fruit: Fruit = Fruit(Point(1,2))

    for (i <- 0 to 20){
      val f: Fruit = Fruit(Point(3,4))
      score = score + f.score
    }

    assert((score / 20)  != fruit.score)

  }

  /*test ("method effect increment the current Match score"){
    val m: Match = new Match
    val d: Fruit = Fruit(Point(5,6))

    assert(m.score == 0)

    d.effect(m)

    assert(m.score == d.score)
  }*/

}
