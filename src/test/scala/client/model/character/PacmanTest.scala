package client.model.character

import client.model._
import client.model.gameElement._
import org.scalatest.FunSuite
import client.model.utils.{BaseEatObjectStrategy, Dimension, LivesImpl, PointImpl}

/**
  * @author Margherita Pecorelli
  */
class PacmanTest extends FunSuite {

  private val myPlayer: Player = PlayerImpl
  private var ip1: String = "10.200.300.400"
  private var ip2: String = "10.200.300.401"
  private var ip3: String = "10.200.300.402"

  val playground = PlaygroundImpl
  playground dimension = Dimension(35,35)

  val gameMatch = MatchImpl

  val pacman = BasePacman("Pacman", BaseEatObjectStrategy())

  gameMatch.addCharactersAndPlayersIp(pacman, myPlayer.ip)

  val characterTest = new CharacterImplTest(pacman)
  characterTest.execute()

  test("name") {
    assert(pacman.name equals "Pacman")
  }

  test("won") {
    pacman.lives = LivesImpl(InitializedInfoImpl.getCharacterLives("pacman"))
    pacman.isAlive = true
    playground.ground = List(Dot("dot", PointImpl(1,1)))
    assert(pacman.won equals false)
    playground.ground = List.empty
    pacman.hasLost = false
    assert(pacman.won equals true)
    pacman.lives = LivesImpl(0)
    assert(pacman.won equals false)
  }

  test("go and direction") {
    playground.ground = List.empty
    var posX = pacman.position.x
    var posY = pacman.position.y

    assert(pacman.direction equals Direction.START)

    pacman.go(Direction.RIGHT)
    assert(pacman.position.x equals posX + 1)
    assert(pacman.direction equals Direction.RIGHT)
    posX = pacman.position.x

    pacman.go(Direction.LEFT)
    assert(pacman.position.x equals posX - 1)
    assert(pacman.direction equals Direction.LEFT)
    posX = pacman.position.x

    pacman.go(Direction.UP)
    assert(pacman.position.y equals posY + 1)
    assert(pacman.direction equals Direction.UP)
    posY = pacman.position.y

    pacman.go(Direction.DOWN)
    assert(pacman.position.y equals posY - 1)
    assert(pacman.direction equals Direction.DOWN)
    posY = pacman.position.y
  }

  test("eatObject") {
    val score = pacman.score
    val cherry = Cherry("Cherry", PointImpl(0, 0))
    playground.ground = List(cherry,
                             Dot("Dot", PointImpl(0, 3)),
                             Grape("Grapes", PointImpl(1, 0)),
                             Key("Key", PointImpl(1, 1)),
                             Pill("Pill", PointImpl(1, 3)))
    pacman.setPosition(PointImpl(1, 0))

    assert(playground.eatables contains cherry)
    assert(!(playground.eatenObjects contains cherry))

    pacman.go(Direction.LEFT)
    assert(pacman.score equals (score + cherry.score))
    assert(!(playground.eatables contains cherry))
    assert(playground.eatenObjects contains cherry)
  }

  test("checkAllPositions") {
    pacman.lives = LivesImpl(InitializedInfoImpl.getCharacterLives("pacman"))

    val blueGhost = BaseGhost("Blue")
    blueGhost.setPosition(PointImpl(0,0))
    val greeenGhost = BaseGhost("Green")
    greeenGhost.setPosition(PointImpl(0,1))
    val yellowGhost = BaseGhost("Yellow")
    yellowGhost.setPosition(PointImpl(0,2))

    gameMatch.addCharactersAndPlayersIp(blueGhost, ip1)
    gameMatch.addCharactersAndPlayersIp(greeenGhost, ip2)
    gameMatch.addCharactersAndPlayersIp(yellowGhost, ip3)

    pacman.setPosition(PointImpl(0,1))
    pacman.isKillable = false
    greeenGhost.isKillable = true
    val lives = pacman.lives.remainingLives
    val score = pacman.score
    pacman.checkAllPositions
    assert(pacman.lives.remainingLives equals lives)
    assert(pacman.score > score)

    pacman.lives.decrementOf(2)
    pacman.setPosition(PointImpl(0,0))
    pacman.isKillable = true
    pacman.checkAllPositions
    assert(pacman.lives.remainingLives equals 0)
    assert(pacman.isAlive equals false)
    assert(pacman.hasLost equals true)
  }

}