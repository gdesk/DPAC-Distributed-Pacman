package client

import java.awt.Color

import character.{GhostImpl, PacmanImpl}
import characterjava.Direction

/**
  * Testing for le working of character class
  * @author Giulia Lucchi
  */
object CharacterTest extends App{

  var ghost = GhostImpl("ghost", Color.BLUE)
  ghost.isKillable = true
  println(ghost.position)
  ghost.go(Direction.RIGHT)
  println(ghost.position)
  ghost.go(Direction.RIGHT)
  println(ghost position)


  ghost.checkAllPositions()
  println(ghost.score)

  /*var pacman = PacmanImpl("pacman")
  println(pacman.position)
  pacman.go(Direction.RIGHT)
  println(pacman.position)*/

}
