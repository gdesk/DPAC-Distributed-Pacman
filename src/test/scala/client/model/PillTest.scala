package client.model

import client.model.gameElement.Pill
import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * Created by ManuBottax on 26/06/2017.
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

  test("belonginFamily"){
    assert(pill.belonginFamily equals "pill")
  }
}
