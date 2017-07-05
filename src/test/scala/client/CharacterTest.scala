package client

import java.awt.Color

import character.{GhostImpl, PacmanImpl}
import characterjava.Direction

/**
  * Testing for le working of character class
  *
  * @author Giulia Lucchi
  */
object CharacterTest extends App{

  var ghost = GhostImpl("ghost", "pink")
  ghost.isKillable = false
 //  println(ghost.position)
  ghost.go(Direction.RIGHT)
 // println(ghost.position)
  ghost.go(Direction.RIGHT)
 // println(ghost position)



  /*var pacman = PacmanImpl("pacman")
  println(pacman.position)
  pacman.go(Direction.RIGHT)
  println(pacman.position)*/

}
