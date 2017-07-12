package client.model

import client.model.character.{BaseEatObjectStrategy, BaseGhost, BasePacman, Character}
import org.scalatest.FunSuite

import scala.collection.mutable.HashMap
import scala.collection.mutable.Map


/**
  * Created by margherita on 05/07/17.
  */
class MatchTest extends FunSuite {

  var usersAndCharacters: Map[Character[Int,Int], String] = HashMap((BasePacman("Pacman", BaseEatObjectStrategy()), "Marghe"),
                                                                    (BaseGhost("Red"), "Chiara"),
                                                                    (BaseGhost("Blue"), "Giuls"),
                                                                    (BaseGhost("Green"), "Manu"),
                                                                    (BaseGhost("Yellow"), "Fede"))

  val playground: PlaygroundImpl = PlaygroundImpl instance()
  val gameMatch: Match = MatchImpl instance()
  gameMatch playground = playground
  gameMatch addPlayers usersAndCharacters
  val characters: List[Character[Int, Int]] = gameMatch characters

  test("Match's getter of characters' list") {
    assert(characters.size.equals(5))
    assert(characters.contains(BasePacman("Pacman", BaseEatObjectStrategy())))
    assert(characters.contains(BaseGhost("Red")))
    assert(characters.contains(BaseGhost("Blue")))
    assert(characters.contains(BaseGhost("Green")))
    assert(characters.contains(BaseGhost("Yellow")))
  }
/*
  singleton
  playground
  myCharacter
  addPlayers
  characters
  deadCharacters
  addDeadCharacters
*/
}
