package client.model

import client.model.gameElement._
import client.model.utils.{Dimension, PointImpl}
import org.scalatest.FunSuite

/**
  * @author manuBottax
  * @author Margherita Pecorelli.
  */

class PlaygroundTest extends FunSuite{

  val playground: Playground = PlaygroundImpl

  test("Dimention"){
    assert ((playground.dimension.x equals 0) && (playground.dimension.y equals 0))
    playground dimension = Dimension(5,5)
    assert ((playground.dimension.x equals 5) && (playground.dimension.y equals 5))
  }

  test("Empty ground, blocks and eatables"){
    assert (playground.ground isEmpty)
    assert (playground.blocks isEmpty)
    assert (playground.eatables isEmpty)
  }

  test("Ground: OutOfPlaygroundBoundAccessException and AlredyUsedPositionException"){
    playground ground = List((Apple("apple", PointImpl(4,4))))
    playground ground = List((Apple("apple", PointImpl(0,0))))

    assertThrows[OutOfPlaygroundBoundAccessException] {
      playground ground = List((Apple("apple", PointImpl(5,5))))
    }

    assertThrows[OutOfPlaygroundBoundAccessException] {
      playground ground = List((Apple("apple", PointImpl(6,2))))
    }

    assertThrows[OutOfPlaygroundBoundAccessException] {
      playground ground = List((Apple("apple", PointImpl(1,6))))
    }

    assertThrows[OutOfPlaygroundBoundAccessException] {
      playground ground = List((Apple("apple", PointImpl(-1,-1))))
    }

    assertThrows[OutOfPlaygroundBoundAccessException] {
      playground ground = List((Apple("apple", PointImpl(-1,3))))
    }

    assertThrows[OutOfPlaygroundBoundAccessException] {
      playground ground = List((Apple("apple", PointImpl(4,-1))))
    }

    assertThrows[AlredyUsedPositionException] {
      playground ground = List((Orange("orange", PointImpl(2,3))), (Cherry("cherry", PointImpl(2,3))))
    }
  }

  test("blocks, eatables and streets"){
    playground ground = List((Apple("Apple", PointImpl(0,0))),
                             (Bell("Bell", PointImpl(0,1))),
                             (Cherry("Cherry", PointImpl(0,2))),
                             (Dot("Dot", PointImpl(0,3))),
                             (GalaxianShip("apGalaxianShipple", PointImpl(0,4))),
                             (Grape("Grapes", PointImpl(1,0))),
                             (Key("Key", PointImpl(1,1))),
                             (Orange("Orange", PointImpl(1,2))),
                             (Pill("Pill", PointImpl(1,3))),
                             (Strawberry("Strawberry", PointImpl(1,4))),
                             (Block(PointImpl(2,0))),
                             (Block(PointImpl(2,1))),
                             (Block(PointImpl(2,2))),
                             (Block(PointImpl(2,3))),
                             (Block(PointImpl(2,4))),
                             (Block(PointImpl(3,0))))

    assert(playground.eatables.size equals 10)
    assert(playground.blocks.size equals 6)

    assert(playground.eatables contains((Apple("Apple", PointImpl(0,0)))))
    assert(playground.eatables contains((Bell("Bell", PointImpl(0,1)))))
    assert(playground.eatables contains((Cherry("Cherry", PointImpl(0,2)))))
    assert(playground.eatables contains((Dot("Dot", PointImpl(0,3)))))
    assert(playground.eatables contains((GalaxianShip("apGalaxianShipple", PointImpl(0,4)))))
    assert(playground.eatables contains((Grape("Grapes", PointImpl(1,0)))))
    assert(playground.eatables contains((Key("Key", PointImpl(1,1)))))
    assert(playground.eatables contains((Orange("Orange", PointImpl(1,2)))))
    assert(playground.eatables contains((Pill("Pill", PointImpl(1,3)))))
    assert(playground.eatables contains((Strawberry("Strawberry", PointImpl(1,4)))))

    assert(playground.blocks contains((Block(PointImpl(2,0)))))
    assert(playground.blocks contains((Block(PointImpl(2,1)))))
    assert(playground.blocks contains((Block(PointImpl(2,2)))))
    assert(playground.blocks contains((Block(PointImpl(2,3)))))
    assert(playground.blocks contains((Block(PointImpl(2,4)))))
    assert(playground.blocks contains((Block(PointImpl(3,0)))))

    assert(playground.streetPositions.size equals (playground.dimension.x*playground.dimension.y-playground.blocks.size))
    for(i <- 0 to playground.dimension.x-1) {
      for(j <- 0 to playground.dimension.y-1) {
        if((playground.blocks filter (e => e.position equals PointImpl(i,j)) size) equals 0) assert(playground.streetPositions contains(PointImpl(i,j)))
      }
    }


    playground ground = List((Apple("Apple", PointImpl(0,0))),
                             (Block(PointImpl(4,4))))

    assert(playground.eatables.size equals 1)
    assert(playground.blocks.size equals 1)

    assert(playground.eatables contains((Apple("Apple", PointImpl(0,0)))))
    assert(playground.blocks contains((Block(PointImpl(4,4)))))

    assert(playground.streetPositions.size equals (playground.dimension.x*playground.dimension.y-playground.blocks.size))
    for(i <- 0 to playground.dimension.x-1) {
      for(j <- 0 to playground.dimension.y-1) {
        if((playground.blocks filter (e => e.position equals PointImpl(i,j)) size) equals 0) assert(playground.streetPositions contains(PointImpl(i,j)))
      }
    }
  }

  test ("removeEatable and eatenObjects"){
    assert(playground.eatables contains(Apple("Apple", PointImpl(0,0))))
    playground.removeEatable(Apple("Apple", PointImpl(0,0)))
    assert(!(playground.eatables contains(Apple("Apple", PointImpl(0,0)))))
    assert(!(playground.ground contains(Apple("Apple", PointImpl(0,0)))))
    assert(playground.eatenObjects contains(Apple("Apple", PointImpl(0,0))))
  }

  test ("elementAtPosition"){
    playground ground = List((Apple("Apple", PointImpl(0,0))),
                             (Bell("Bell", PointImpl(0,1))),
                             (Dot("Dot", PointImpl(0,3))),
                             (Block(PointImpl(2,0))),
                             (Block(PointImpl(2,1))),
                             (Block(PointImpl(3,0))))

    assert(playground.elementAtPosition(PointImpl(0,0)).get equals (Apple("Apple", PointImpl(0,0))))
    assert(playground.elementAtPosition(PointImpl(0,1)).get equals (Bell("Bell", PointImpl(0,1))))
    assert(playground.elementAtPosition(PointImpl(0,3)).get equals (Dot("Dot", PointImpl(0,3))))
    assert(playground.elementAtPosition(PointImpl(2,0)).get equals (Block(PointImpl(2,0))))
    assert(playground.elementAtPosition(PointImpl(2,1)).get equals (Block(PointImpl(2,1))))
    assert(playground.elementAtPosition(PointImpl(3,0)).get equals (Block(PointImpl(3,0))))

    for(i <- 0 to playground.dimension.x-1) {
      for(j <- 0 to playground.dimension.y-1) {
        if((playground.ground filter (e => e.position equals PointImpl(i,j)) size) equals 0) assert(playground.elementAtPosition(PointImpl(i,j)) isEmpty)
      }
    }

    assertThrows[OutOfPlaygroundBoundAccessException] {
      playground.elementAtPosition(PointImpl(5,5))
    }

    assertThrows[OutOfPlaygroundBoundAccessException] {
      playground.elementAtPosition(PointImpl(-1,-1))
    }

  }

  test ("singleton"){
   val pg = PlaygroundImpl
    assert(playground == pg)
  }

}
