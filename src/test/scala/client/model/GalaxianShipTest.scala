package client.model

import client.model.gameElement.GalaxianShip
import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * Created by margherita on 13/07/17.
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

  test("belonginFamily"){
    assert(galaxianShip.belonginFamily equals "galaxian ship")
  }
}
