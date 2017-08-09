package client.controller3

import java.util.{Observable, Observer}

import client.model._
import client.model.character.Character
import client.model.utils.Point
import client.view.MainFrame
import client.view.{GamePanel, GamePanelImpl}

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
  def move(direction: Direction): Unit

 // def view: View

  //def view_=(view: View): Unit

}

case class BaseControllerCharacter(private val view: GamePanel) extends ControllerCharacter with Observer{


  private val gameMatch: Match = MatchImpl instance()
  private val playeground: Playground = PlaygroundImpl instance()


  //override var view: View = null

  /**
    * Method called when the user moves his character. This method calls the method in the model.
    *
    * //@param character - the character which is moving.
    *
    * @param direction - the direction of the movement.
    * @return the new character's position
    */
  override def move(direction: Direction) = {
    val character = gameMatch myCharacter;
    /*
    character.isInstanceOf[Pacman] match {
      case true =>
        val preEatenObj: List[Eatable] = playeground eatenObjects;
        character go direction
        val postEatenObj: List[Eatable] = playeground eatenObjects
        val eatenObjet = postEatenObj diff preEatenObj
        if(!(eatenObjet isEmpty)) {
          //view eatenObject (eatenObjet head)
          //view score (character score)
        }
      case false =>
        character go direction
    }
    */
    val prePosition: Point[Int, Int] = character position;
    character go direction
    val postPosition: Point[Int, Int] = character position;
    //if(!(prePosition equals postPosition)) view move character
  }

  override def update(o: Observable, arg: scala.Any) = {
    val pair: (String, Character) = if(arg.isInstanceOf[(String, client.model.character.Character)]) {arg.asInstanceOf[(String, Character)]} else {null}
    if(pair != null) {
      pair._1 match {
        case "remainingLives" => view updateLives pair._2
        case "isDead" => view deleteCharacter pair._2
        case "score" => view updateScore pair._2.score
        //case "move" => view move pair._2
      }
    }
  }

}

object BaseControllerCharacter {

  private var _instance: BaseControllerCharacter = null

  def instance(): BaseControllerCharacter = {
    if(_instance == null) _instance = BaseControllerCharacter(MainFrame.getInstance().getContentPane.asInstanceOf[GamePanel])
    _instance
  }

}

case class ThisIpDoesNotExist(private val message: String = "") extends Exception(message)
