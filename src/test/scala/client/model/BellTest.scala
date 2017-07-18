package client.model

import client.model.gameElement.Bell
import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * Created by margherita on 13/07/17.
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