package client.model

import client.model.gameElement.Block
import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * Created by margherita on 13/07/17.
  */
class BlockTest extends FunSuite {

  val block = Block(PointImpl(0,3))

  test("position"){
    assert(block.position equals PointImpl(0,3))
  }
}
