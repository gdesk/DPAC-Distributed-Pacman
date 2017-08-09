package client.model.utils

import client.model.{Match, MatchImpl, PlaygroundImpl}
import client.model.character.{BaseGhost, BasePacman, Character}
import client.model.gameElement.{Apple, Pill}
import org.scalatest.FunSuite

import scala.collection.mutable.{HashMap, Map}

/**
  * Created by margherita on 18/07/17.
  */
class BaseEatObjectStrategyTest extends FunSuite {

  private var ip1: String = "10.200.300.400"
  private var ip2: String = "10.200.300.401"
  private var ip3: String = "10.200.300.402"

  val strategy = BaseEatObjectStrategy()

  val gameMatch: Match = MatchImpl instance()

  val playground: PlaygroundImpl = PlaygroundImpl instance()
  playground dimension = Dimension(35,35)

  val redGhost = BaseGhost("Red")
  val pacman = BasePacman("Pacman", strategy)
  val blueGhost = BaseGhost("Blue")

  gameMatch.addCharactersAndPlayersIp(redGhost, ip1)
  gameMatch.addCharactersAndPlayersIp(blueGhost, ip2)
  gameMatch.addCharactersAndPlayersIp(pacman, ip3)

  test("eat") {
    assert(pacman.isKillable && !(redGhost.isKillable) && !(blueGhost.isKillable))
    strategy.eat(Apple("apple", PointImpl(0,0)))
    assert(pacman.isKillable && !(redGhost.isKillable) && !(blueGhost.isKillable))
    strategy.eat(Pill("pill", PointImpl(0,1)))
    assert(!(pacman.isKillable) && redGhost.isKillable && blueGhost.isKillable)
    val time = System.currentTimeMillis()
    while(System.currentTimeMillis() <= time + BaseEatObjectStrategy.getMillisecondsToWait) {}
    assert(pacman.isKillable && !(redGhost.isKillable) && !(blueGhost.isKillable))
  }

}
