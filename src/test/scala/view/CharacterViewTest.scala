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
    val pacmanView: CharacterViewImpl = new PacmanView()

    var pacmanImage = Utils.getImage("pacman/"+resolution+"/Up")
    assert(pacmanView.getCharacterUp == pacmanImage)

    pacmanImage= Utils.getImage("pacman/"+resolution+"/Down")
    assert(pacmanView.getCharacterDown == pacmanImage)

    pacmanImage= Utils.getImage("pacman/"+resolution+"/Left")
    assert(pacmanView.getCharacterLeft == pacmanImage)

    pacmanImage= Utils.getImage("pacman/"+resolution+"/Right")
    assert(pacmanView.getCharacterRight == pacmanImage)
  }


  test("Correct access to ghost images") {
    //val ghostColor = "red"
    // val ghostView: CharacterViewImpl = new RedGhostView()
    //val ghostColor = "blue"
    //val ghostView: CharacterViewImpl = new BlueGhostView()
    //val ghostColor = "pink"
    //val ghostView: CharacterViewImpl = new PinkGhostView()
    val ghostColor = "yellow"
    val ghostView: CharacterViewImpl = new YellowGhostView()

    var ghostImage = Utils.getImage("ghosts/"+ghostColor+"/"+resolution+"/Up")

    assert(ghostView.getCharacterUp == ghostImage)

    ghostImage= Utils.getImage("ghosts/"+ghostColor+"/"+resolution+"/Down")
    assert(ghostView.getCharacterDown == ghostImage)

    ghostImage= Utils.getImage("ghosts/"+ghostColor+"/"+resolution+"/Left")
    assert(ghostView.getCharacterLeft == ghostImage)

    ghostImage= Utils.getImage("ghosts/"+ghostColor+"/"+resolution+"/Right")
    assert(ghostView.getCharacterRight == ghostImage)
  }
  
}
