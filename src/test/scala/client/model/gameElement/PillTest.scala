package client.model.gameElement

import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * @author Margherita Pecorelli
  */
class PillTest extends FunSuite {

  val pill = Pill("Pill", PointImpl(1,3))

  test("score"){
    assert(pill.score equals 50)
  }

  test("name"){
    assert(pill.id equals "Pill")
  }

  test("position"){
    assert(pill.position equals PointImpl(1,3))
  }
}
