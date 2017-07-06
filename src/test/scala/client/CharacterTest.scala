package client

import java.awt.Color

import character.{GhostImpl, PacmanImpl}
import characterjava.Direction
import client.utils.ScalaProlog

/**
  * Testing for le working of character class
  *
  * @author Giulia Lucchi
  */
object CharacterTest extends App{

  var ghost0= GhostImpl("ghost", "pink")
  ghost0.isKillable = false
  ghost0.go(Direction.RIGHT)
  ghost0.go(Direction.RIGHT)

  var ghost1 = GhostImpl("ghost", "pink")
  ghost1.isKillable = true
  ghost1.go(Direction.RIGHT)
  ghost1.go(Direction.RIGHT)

}
