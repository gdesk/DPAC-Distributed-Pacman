package client.model.gameElement

import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * @author Margherita Pecorelli
  */
class BellTest extends FunSuite {

  val bell = Bell("Bell", PointImpl(0,1))

  test("score"){
    assert(bell.score equals 3000)
  }

  test("name"){
    assert(bell.id equals "Bell")
  }

  test("position"){
    assert(bell.position equals PointImpl(0,1))
  }

}