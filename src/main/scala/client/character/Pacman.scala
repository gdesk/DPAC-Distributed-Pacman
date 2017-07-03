package client.character
import java.io.FileInputStream

import alice.tuprolog.Theory
import character.Direction
import client.utils.{Point, ScalaProlog}

/**
  * Manages the Pacman character.
  *
  * @author Giulia Lucchi
  */
trait Pacman extends CharacterImpl {
  /**
    * Manages the character's strategy of mortality.
    */
 def eatObject(): Unit
}

case class PacmanImpl(override val name: String) extends CharacterImpl(true, new LivesImpl(InitializedInfoImpl.getCharacterLives("pacman"))) with Pacman{
  setPosition(Point[Int,Int](0,0))// da prolog
  /**
    * Manages the character's strategy of mortality.
    */
  override def eatObject(): Unit = ??? // farla solo se si è msso

  /**
    * Manage the the strategy of game, that is based on who the killer is and who the killable
    */
  override def checkAllPositions(): Unit = ???

  override def go(direction: Direction): Unit = {
    super.go(direction)
    //richiare eat_objext che richiama prolog SOLO SE  si è effettivamente mosso
  }
}

