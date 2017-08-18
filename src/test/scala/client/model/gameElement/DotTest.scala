package client.model.gameElement

import client.model.utils.PointImpl
import org.scalatest.FunSuite

/**
  * @author Manuel Bottazzi
  * @author Margherita Pecorelli
  */
class DotTest extends FunSuite {

  val dot = Dot("Dot", PointImpl(0,3))

  test("score"){
    assert(dot.score equals 10)
  }

  test("name"){
    assert(dot.id equals "Dot")
  }

  test("position"){
    assert(dot.position equals PointImpl(0,3))
  }

}
