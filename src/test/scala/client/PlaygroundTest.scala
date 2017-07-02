package client

import client.gameElement._
import client.utils.{Dimension, Point}
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 29/06/2017.
  */

class PlaygroundTest extends FunSuite{

  val playground: Playground = new Playground(Dimension(5,5))


  test("Block and eatable lists are empty in a new Playground"){

    assert (playground.getAllEatable.isEmpty && playground.getAllBlocks.isEmpty)

  }

  val b2: Block = Block (Point (2,2), 1, 6)
  val b3: Block = Block (Point (3,3))

  test("Correctly add block on block list"){

    val b1: Block = Block (Point (1,1))


    playground.addBlock(b1)
    playground.addBlock(b2)
    playground.addBlock(b3)

    assert(playground.getAllBlocks.size == 3)
  }

  val d: Dot = Dot (Point (4,4))
  val f: Fruit = Fruit (Point (1,4))

  test("Correctly add eatable on eatable list"){


    val p: Pill = Pill (Point (5,5))

    playground.addEatable(d)
    playground.addEatable(p)
    playground.addEatable(f)

    assert(playground.getAllEatable.size == 3)
  }

  test ("removeBlock remove the correct block from the list"){
    val b1: Block = Block (Point (1,1))

    playground.removeBlock(b1)

    assert (playground.getAllBlocks.size == 2 && playground.getAllBlocks.head == b2 && playground.getAllBlocks(1) == b3)
  }

  test ("removeEatable remove the correct eatable from the list"){
    val p: Pill = Pill (Point (5,5))
    playground.removeEatable(p)

    assert (playground.getAllEatable.size == 2 && playground.getAllEatable.head == d && playground.getAllEatable(1) == f)
  }

  test ("search for an element out of the dimension of playground has no effect (return Empty)"){
    val point: Point[Int,Int] = Point(7,3)

    assert(playground.getElementAtPosition(point) == Option.empty[GameItem])
  }

  test("search for an element in position find it properly"){
    val point: Point[Int,Int] = Point(4,4)

    assert(playground.getElementAtPosition(point) == Option(d))
  }

  test("search for an element that not exist return Empty "){
    val point: Point[Int,Int] = Point(3,2)

    assert(playground.getElementAtPosition(point) == Option.empty[GameItem])
  }

}
