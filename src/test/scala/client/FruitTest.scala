package client

import client.gameElement.{VirtualFruit, Fruits}
import client.utils.Point
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 26/06/2017.
  */
class FruitTest extends FunSuite{

  test("default fruit is a Cherry "){

    val fruit: VirtualFruit = VirtualFruit(Point(1,1))

    assert( fruit.fruitTypes == Fruits.Cherry && fruit.score == Fruits.Cherry.getScore)

  }

  test ("Fruit of different types are different") {

    val fruit1: VirtualFruit = VirtualFruit(Point(1,1), Fruits.Apple)
    val fruit2: VirtualFruit = VirtualFruit(Point(2,2), Fruits.Grapes)

    assert (fruit1.fruitTypes != fruit2.fruitTypes)
  }






}
