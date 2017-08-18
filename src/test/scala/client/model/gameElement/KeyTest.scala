package client.model.gameElement

import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * @author Margherita Pecorelli
  */
class KeyTest extends FunSuite {

  val key = Key("Key", PointImpl(1,1))

  test("score"){
    assert(key.score equals 5000)
  }

  test("name"){
    assert(key.id equals "Key")
  }

  test("position"){
    assert(key.position equals PointImpl(1,1))
  }

}
