package client

import client.gameElement._
import client.utils.{Dimension, PointImpl}
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 29/06/2017.
  */

class PlaygroundTest extends FunSuite{

  val playground: VirtualPlayground = new VirtualPlayground(Dimension(5,5))


  test("Block and eatable lists are empty in a new Playground"){

    assert (playground.getAllEatable.isEmpty && playground.getAllBlocks.isEmpty)

  }

  val b2: VirtualBlock = VirtualBlock (PointImpl (2,2))
  val b3: VirtualBlock = VirtualBlock (PointImpl (3,3))

  test("Correctly add block on block list"){

    val b1: VirtualBlock = VirtualBlock (PointImpl (1,1))


    playground.addBlock(b1)
    playground.addBlock(b2)
    playground.addBlock(b3)

    assert(playground.getAllBlocks.size == 3)
  }

  val d: VirtualDot = VirtualDot (PointImpl (4,4))
  val f: VirtualFruit = VirtualFruit (PointImpl (1,4))

  test("Correctly add eatable on eatable list"){


    val p: VirtualPill = VirtualPill (PointImpl (5,5))

    playground.addEatable(d)
    playground.addEatable(p)
    playground.addEatable(f)

    assert(playground.getAllEatable.size == 3)
  }

  test ("removeBlock remove the correct block from the list"){
    val b1: VirtualBlock = VirtualBlock (PointImpl (1,1))

    playground.removeBlock(b1)

    assert (playground.getAllBlocks.size == 2 && playground.getAllBlocks.head == b2 && playground.getAllBlocks(1) == b3)
  }

  test ("removeEatable remove the correct eatable from the list"){
    val p: VirtualPill = VirtualPill (PointImpl (5,5))
    playground.removeEatable(p)

    assert (playground.getAllEatable.size == 2 && playground.getAllEatable.head == d && playground.getAllEatable(1) == f)
  }

  test ("search for an element out of the dimension of playground has no effect (return Empty)"){
    val point: PointImpl[Int,Int] = PointImpl(7,3)

    assert(playground.getElementAtPosition(point) == Option.empty[GameItem])
  }

  test("search for an element in position find it properly"){
    val point: PointImpl[Int,Int] = PointImpl(4,4)

    assert(playground.getElementAtPosition(point) == Option(d))
  }

  test("search for an element that not exist return Empty "){
    val point: PointImpl[Int,Int] = PointImpl(3,2)

    assert(playground.getElementAtPosition(point) == Option.empty[GameItem])
  }

}
