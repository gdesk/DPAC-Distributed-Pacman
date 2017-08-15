package client.model

import client.model.character.{BaseGhost, BasePacman}
import client.model.character.Character
import client.model.gameElement._
import client.model.utils.{BaseEatObjectStrategy, Dimension, PointImpl}
import org.scalatest.FunSuite

import scala.collection.mutable.HashMap
import scala.collection.mutable.Map


/**
  * @author Margherita Pecorelli
  */
class MatchTest extends FunSuite {

  private val myPlayer: Player = PlayerImpl.instance()
  private var ip1: String = "10.200.300.400"
  private var ip2: String = "10.200.300.401"
  private var ip3: String = "10.200.300.402"

  val gameMatch: Match = MatchImpl

  val playground: PlaygroundImpl = PlaygroundImpl instance()
  playground dimension = Dimension(35,35)

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

  test("addCharactersAndPlayersIp and charactersAndPlayersIp") {
    gameMatch.addCharactersAndPlayersIp(pacman, myPlayer.ip)
    gameMatch.addCharactersAndPlayersIp(blueGhost, ip1)
    gameMatch.addCharactersAndPlayersIp(greeenGhost, ip2)
    gameMatch.addCharactersAndPlayersIp(yellowGhost, ip3)

    assert(gameMatch.charactersAndPlayersIp.size == 4)
    assert(gameMatch.charactersAndPlayersIp exists (m => ((m._1 equals pacman) && (m._2 equals myPlayer.ip))))
    assert(gameMatch.charactersAndPlayersIp exists (m => ((m._1 equals blueGhost) && (m._2 equals ip1))))
    assert(gameMatch.charactersAndPlayersIp exists (m => ((m._1 equals greeenGhost) && (m._2 equals ip2))))
    assert(gameMatch.charactersAndPlayersIp exists (m => ((m._1 equals yellowGhost) && (m._2 equals ip3))))
  }

  test("allCharacters and allPlayersIp") {
    assert(gameMatch.allCharacters.size == 4)
    assert(gameMatch.allCharacters contains pacman)
    assert(gameMatch.allCharacters contains blueGhost)
    assert(gameMatch.allCharacters contains greeenGhost)
    assert(gameMatch.allCharacters contains yellowGhost)

    assert(gameMatch.allPlayersIp.size == 4)
    assert(gameMatch.allPlayersIp contains myPlayer.ip)
    assert(gameMatch.allPlayersIp contains ip1)
    assert(gameMatch.allPlayersIp contains ip2)
    assert(gameMatch.allPlayersIp contains ip3)
  }

  test("myCharacter") {
    assert(gameMatch.myCharacter == pacman)
  }

  test("character and playerIp") {
    assert(gameMatch.character(myPlayer.ip).get equals pacman)
    assert(gameMatch.character(ip1).get equals blueGhost)
    assert(gameMatch.character(ip2).get equals greeenGhost)
    assert(gameMatch.character(ip3).get equals yellowGhost)

    assert(gameMatch.playerIp(pacman).get equals myPlayer.ip)
    assert(gameMatch.playerIp(blueGhost).get equals ip1)
    assert(gameMatch.playerIp(greeenGhost).get equals ip2)
    assert(gameMatch.playerIp(yellowGhost).get equals ip3)
  }

  test("deadCharacters and addDeadCharacters") {
    assert(gameMatch.deadCharacters isEmpty)

    gameMatch.addDeadCharacters(pacman)
    assert(gameMatch.deadCharacters contains pacman)
    assert(!(gameMatch.charactersAndPlayersIp contains pacman))

    gameMatch.addDeadCharacters(blueGhost)
    assert(gameMatch.deadCharacters contains blueGhost)
    assert(!(gameMatch.charactersAndPlayersIp contains blueGhost))

    gameMatch.addDeadCharacters(greeenGhost)
    assert(gameMatch.deadCharacters contains greeenGhost)
    assert(!(gameMatch.charactersAndPlayersIp contains greeenGhost))

    gameMatch.addDeadCharacters(yellowGhost)
    assert(gameMatch.deadCharacters contains yellowGhost)
    assert(!(gameMatch.charactersAndPlayersIp contains yellowGhost))

    val blackGhost = BaseGhost("Black")

    assertThrows[CharacterDoesNotExistException] {
      gameMatch.addDeadCharacters(blackGhost)
    }
  }

  test ("singleton"){
    assert(gameMatch == MatchImpl)
  }

}
