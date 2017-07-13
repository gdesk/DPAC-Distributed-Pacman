package client.model

import client.model.gameElement.Key
import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * Created by margherita on 13/07/17.
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

  test("belonginFamily"){
    assert(key.belonginFamily equals "key")
  }
}
