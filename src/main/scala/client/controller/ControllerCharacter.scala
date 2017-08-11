package client.controller

import java.awt.Image
import java.util.{Observable, Observer}

import client.model._
import client.model.character.Character
import client.model.utils.Point
import client.view.GamePanel

/**
  * Represents the controller for characters' management.
  *
  * @author Margherita Pecorelli
  */
trait ControllerCharacter {

  /**
    * Moves the computer user's character in the specified direction.
    *
    * @param direction - the direction of the movement.
    */
  def move(direction: Direction): Unit

  /**
    *Sets the map containing images for each direction of all characters.
    *
    * @param mapCharacterImages - map of images for each direction of all characters.
    */
  def setCharacterImages(mapCharacterImages: Map[String, Map[Direction, Image]]): Unit

  /**
    * Sets the view to be recalled.
    *
    * @param view - view to be recalled.
    */
  def view(view: GamePanel): Unit

}

/**
  * Represents the implementation of the controller for characters' management.
  *
  *
  * @author Margherita Pecorelli
  */
case class BaseControllerCharacter private() extends ControllerCharacter with Observer {

  private val gameMatch: Match = MatchImpl.instance()
  private val playground: Playground = PlaygroundImpl.instance()
  private var _view: GamePanel = null
  private var characterImages: Map[String, Map[Direction, Image]] = Map.empty

  /**
    * Moves the computer user's character in the specified direction.
    *
    * @param direction - the direction of the movement.
    */
  override def move(direction: Direction) = {
    val character = gameMatch.myCharacter;

    val prePosition: Point[Int, Int] = character.position
    val preLives: Int = character.lives.remainingLives
    val preScore: Int = character.score

    character go direction

    val postPosition: Point[Int, Int] = character.position
    val postLives: Int = character.lives.remainingLives
    val postScore: Int = character.score

    if(!(prePosition equals postPosition)) _view.move(characterImages.get(character.name).get(direction),
      prePosition.asInstanceOf[Point[Integer,Integer]],
      postPosition.asInstanceOf[Point[Integer,Integer]])

    if(!(preLives equals postLives)) {
      _view.updateLives(postLives)
      if(postLives <= 0) _view.gameOver()
    }

    if(!(preScore equals postScore)) _view.updateLives(postScore)
  }

  /**
    *Sets the map containing images for each direction of all characters.
    *
    * @param mapCharacterImages - map of images for each direction of all characters.
    */
  override def setCharacterImages(mapCharacterImages: Map[String, Map[Direction, Image]]) = characterImages = mapCharacterImages

  /**
    * Sets the view to be recalled.
    *
    * @param view - view to be recalled.
    */
  override def view(view: GamePanel): Unit = this._view = view

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
          if(!characterToUpdate.isAlive) _view.deleteCharacter(characterToUpdate.position.asInstanceOf[Point[Integer,Integer]])
        case "direction" =>
          val direction = tris._3.asInstanceOf[Direction]
          val prePosition: Point[Int, Int] = characterToUpdate.position
          val preLives: Int = gameMatch.myCharacter.lives.remainingLives
          val preScore: Int = gameMatch.myCharacter.score
          characterToUpdate.go(direction)
          val postPosition: Point[Int, Int] = characterToUpdate.position
          val postLives: Int = gameMatch.myCharacter.lives.remainingLives
          val postScore: Int = gameMatch.myCharacter.score

          if(!(prePosition equals postPosition)) _view.move(characterImages.get(characterToUpdate.name).get(direction),
                                                            prePosition.asInstanceOf[Point[Integer,Integer]],
                                                            postPosition.asInstanceOf[Point[Integer,Integer]])

          if(!(preLives equals postLives)) {
            _view.updateLives(postLives)
            if(postLives <= 0) _view.gameOver()
          }

          if(!(preScore equals postScore)) _view.updateLives(postScore)
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
