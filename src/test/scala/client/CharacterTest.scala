package client

import character.{GhostImpl, PacmanImpl}
import characterjava.Direction

/**
  * @author Giulia Lucchi
  */
object CharacterTest extends App{

  var ghost = GhostImpl("ghost")
  println(ghost.position)
  ghost.go(Direction.RIGHT)
  println(ghost.position)
  ghost.go(Direction.RIGHT)
  println(ghost position)

  var pacman = PacmanImpl("pacman")
  println(pacman.position)
  pacman.go(Direction.RIGHT)
  println(pacman.position)

}
