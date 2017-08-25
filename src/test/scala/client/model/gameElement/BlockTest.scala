package client.model.gameElement

import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * @author Margherita Pecorelli
  */
class BlockTest extends FunSuite {

  val block = Block(PointImpl(0,3))

  test("position"){
    assert(block.position equals PointImpl(0,3))
  }

}
