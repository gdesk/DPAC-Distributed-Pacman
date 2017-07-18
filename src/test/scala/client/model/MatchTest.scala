package client.model

import client.model.character.{BaseEatObjectStrategy, BaseGhost, BasePacman}
import client.model.character.Character
import client.model.gameElement._
import client.model.utils.{Dimension, PointImpl}
import org.scalatest.FunSuite

import scala.collection.mutable.HashMap
import scala.collection.mutable.Map


/**
  * @author Margherita Pecorelli
  */
class MatchTest extends FunSuite {

  val gameMatch: Match = MatchImpl instance()

  val playground: PlaygroundImpl = PlaygroundImpl instance()
  playground dimension = Dimension(35,35)

  val redGhost = BaseGhost("Red")
  val pacman = BasePacman("Pacman", BaseEatObjectStrategy())
  val blueGhost = BaseGhost("Blue")
  val greeenGhost = BaseGhost("Green")
  val yellowGhost = BaseGhost("Yellow")

  test("playground") {
    playground ground = List((Apple("Apple", PointImpl(0,0))),
                             (Bell("Bell", PointImpl(0,1))),
                             (Cherry("Cherry", PointImpl(0,2))),
                             (Dot("Dot", PointImpl(0,3))),
                             (GalaxianShip("Galaxian Ship", PointImpl(0,4))),
                             (Grapes("Grapes", PointImpl(1,0))),
                             (Key("Key", PointImpl(1,1))),
                             (Orange("Orange", PointImpl(1,2))),
                             (Pill("Pill", PointImpl(1,3))),
                             (Strawberry("Strawberry", PointImpl(1,4))),
                             (Block(PointImpl(2,0))),
                             (Block(PointImpl(2,1))),
                             (Block(PointImpl(2,2))),
                             (Block(PointImpl(2,3))),
                             (Block(PointImpl(2,4))),
                             (Block(PointImpl(3,0))))

    assert(gameMatch.playground == null)
    gameMatch playground = playground
    assert(gameMatch.playground equals playground)
  }

  test("myCharacter") {
    assert(gameMatch.myCharacter == null)
    gameMatch myCharacter = redGhost;
    assert(gameMatch.myCharacter equals redGhost)
  }

  test("addPlayers and characters") {
    assert(gameMatch.characters isEmpty)
    var usersAndCharacters: Map[Character, String] = HashMap((pacman, "Marghe"),
                                                             (blueGhost, "Giuls"),
                                                             (greeenGhost, "Manu"),
                                                             (yellowGhost, "Fede"))
    gameMatch addPlayers usersAndCharacters
    assert(gameMatch.characters.size equals 4)
    assert(gameMatch.characters contains BasePacman("Pacman", BaseEatObjectStrategy()))
    assert(gameMatch.characters contains BaseGhost("Blue"))
    assert(gameMatch.characters contains BaseGhost("Green"))
    assert(gameMatch.characters contains BaseGhost("Yellow"))
  }

  test("characters, deadCharacters and addDeadCharacters") {
    assert(gameMatch.deadCharacters isEmpty)

    gameMatch.addDeadCharacters(pacman)
    assert(gameMatch.deadCharacters contains BasePacman("Pacman", BaseEatObjectStrategy()))

    gameMatch.addDeadCharacters(blueGhost)
    assert(gameMatch.deadCharacters contains BaseGhost("Blue"))

    gameMatch.addDeadCharacters(greeenGhost)
    assert(gameMatch.deadCharacters contains BaseGhost("Green"))

    gameMatch.addDeadCharacters(yellowGhost)
    assert(gameMatch.deadCharacters contains BaseGhost("Yellow"))

    gameMatch.addDeadCharacters(redGhost)
    assert(gameMatch.deadCharacters contains BaseGhost("Red"))

    val blackGhost = BaseGhost("Black")

    assertThrows[CharacterDoesNotExistException] {
      gameMatch.addDeadCharacters(blackGhost)
    }
  }

  test ("singleton"){
    val gm = MatchImpl instance()
    assert(gameMatch == gm)
  }

}
