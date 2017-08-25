package client.model.character

import client.model._
import client.model.utils.{BaseEatObjectStrategy, Dimension, LivesImpl, PointImpl}
import org.scalatest.FunSuite

/**
  * @author Margherita Pecorelli
  */
class GhostTest extends FunSuite {

  private val myPlayer: Player = PlayerImpl
  private var ip1: String = "10.200.300.400"
  private var ip2: String = "10.200.300.401"
  private var ip3: String = "10.200.300.402"
  private var ip4: String = "10.200.300.403"

  val playground = PlaygroundImpl
  playground dimension = Dimension(35,35)

  val gameMatch = MatchImpl

  val redGhost = BaseGhost("Red")
  val pacman = BasePacman("Pacman", BaseEatObjectStrategy())

  gameMatch.addCharactersAndPlayersIp(redGhost, myPlayer.ip)

  val characterTest = new CharacterImplTest(redGhost)
  characterTest.execute()

  test("name") {
    assert(redGhost.name equals "Red")
  }

  test("go and direction") {
    playground.ground = List.empty
    var posX = redGhost.position.x
    var posY = redGhost.position.y

    assert(redGhost.direction equals Direction.START)

    redGhost.go(Direction.RIGHT)
    assert(redGhost.position.x equals posX + 1)
    assert(redGhost.direction equals Direction.RIGHT)
    posX = redGhost.position.x

    redGhost.go(Direction.LEFT)
    assert(redGhost.position.x equals posX - 1)
    assert(redGhost.direction equals Direction.LEFT)
    posX = redGhost.position.x

    redGhost.go(Direction.UP)
    assert(redGhost.position.y equals posY + 1)
    assert(redGhost.direction equals Direction.UP)
    posY = redGhost.position.y

    redGhost.go(Direction.DOWN)
    assert(redGhost.position.y equals posY - 1)
    assert(redGhost.direction equals Direction.DOWN)
    posY = redGhost.position.y
  }

  test("checkAllPositions and won") {
    pacman.setPosition(PointImpl(0,0))
    val blueGhost = BaseGhost("Blue")
    blueGhost.setPosition(PointImpl(0,3))
    val greeenGhost = BaseGhost("Green")
    greeenGhost.setPosition(PointImpl(0,1))
    val yellowGhost = BaseGhost("Yellow")
    yellowGhost.setPosition(PointImpl(0,2))

    gameMatch.addCharactersAndPlayersIp(blueGhost, ip1)
    gameMatch.addCharactersAndPlayersIp(greeenGhost, ip2)
    gameMatch.addCharactersAndPlayersIp(yellowGhost, ip3)
    gameMatch.addCharactersAndPlayersIp(pacman, ip4)

    blueGhost.setPosition(PointImpl(0,0))
    blueGhost.isKillable = true
    pacman.isKillable = false
    pacman.lives = LivesImpl(1)
    blueGhost.lives = LivesImpl(InitializedInfoImpl.getCharacterLives("ghost"))
    val lives = blueGhost.lives.remainingLives
    val score = blueGhost.score

    blueGhost.checkAllPositions
    assert(blueGhost.lives.remainingLives equals 0)
    assert(blueGhost.won equals false)
    assert(blueGhost.isAlive equals false)
    assert(blueGhost.hasLost equals true)

    yellowGhost.isKillable = false
    pacman.isKillable = true
    pacman.setPosition(PointImpl(0,2))
    pacman.lives = LivesImpl(1)

    assert(yellowGhost.won equals false)
    yellowGhost.checkAllPositions
    assert(yellowGhost.lives.remainingLives equals lives)
    assert(yellowGhost.won equals true)
    assert(yellowGhost.score > score)
  }

}