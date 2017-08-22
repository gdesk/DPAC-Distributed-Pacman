package client.controller

import java.awt.{Color, Image}
import java.util.{Observable, Observer}
import javafx.util.Pair

import client.model._
import client.model.character.Character
import client.model.utils.{Point, PointImpl}
import client.view.`match`.GamePanel

/**
  * Represents the controller for characters management.
  * Implements Observer since it has to be notify when other characters move or die.
  *
  * @author Margherita Pecorelli
  */
trait ControllerCharacter extends Observer {

  /**
    * Moves the principal user's character in the specified direction.
    *
    * @param direction - the direction of the movement.
    */
  def move(direction: Direction): Unit

  /**
    *Returns, given the name of the character, the map containing images for each direction of the character.
    *
    * @param characterName - the name of the character of which you want the pictures.
    * @return map of images for each direction of the character.
    */
  def imagesOf(characterName: String): Map[Direction, Image]

  /**
    *Returns the map containing images for each direction of all characters.
    *
    * @return map of images for each direction of all characters.
    */
  def allCharactersImages: Map[String, Map[Direction, Image]]

  /**
    *Sets the map containing images for each direction of all characters.
    *
    * @param mapCharactersImages - map of images for each direction of all characters.
    */
  def allCharactersImages_=(mapCharactersImages: Map[String, Map[Direction, Image]]): Unit

  /**
    * Sets the view to be called when somethings changes about characters.
    *
    * @param view - view to be called.
    */
  def setView(view: GamePanel): Unit

  /**
    * Called when other character moves or dies.
    * Invokes the model to perform actions that result from the changes of the character and notifies the view of changes.
    *
    * @param observable - the observable who notified me.
    * @param arg - a flowable with: character user's ip, message about what had changed, a boolean is case of death (true if is dead) or a direction in case of movement.
    *
    * @throws ThisIpDoesNotExistException when the given ip doesn't belong to the current match's ips.
    */
  override def update(observable: Observable, arg: scala.Any)

}

/**
  * Represents the implementation of the controller for characters' management.
  *
  * @author Margherita Pecorelli
  */
object BaseControllerCharacter extends ControllerCharacter {

  private val gameMatch: Match = MatchImpl
  private val playground: Playground = PlaygroundImpl
  private var _view: GamePanel = _

  override var allCharactersImages: Map[String, Map[Direction, Image]] = Map.empty

  /**
    *Returns, given the name of the character, the map containing images for each direction of the character.
    *
    * @param characterName - the name of the character of which you want the pictures.
    * @return map of images for each direction of the character.
    */
  override def imagesOf(characterName: String) = allCharactersImages.getOrElse(characterName, Map.empty)

  /**
    * Moves the computer user's character in the specified direction.
    *
    * @param direction - the direction of the movement.
    */
  override def move(direction: Direction) = {
    val character = gameMatch.myCharacter

    val prePosition: Point[Int, Int] = character.position
    val preLives: Int = character.lives.remainingLives
    val preScore: Int = character.score

    character.go(direction)

    val postPosition: Point[Int, Int] = character.position
    val postLives: Int = character.lives.remainingLives
    val postScore: Int = character.score

    if(!(prePosition equals postPosition)) {
      _view.move(allCharactersImages(character.name)(changeDir(direction)), Color.red,
        prePosition.asInstanceOf[Point[Integer,Integer]],
        postPosition.asInstanceOf[Point[Integer,Integer]])
    }

    if(!(preLives equals postLives)) _view.updateLives(postLives)

    if(character.won) _view.showResult(postScore.toString)

    if(character.hasLost) _view.gameOver()

    if(!(preScore equals postScore)) _view.renderScore(postScore)
  }

  /**
    * Sets the view to be recalled.
    *
    * @param view - view to be recalled.
    */
  override def setView(view: GamePanel) = _view = view

  /**
    * Called when other character moves or dies.
    * Invokes the model to perform actions that result from the changes of the character and notifies the view of changes.
    *
    * @param observable - the observable who notified me.
    * @param arg - a flowable with: character user's ip, message about what had changed, a boolean is case of death (true if is dead) or a direction in case of movement.
    *
    * @throws ThisIpDoesNotExistException when the given ip doesn't belong to the current match's ips.
    */
  override def update(observable: Observable, arg: scala.Any) = {
    val args: Pair[String, Object] = arg.asInstanceOf[Pair[String, Object]]

    val ip = args.getKey
    var characterToUpdate: Character = null
    val playerIp = gameMatch.allPlayersIp.find(i => i equals ip)
    if(playerIp isEmpty) {
      throw ThisIpDoesNotExistException("Ip:" + ip + " doesn't exist!")
    } else {
      characterToUpdate = gameMatch.character(playerIp.get).get
    }

    if(args.getValue.isInstanceOf[Boolean]) {
      characterToUpdate.isAlive = args.getValue.asInstanceOf[Boolean]
      if(!characterToUpdate.isAlive) _view.deleteCharacter(characterToUpdate.position.asInstanceOf[Point[Integer, Integer]])
      if(gameMatch.myCharacter.won) _view.showResult(gameMatch.myCharacter.score.toString)
    }

    if(args.getValue.isInstanceOf[Pair[Point[Int, Int], Direction]]) {
      val direction: Direction = args.getValue.asInstanceOf[Pair[Point[Int, Int], Direction]].getValue
      val pos: Point[Int, Int] = args.getValue.asInstanceOf[Pair[Point[Int, Int], Direction]].getKey

      val viewPrePosition: Point[Int, Int] = characterToUpdate.position
      val prePosition: Point[Int, Int] = calculatePrePosition(direction, pos)
      val preLives: Int = gameMatch.myCharacter.lives.remainingLives
      val preScore: Int = gameMatch.myCharacter.score

      characterToUpdate.setPosition(prePosition)
      characterToUpdate.go(direction)

      val postPosition: Point[Int, Int] = characterToUpdate.position
      val postLives: Int = gameMatch.myCharacter.lives.remainingLives
      val postScore: Int = gameMatch.myCharacter.score

      if(!(prePosition equals postPosition)) {
        _view.move(allCharactersImages(characterToUpdate.name)(changeDir(direction)), Color.red,
          viewPrePosition.asInstanceOf[Point[Integer,Integer]],
          postPosition.asInstanceOf[Point[Integer,Integer]])
      }

      if(!(preLives equals postLives)) _view.updateLives(postLives)

      if(gameMatch.myCharacter.won) _view.showResult(postScore.toString)

      if(gameMatch.myCharacter.hasLost) _view.gameOver()

      if(!(preScore equals postScore)) _view.renderScore(postScore)
    }
  }

  /**
    * Converts up's directiorn with down's direction.
    * It is necessary because we use a matrix, so the (0,0) is top left.
    *
    * @param direction - the direction to convert, if necessary.
    * @return the right direction.
    */
  private def changeDir(direction: Direction): Direction =  direction match {
    case Direction.UP => Direction.DOWN
    case Direction.DOWN =>Direction.UP
    case _ => direction
  }

  /**
    * Computes the player's previous player's position.
    * It is necessary because some positions are skipped, so before going in the selected direction, we have to calculate the rigth previos position.
    *
    * @param direction - the selected direction.
    * @param pos - the new position.
    * @return the previous character's position
    */
  private def calculatePrePosition(direction: Direction, pos: Point[Int, Int]): Point[Int, Int] = direction match {
    case Direction.RIGHT => PointImpl(pos.x-1, pos.y)
    case Direction.LEFT => PointImpl(pos.x+1, pos.y)
    case Direction.UP => PointImpl(pos.x, pos.y-1)
    case Direction.DOWN => PointImpl(pos.x, pos.y+1)
  }
}

/**
  * Represents the exception throws when the given ip doesn't belong to the current match's ips.
  *
  * @param message - message throws by the exception.
  */
case class ThisIpDoesNotExistException(message: String = "") extends Exception(message)