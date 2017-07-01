package view

import client.Match
import client.gameElement.Dot
import client.utils.Point
import org.scalatest.FunSuite

/**
  * Created by chiaravarini on 01/07/17.
  */
class CharacterViewTest extends FunSuite {

  private val resolution: String = Utils.getResolution().asString()

  test("Correct access to pacman images") {
    val pacmanView: PacmanView = new PacmanView()

    val pacmanUpImage = Utils.getImage("pacman/"+resolution+"/Up")
    assert(pacmanView.getCharacterUp == pacmanUpImage)
  }

  /*test ("method effect increment the current Match score"){
    val m: Match = new Match
    val d: Dot = Dot(Point(5,6))

    assert(m.score == 0)

    d.effect(m)

    assert(m.score == d.score)
  }*/

}
