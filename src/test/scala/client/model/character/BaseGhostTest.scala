package client.model.character

import client.model.{MatchImpl, PlaygroundImpl}
import client.model.utils.{BaseEatObjectStrategy, Dimension, PointImpl}
import org.scalatest.FunSuite

import scala.collection.mutable.{HashMap, Map}

/**
  * Created by margherita on 13/07/17.
  */
class BaseGhostTest extends FunSuite {

  val playground = PlaygroundImpl instance()
  playground dimension = Dimension(35,35)

  val gameMatch = MatchImpl instance()

  val ghost = BaseGhost("Red")

  val characterTest = new CharacterImplTest(ghost)
  characterTest.execute()

  /*
  test("lives") {
    assert(ghost.lives.remainingLives equals (InitializedInfoImpl.getCharacterLives("ghost")))
    assert(ghost.lives.remainingLives equals 1)
  }
  */

  test("name") {
    assert(ghost.name equals "Red")
  }

  /*
  test("position and setPosition") {
    //assert(ghost.position equals (InitializedInfoImpl.getStartPosition("ghost")))
    assert(ghost.position equals PointImpl(20, 20))

    ghost setPosition PointImpl(10, 10)
    assert(ghost.position equals PointImpl(10, 10))

    assertThrows[OutOfPlaygroundBoundAccessException] {
      ghost setPosition PointImpl(35, 35)
    }
  }

  test("direction") {
    assert(ghost.direction equals Direction.START)
    ghost direction = Direction.DOWN
    assert(ghost.direction equals Direction.DOWN)
  }

  test("isAlive") {
    assert(ghost.isAlive equals true)
    ghost isAlive = false
    assert(ghost.isAlive equals false)
  }

  test("isKillable") {
    assert(ghost.isKillable equals false)
    ghost isKillable = true
    assert(ghost.isKillable equals true)
  }

  test("score") {
    assert(ghost.score equals 0)
    ghost score = 5
    assert(ghost.score equals 5)
  }

  test("go") {
    playground ground = List empty //tutte strade

    var posX = ghost.position x;
    var posY = ghost.position y;

    ghost go Direction.RIGHT
    assert(ghost.position.x equals posX + 1)
    posX = ghost.position x;

    ghost go Direction.LEFT
    assert(ghost.position.x equals posX - 1)
    posX = ghost.position x;

    ghost go Direction.UP
    assert(ghost.position.x equals posY - 1)
    posY = ghost.position y;

    ghost go Direction.DOWN
    assert(ghost.position.x equals posY + 1)
    posY = ghost.position y;
  }
  */

  test("checkAllPositions") {
    val pacman = BasePacman("Pacman1", BaseEatObjectStrategy())
    pacman setPosition PointImpl(0,0)
    val blueGhost = BaseGhost("Blue")
    blueGhost setPosition PointImpl(0,3)
    val greeenGhost = BaseGhost("Green")
    greeenGhost setPosition PointImpl(0,1)
    val yellowGhost = BaseGhost("Yellow")
    yellowGhost setPosition PointImpl(0,2)
    var usersAndCharacters: Map[Character, String] = HashMap((pacman, "Giuls"),
                                                             (blueGhost, "Giuls"),
                                                             (greeenGhost, "Manu"),
                                                             (yellowGhost, "Fede"))
    gameMatch addPlayers usersAndCharacters
    gameMatch myCharacter = ghost
    ghost setPosition PointImpl(0,0)
    ghost isKillable = false
    pacman isKillable = true
    val lives = ghost.lives remainingLives;
    val score = ghost score;
    ghost checkAllPositions;
    assert(ghost.lives.remainingLives equals lives)
    assert(ghost.score > score)

    ghost setPosition PointImpl(0,1)
    ghost isKillable = true
    pacman isKillable = false
    ghost checkAllPositions;
    assert(ghost.lives.remainingLives equals 0)
    assert(ghost.isAlive equals false)
  }

}