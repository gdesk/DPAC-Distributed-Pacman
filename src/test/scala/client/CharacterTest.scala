package client

import java.awt.Color

import client.model.character.{GhostImpl, PacmanImpl}
import characterjava.Direction
import client.model.utils.ScalaProlog

/**
  * Testing for le working of client.model.character.gameElement.character class
  *
  * @author Giulia Lucchi
  */
object CharacterTest extends App{

  var ghost0= GhostImpl("pink")
  ghost0.isKillable = false
  ghost0.go(Direction.RIGHT)
  ghost0.go(Direction.RIGHT)

  var ghost1 = GhostImpl("pink")
  ghost1.isKillable = true
  ghost1.go(Direction.RIGHT)
  ghost1.go(Direction.RIGHT)

}
