package client.character
import character.Direction

/**
  * Created by Giulia Lucchi on 28/06/2017.
  */
trait Ghost{
  def eatPacman(): Unit
  def ghostDefended(): Unit

}

case class GhostImpl(override val name: String) extends CharacterImpl(true, new LivesImpl(1)) with Ghost{
  override def eatPacman(): Unit = ???

  override def ghostDefended(): Unit = ???

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

