package client

import character.{GhostImpl, PacmanImpl}
import characterjava.Direction

/**
  * @author Giulia Lucchi
  */
object CharacterTest extends App{

  var ghost = new GhostImpl("ghost")
  println(ghost.position.toString)
  ghost.go(Direction.RIGHT)
  println(ghost.position.toString)

  var pacman = new PacmanImpl("pacman")
  println(pacman.position.toString)
  pacman.go(Direction.RIGHT)
  println(pacman.position.toString)

}
