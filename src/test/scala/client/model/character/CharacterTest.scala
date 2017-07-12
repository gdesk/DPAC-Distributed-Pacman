package client.model.character

import client.model.Direction

/**
  * Testing for le working of client.model.character.gameElement.character class
  *
  * @author Giulia Lucchi
  */
object CharacterTest extends App{

  var ghost0= BaseGhost("pink")
  ghost0.isKillable = false
  ghost0.go(Direction.RIGHT)
  ghost0.go(Direction.RIGHT)

  var ghost1 = BaseGhost("pink")
  ghost1.isKillable = true
  ghost1.go(Direction.RIGHT)
  ghost1.go(Direction.RIGHT)

}
