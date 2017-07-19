package client.model.character

import client.model.{MatchImpl, PlaygroundImpl}
import org.scalatest.FunSuite
import client.model.utils.{BaseEatObjectStrategy, Dimension, PointImpl}

import scala.collection.mutable.{HashMap, Map}

/**
  * @author Margherita Pecorelli
  */
class BasePacmanTest extends FunSuite {

  val playground = PlaygroundImpl instance()
  playground dimension = Dimension(35,35)

  val gameMatch = MatchImpl instance()

  val pacman = BasePacman("Pacman", BaseEatObjectStrategy())

  val characterTest = new CharacterImplTest(pacman)
  characterTest.execute()

  /*
  test("lives") {
    assert(pacman.lives.remainingLives equals (InitializedInfoImpl.getCharacterLives("pacman")))
    assert(pacman.lives.remainingLives equals 3)
  }
  */

  test("name") {
    assert(pacman.name equals "Pacman")
  }

  /*
  test("position and setPosition") {
    assert(pacman.position equals (InitializedInfoImpl.getStartPosition("pacman")))
    assert(pacman.position equals PointImpl(30, 30))

    pacman setPosition PointImpl(10, 10)
    assert(pacman.position equals PointImpl(10, 10))

    assertThrows[OutOfPlaygroundBoundAccessException] {
      pacman setPosition PointImpl(35, 35)
    }
  }

  test("direction") {
    assert(pacman.direction equals Direction.START)
    pacman direction = Direction.DOWN
    assert(pacman.direction equals Direction.DOWN)
  }

  test("isAlive") {
    assert(pacman.isAlive equals true)
    pacman isAlive = false
    assert(pacman.isAlive equals false)
  }

  test("isKillable") {
    assert(pacman.isKillable equals true)
    pacman isKillable = false
    assert(pacman.isKillable equals false)
  }

  test("score") {
    assert(pacman.score equals 0)
    pacman score = 5
    assert(pacman.score equals 5)
  }

  test("go") {
    playground ground = List empty //tutte strade

    var posX = pacman.position x;
    var posY = pacman.position y;

    pacman go Direction.RIGHT
    assert(pacman.position.x equals posX + 1)
    posX = pacman.position x;

    pacman go Direction.LEFT
    assert(pacman.position.x equals posX - 1)
    posX = pacman.position x;

    pacman go Direction.UP
    assert(pacman.position.x equals posY - 1)
    posY = pacman.position y;

    pacman go Direction.DOWN
    assert(pacman.position.x equals posY + 1)
    posY = pacman.position y;
  }
*/
  /*
  DA FINIREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
  test("eatObject") {
    val score = pacman score;
    val cherry = Cherry("Cherry", PointImpl(0, 2))
    playground ground = List((Apple("Apple", PointImpl(0, 0))),
                             (Cherry("Cherry", PointImpl(0, 2))),
                             (Dot("Dot", PointImpl(0, 3))),
                             (Grapes("Grapes", PointImpl(1, 0))),
                             (Key("Key", PointImpl(1, 1))),
                             (Pill("Pill", PointImpl(1, 3))))
    pacman setPosition PointImpl(0, 1)

    assert(playground.eatables contains cherry)
    assert(!(playground.eatenObjects contains cherry))

    pacman go Direction.RIGHT

    assert(pacman.score equals score + cherry.score)
    assert(!(playground.eatables contains cherry))
    assert(playground.eatenObjects contains cherry)
  }
 */

  test("checkAllPositions") {
    val blueGhost = BaseGhost("Blue")
    blueGhost setPosition PointImpl(0,0)
    val greeenGhost = BaseGhost("Green")
    greeenGhost setPosition PointImpl(0,1)
    val yellowGhost = BaseGhost("Yellow")
    yellowGhost setPosition PointImpl(0,2)
    var usersAndCharacters: Map[Character, String] = HashMap((blueGhost, "Giuls"),
                                                             (greeenGhost, "Manu"),
                                                             (yellowGhost, "Fede"))
    gameMatch addPlayers usersAndCharacters
    gameMatch myCharacter = pacman

    pacman setPosition PointImpl(0,1)
    pacman isKillable = false
    greeenGhost isKillable = true
    val lives = pacman.lives remainingLives;
    val score = pacman score;
    pacman checkAllPositions;
    assert(pacman.lives.remainingLives equals lives)
    assert(pacman.score > score)

    pacman.lives decrementOf 2
    pacman setPosition PointImpl(0,0)
    pacman isKillable = true
    pacman checkAllPositions;
    assert(pacman.lives.remainingLives equals 0)
    assert(pacman.isAlive equals false)
  }

}
