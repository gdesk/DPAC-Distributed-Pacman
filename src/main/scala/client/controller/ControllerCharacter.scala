package client.controller

import java.util.{Observable, Observer}

import client.model._
import client.model.character.Pacman
import client.model.character.Character
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

case class BaseControllerCharacter(/*private val view = ???*/) extends ControllerCharacter with Observer{

  private val gameMatch: Match = MatchImpl instance()
  private val playeground: Playground = PlaygroundImpl instance()

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

  override def update(o: Observable, arg: scala.Any) = {
    val pair: (String, Character) = if(arg.isInstanceOf[(String, client.model.character.Character)]) {arg.asInstanceOf[(String, Character)]} else {null}
    if(pair != null) {
      pair._1 match {
        case "remainingLives" => view updateLives pair._2
        case "isDead" => view deleteCharacter pair._2
        case "score" => view updateScore pair._2.score
        case "move" => view move pair._2
      }
    }
  }

}