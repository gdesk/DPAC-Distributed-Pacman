package client.model.gameElement

import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * @author Margherita Pecorelli
  */
class GalaxianShipTest extends FunSuite {

  val galaxianShip = GalaxianShip("Galaxian Ship", PointImpl(0,4))

  test("score"){
    assert(galaxianShip.score equals 2000)
  }

  test("name"){
    assert(galaxianShip.id equals "Galaxian Ship")
  }

  test("position"){
    assert(galaxianShip.position equals PointImpl(0,4))
  }

}
