package client.controller

import java.util.{Observable, Observer}

import client.model._
import client.model.character.Character
import client.model.utils.Point
import client.view.{GamePanel, MainFrame}

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

  def view: View

  def view_=(view: View): Unit

}

case class BaseControllerCharacter(private val view: GamePanel) extends ControllerCharacter with Observer{


  private val gameMatch: Match = MatchImpl instance()
  private val playeground: Playground = PlaygroundImpl instance()

  override var view: ??? = null

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
    if(!(prePosition equals postPosition)) view move character
  }

  override def update(o: Observable, arg: scala.Any) = {
    val tris: (String, String, _) = if(arg.isInstanceOf[(String, String, _)]) {arg.asInstanceOf[(String, String, _)]} else {null}
    if(tris != null) {
      var characterToUpdate: Character = null
      val player = gameMatch.allPlayersIp.find(ip => ip equals tris._1)
      if(player isEmpty) {
        throw new ThisIpDoesNotExist("Ip:" + tris._1 + " doen't exist!")
      } else {
        characterToUpdate = gameMatch.character(player.get).get
      }
      tris._2 match {
        case "remainingLives" =>
          characterToUpdate.lives remainingLives = tris._3.asInstanceOf[Int]
          view updateLives characterToUpdate
        case "isDead" =>
          characterToUpdate isAlive = tris._3.asInstanceOf[Boolean]
          view deleteCharacter characterToUpdate
        case "score" =>
          characterToUpdate score = tris._3.asInstanceOf[Int]
          view updateScore characterToUpdate //quali score vogliamo visualizzare?????????????????????????????????????????????????????????
        case "move" =>
          characterToUpdate setPosition tris._3.asInstanceOf[Point[Int, Int]]
          view move characterToUpdate
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
