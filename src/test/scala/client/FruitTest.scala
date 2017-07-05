package client

import client.gameElement.{Fruit, Fruits}
import client.utils.Point
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 26/06/2017.
  */
class FruitTest extends FunSuite{

  test("default fruit is a Cherry "){

    val fruit: Fruit = Fruit(Point(1,1))

    assert( fruit.fruitTypes == Fruits.Cherry && fruit.score == Fruits.Cherry.getScore)

  }

  test ("Fruit of different types are different") {

    val fruit1: Fruit = Fruit(Point(1,1), Fruits.Apple)
    val fruit2: Fruit = Fruit(Point(2,2), Fruits.Grapes)

    assert (fruit1.fruitTypes != fruit2.fruitTypes)
  }

<<<<<<< HEAD
  /*test ("method effect increment the current Match score"){
    val m: Match = new Match
    val d: Fruit = Fruit(Point(5,6))
=======
>>>>>>> 365060356dfa7ad4f3d318a5322a3c8940a37172



<<<<<<< HEAD
    assert(m.score == d.score)
  }*/
=======

>>>>>>> 365060356dfa7ad4f3d318a5322a3c8940a37172

}
