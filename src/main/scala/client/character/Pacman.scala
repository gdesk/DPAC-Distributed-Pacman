package client.character
import java.io.FileInputStream

import alice.tuprolog.Theory
import character.Direction
import client.utils.ScalaProlog

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

object Pacman{
  //private val FILE_NAME = "src/main/prolog/dpac-prolog.pl" QUELLA PER PROGETTO
  //per provare
  private val FILE_NAME = "dpac-prolog.pl"
  private val ENGINE = ScalaProlog.mkPrologEngine(new Theory(new FileInputStream(FILE_NAME)))
}


case class PacmanImpl(override val name: String) extends CharacterImpl(true, new LivesImpl(InitializedInfoImpl.getCharacterLives("pacman"))) with Pacman{
  /**
    * Manages the character's strategy of mortality.
    */
  override def eatObject(): Unit = ???

  /**
    * Manages the character's movement and consequently the contact with other item of the game.
    *
    * @param direction character's of direction
    */
  override def go(direction: Direction): Unit = ???

  /**
    * Manage the the strategy of game, that is based on who the killer is and who the killable
    */
  override def death(): Unit = ???
}

