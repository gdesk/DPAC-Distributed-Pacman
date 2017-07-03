package client.character
import java.io.FileInputStream

import alice.tuprolog.Theory
import characterjava.Direction
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
  setPosition(InitializedInfoImpl.getStartPosition())
  /**
    * Manages the character's strategy of mortality.
    */
  override def eatObject(): Unit = ??? // farla solo se si è msso

  /**
    * Manage the the strategy of game, that is based on who the killer is and who the killable
    */
  override def checkAllPositions(): Unit = ???

  /**
    * Manages the character's movement and consequently the contact with other item of the game.
    *
    * @param direction    character's of direction
    */
  override def go(direction: Direction): Unit = {
    super.go(direction)
    //richiare eat_objext che richiama prolog SOLO SE  si è effettivamente mosso
  }
}

