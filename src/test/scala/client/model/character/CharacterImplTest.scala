package client.model.character

import client.model.utils.{Dimension, PointImpl}
import client.model.{Direction, MatchImpl, OutOfPlaygroundBoundAccessException, PlaygroundImpl}
import org.scalatest.FunSuite

/**
  * Testing for le working of client.model.character.gameElement.character class
  *
  * @author Giulia Lucchi
  * @author Margherita Pecorelli
  */
class CharacterImplTest(character: Character[Int, Int]) extends FunSuite {

    test("lives") {
      assert(character.lives.remainingLives equals (InitializedInfoImpl.getCharacterLives(character.isInstanceOf[Pacman] match {
        case true => "pacman"
        case false => "ghost"
      })))
    }

    test("position and setPosition") {
      /*
      assert(character.position equals (InitializedInfoImpl.getStartPosition(character.isInstanceOf[Pacman] match {
        case true => "pacman"
        case false => "ghost"
      })))
      */
      //DA TOGLIERE QUANDO SCOMMENTIAMO LA PARTE SOPRA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      assert(character.position equals (character.isInstanceOf[Pacman] match {
        case true => InitializedInfoImpl.getStartPosition("pacman")
        case false => PointImpl(20, 20)
      }))

      character setPosition PointImpl(10, 10)
      assert(character.position equals PointImpl(10, 10))

      assertThrows[OutOfPlaygroundBoundAccessException] {
        character setPosition PointImpl(35, 35)
      }
    }

    test("direction") {
      assert(character.direction equals Direction.START)
      character direction = Direction.DOWN
      assert(character.direction equals Direction.DOWN)
    }

    test("isAlive") {
      assert(character.isAlive equals true)
      character isAlive = false
      assert(character.isAlive equals false)
    }

    test("isKillable") {
      assert(character.isKillable equals (character.isInstanceOf[Pacman] match {
        case true => true
        case false => false
      }))
      character isKillable = false
      assert(character.isKillable equals false)
      character isKillable = true
      assert(character.isKillable equals true)
    }

    test("score") {
      assert(character.score equals 0)
      character score = 5
      assert(character.score equals 5)
    }

    /*
    CONTROLLA ANCHE UP E DOWN!!!!
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
}
