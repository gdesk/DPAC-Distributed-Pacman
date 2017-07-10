package client

import client.gameElement._
import client.utils.{Dimension, PointImpl}
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 29/06/2017.
  */

class PlaygroundTest extends FunSuite{

  val playground: PlaygroundImpl = new PlaygroundImpl(Dimension(5,5))


  test("Block and eatable lists are empty in a new Playground"){

    assert (playground.eatables.isEmpty && playground.blocks.isEmpty)

  }

  val b2: Block = Block (PointImpl (2,2))
  val b3: Block = Block (PointImpl (3,3))

  test("Correctly add block on block list"){

    val b1: Block = Block (PointImpl (1,1))


    playground.addBlock(b1)
    playground.addBlock(b2)
    playground.addBlock(b3)

    assert(playground.blocks.size == 3)
  }

  val d: VirtualDot = VirtualDot (PointImpl (4,4))
  val f: VirtualFruit = VirtualFruit (PointImpl (1,4))

  test("Correctly add eatable on eatable list"){


    val p: VirtualPill = VirtualPill (PointImpl (5,5))

    playground.addEatable(d)
    playground.addEatable(p)
    playground.addEatable(f)

    assert(playground.eatables.size == 3)
  }

  test ("removeBlock remove the correct block from the list"){
    val b1: Block = Block (PointImpl (1,1))

    playground.removeBlock(b1)

    assert (playground.blocks.size == 2 && playground.blocks.head == b2 && playground.blocks(1) == b3)
  }

  test ("removeEatable remove the correct eatable from the list"){
    val p: VirtualPill = VirtualPill (PointImpl (5,5))
    playground.removeEatable(p)

    assert (playground.eatables.size == 2 && playground.eatables.head == d && playground.eatables(1) == f)
  }

  test ("search for an element out of the dimension of playground has no effect (return Empty)"){
    val point: PointImpl[Int,Int] = PointImpl(7,3)

    assert(playground.elementAtPosition(point) == Option.empty[GameItem])
  }

  test("search for an element in position find it properly"){
    val point: PointImpl[Int,Int] = PointImpl(4,4)

    assert(playground.elementAtPosition(point) == Option(d))
  }

  test("search for an element that not exist return Empty "){
    val point: PointImpl[Int,Int] = PointImpl(3,2)

    assert(playground.elementAtPosition(point) == Option.empty[GameItem])
  }

}
