package client.controller

import java.awt.Image
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

  def setCharacterImages(mapCharacterImages: Map[String, Map[Direction, Image]]): Unit

  def view(view: GamePanel): Unit

}

case class BaseControllerCharacter private() extends ControllerCharacter with Observer{

  private val gameMatch: Match = MatchImpl instance()
  private val playeground: Playground = PlaygroundImpl instance()
  private var view: GamePanel = null
  private var characterImages: Map[String, Map[Direction, Image]] = Map.empty

  override def view(view: GamePanel): Unit = this.view = view

  def setCharacterImages(mapCharacterImages: Map[String, Map[Direction, Image]]) = characterImages = mapCharacterImages

  /**
    * Method called when the user moves his character. This method calls the method in the model.
    *
    * //@param character - the character which is moving.
    *
    * @param direction - the direction of the movement.
    * @return the new character's position
    */
  override def move(direction: Direction) = {
    val character = gameMatch.myCharacter;
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
    val prePosition: Point[Int, Int] = character.position
    val preLives: Int = character.lives.remainingLives
    val preScore: Int = character.score
    character go direction
    val postPosition: Point[Int, Int] = character.position
    val postLives: Int = character.lives.remainingLives
    val postScore: Int = character.score

    if(!(prePosition equals postPosition)) view.move(characterImages.get(character.name).get(direction),
                                                                          prePosition.asInstanceOf[Point[Integer,Integer]],
                                                                          postPosition.asInstanceOf[Point[Integer,Integer]])

    if(!(preLives equals postLives)) {
      view.updateLives(postLives)
      if(postLives <= 0) view.gameOver()
    }

    if(!(preScore equals postScore)) view.updateLives(postScore)
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
        case "isAlive" =>
          characterToUpdate.isAlive = tris._3.asInstanceOf[Boolean]
          if(!characterToUpdate.isAlive) view.deleteCharacter(characterToUpdate.position.asInstanceOf[Point[Integer,Integer]])
        case "direction" =>
          val direction = tris._3.asInstanceOf[Direction]
          val prePosition: Point[Int, Int] = characterToUpdate.position
          val preLives: Int = gameMatch.myCharacter.lives.remainingLives
          val preScore: Int = gameMatch.myCharacter.score
          characterToUpdate.go(direction)
          val postPosition: Point[Int, Int] = characterToUpdate.position
          val postLives: Int = gameMatch.myCharacter.lives.remainingLives
          val postScore: Int = gameMatch.myCharacter.score

          if(!(prePosition equals postPosition)) view.move(characterImages.get(characterToUpdate.name).get(direction),
                                                            prePosition.asInstanceOf[Point[Integer,Integer]],
                                                            postPosition.asInstanceOf[Point[Integer,Integer]])

          if(!(preLives equals postLives)) {
            view.updateLives(postLives)
            if(postLives <= 0) view.gameOver()
          }

          if(!(preScore equals postScore)) view.updateLives(postScore)
      }
    }
  }

}

object BaseControllerCharacter {

  private var _instance: BaseControllerCharacter = null

  def instance(): BaseControllerCharacter = {
    if(_instance == null) _instance = BaseControllerCharacter()
    _instance
  }

}

case class ThisIpDoesNotExist(private val message: String = "") extends Exception(message)
