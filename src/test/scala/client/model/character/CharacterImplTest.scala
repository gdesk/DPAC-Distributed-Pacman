package client.model.character

import client.model.utils.{Dimension, PointImpl}
import client.model.{OutOfPlaygroundBoundAccessException, PlaygroundImpl}
import org.scalatest.FunSuite

/**
  * @author Giulia Lucchi
  * @author Margherita Pecorelli
  */
class CharacterImplTest(character: Character) extends FunSuite {

  PlaygroundImpl.dimension = Dimension(35,35)

  test("lives") {
    assert(character.lives.remainingLives equals InitializedInfoImpl.getCharacterLives(character.isInstanceOf[Pacman] match {
      case true => "pacman"
      case false => "ghost"
    }))
  }

  test("position and setPosition") {
    if(character.isInstanceOf[Pacman]) assert(character.position equals InitializedInfoImpl.getPacmanStartPosition)

    character.setPosition(PointImpl(10, 10))
    assert(character.position equals PointImpl(10, 10))

    assertThrows[OutOfPlaygroundBoundAccessException] {
      character.setPosition(PointImpl(35, 35))
    }
  }

  test("isKillable") {
    assert(character.isKillable equals character.isInstanceOf[Pacman])
    character.isKillable = false
    assert(character.isKillable equals false)
    character.isKillable = true
    assert(character.isKillable equals true)
  }

  test("score") {
    assert(character.score equals 0)
    character.score = 5
    assert(character.score equals 5)
  }

  test("isAlive and hasLost") {
    assert(character.isAlive equals true)
    character.isAlive = false
    assert(character.isAlive equals false)

    character.isAlive = true
    character.lives.decrementOf(character.lives.remainingLives)
    assert(character.isAlive equals false)
    assert(character.hasLost equals true)
  }

}
