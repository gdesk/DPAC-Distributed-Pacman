package client.controller

import client.model.Direction
import client.model.character.Pacman
import client.model.gameElement.Eatable
import client.model.utils.Point

/**
  * Created by margherita on 25/07/17.
  */
trait ControllerCharacter {

  /**
    * Method called when the user moves his character. This method calls the method in the model.
    *
    * //@param character - the character which is moving.
    * @param direction - the direction of the movement.
    * @return the new character's position
    */
  def move(direction: Direction): Point[Int, Int]

}

case class BaseControllerCharacter extends ControllerCharacter {

  /**
    * Method called when the user moves his character. This method calls the method in the model.
    *
    * //@param character - the character which is moving.
    *
    * @param direction - the direction of the movement.
    * @return the new character's position
    */
  override def move(direction: Direction): Point[Int, Int] = {
    val character = gameMatch myCharacter;
    character.isInstanceOf[Pacman] match {
      case true =>
        val preEatenObj: List[Eatable] = playeground eatenObjects;
        character go direction
        val postEatenObj: List[Eatable] = playeground eatenObjects
        val eatenObjet = postEatenObj diff preEatenObj
        if(!(eatenObjet isEmpty)) {
          view eatenObject (eatenObjet head)
          view score (character score)
        }
      case false =>
        character go direction
    }
    character position
  }
}
