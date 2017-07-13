package client.model

import client.model.gameElement.{Cherry, Grapes, Orange, Strawberry, _}
import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 26/06/2017.
  */
class FruitTest(fruit: Fruit) extends FunSuite{

  test("belonginFamily"){
    assert(fruit.belonginFamily equals "fruit")
  }
}

class AppleTest extends FunSuite {

  val apple = Apple("Apple", PointImpl(0,0))

  val fruitTest = new FruitTest(apple)
  fruitTest.execute()

  test("score"){
    assert(apple.score equals 700)
  }

  test("name"){
    assert(apple.id equals "Apple")
  }

  test("position"){
    assert(apple.position equals PointImpl(0,0))
  }

}

class CherryTest extends FunSuite {

  val cherry = Cherry("Cherry", PointImpl(0,2))

  val fruitTest = new FruitTest(cherry)
  fruitTest.execute()

  test("score"){
    assert(cherry.score equals 100)
  }

  test("name"){
    assert(cherry.id equals "Cherry")
  }

  test("position"){
    assert(cherry.position equals PointImpl(0,2))
  }

}

class GrapesTest extends FunSuite {

  val grapes = Grapes("Grapes", PointImpl(1,0))

  val fruitTest = new FruitTest(grapes)
  fruitTest.execute()

  test("score"){
    assert(grapes.score equals 1000)
  }

  test("name"){
    assert(grapes.id equals "Grapes")
  }

  test("position"){
    assert(grapes.position equals PointImpl(1,0))
  }

}

class OrangeTest extends FunSuite {

  val orange = Orange("Orange", PointImpl(1,2))

  val fruitTest = new FruitTest(orange)
  fruitTest.execute()

  test("score"){
    assert(orange.score equals 500)
  }

  test("name"){
    assert(orange.id equals "Orange")
  }

  test("position"){
    assert(orange.position equals PointImpl(1,2))
  }

}

class StrawberryTest extends FunSuite {

  val strawberry = Strawberry("Strawberry", PointImpl(1,4))

  val fruitTest = new FruitTest(strawberry)
  fruitTest.execute()

  test("score"){
    assert(strawberry.score equals 300)
  }

  test("name"){
    assert(strawberry.id equals "Strawberry")
  }

  test("position"){
    assert(strawberry.position equals PointImpl(1,4))
  }
}