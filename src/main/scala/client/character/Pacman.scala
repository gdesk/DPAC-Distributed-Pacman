package client.character
import java.io.FileInputStream

import alice.tuprolog.Theory
import character.Direction
import client.utils.ScalaProlog

/**
  * Created by Giulia Lucchi on 28/06/2017.
  */
trait Pacman extends CharacterImpl {
 def eatObject(): Unit
}

object Pacman{
  //private val FILE_NAME = "src/main/prolog/dpac-prolog.pl" QUELLA PER PROGETTO
  //per provare
  private val FILE_NAME = "dpac-prolog.pl"
  private val ENGINE = ScalaProlog.mkPrologEngine(new Theory(new FileInputStream(FILE_NAME)))
}

//trait Pacman[X, Y] extends Character[X, Y]{
//def eatItem(eatable: Eatable) GUARDA PROLOG probabilmente non serve perchè verrà richiamato il metodo prolog direttamente da go
//si potrebbe fare privato e richiamare prolog . tutto richimto su go del quale facciamo un override
//}
case class PacmanImpl(override val name: String) extends CharacterImpl(true, new LivesImpl(1)) with Pacman{
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

