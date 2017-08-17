package client.controller

import java.awt.{Color, Image}
import java.util.{Observable, Observer}

import client.model._
import client.model.character.Character
import client.model.utils.Point
import client.view.`match`.GamePanel
import io.reactivex.Flowable
import network.client.P2P.game.PeerRegisterHandler

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
  def getCharacterImages(characterName: String): Map[Direction, Image]

  /**
    *Returns the map containing images for each direction of all characters.
    *
    * @return map of images for each direction of all characters.
    */
  def characterImages: Map[String, Map[Direction, Image]]
  /**
    *Sets the map containing images for each direction of all characters.
    *
    * @param mapCharacterImages - map of images for each direction of all characters.
    */
  def characterImages_=(mapCharacterImages: Map[String, Map[Direction, Image]]): Unit

  /**
    * Sets the view to be called when somethings changes about characters.
    *
    * @param view - view to be called.
    */
  def setView(view: GamePanel): Unit

  /**
    * Sets the model to be called when somethings changes about characters.
    *
    * @param model - model to be called.
    */
  def setModel(model: PeerRegisterHandler): Unit

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
  private var view: GamePanel = null
  private var model: PeerRegisterHandler = null

  override var characterImages: Map[String, Map[Direction, Image]] = Map.empty

  /**
    *Returns, given the name of the character, the map containing images for each direction of the character.
    *
    * @param characterName - the name of the character of which you want the pictures.
    * @return map of images for each direction of the character.
    */
  override def getCharacterImages(characterName: String) = characterImages.get(characterName).getOrElse(Map.empty)


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
      println("IL MIO NOME è: " + character.name)
      println("I NOMI NELLA MAPPA SONO: ")
      characterImages.keySet.foreach(println)
      view.move(characterImages.get(character.name).get(direction), Color.red,
        prePosition.asInstanceOf[Point[Integer,Integer]],
        postPosition.asInstanceOf[Point[Integer,Integer]])
      model.updateRegisterObj
    }

    if(!(preLives equals postLives)) {
      view.updateLives(postLives)
      if(character.hasLost) {
        view.gameOver
        model.updateRegisterObj
      }
    }

    if(character.won) view.showResult(postScore.toString)

    if(postLives <= 0) {
      view.gameOver
      model.updateRegisterObj
    }

    if(!(preScore equals postScore)) view.renderScore(postScore)
  }

  /**
    * Sets the view to be recalled.
    *
    * @param view - view to be recalled.
    */
  override def setView(view: GamePanel) = this.view = view

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
    val flowable = arg.asInstanceOf[Flowable[Object]]
    val ip = flowable.elementAt(0).blockingGet.asInstanceOf[String]
    val message = flowable.elementAt(1).blockingGet.asInstanceOf[String]

    var characterToUpdate: Character = null
    val player = gameMatch.allPlayersIp.find(i => i equals ip)
    if(player isEmpty) {
      throw ThisIpDoesNotExistException("Ip:" + ip + " doesn't exist!")
    } else {
      characterToUpdate = gameMatch.character(player.get).get
    }
    message match {
      case "isDead" =>
        characterToUpdate.isAlive = !flowable.elementAt(2).blockingGet.asInstanceOf[Boolean]
        if(!characterToUpdate.isAlive) view.deleteCharacter(characterToUpdate.position.asInstanceOf[Point[Integer,Integer]])
      case "direction" =>
        val direction = flowable.elementAt(2).blockingGet.asInstanceOf[Direction]

        val prePosition: Point[Int, Int] = characterToUpdate.position
        val preLives: Int = gameMatch.myCharacter.lives.remainingLives
        val preScore: Int = gameMatch.myCharacter.score

        characterToUpdate.go(direction)

        val postPosition: Point[Int, Int] = characterToUpdate.position
        val postLives: Int = gameMatch.myCharacter.lives.remainingLives
        val postScore: Int = gameMatch.myCharacter.score

        if(!(prePosition equals postPosition)) view.move(characterImages.get(characterToUpdate.name).get(direction), Color.red,
          prePosition.asInstanceOf[Point[Integer,Integer]],
          postPosition.asInstanceOf[Point[Integer,Integer]])

        if(!(preLives equals postLives)) {
          view.updateLives(postLives)
          if(postLives <= 0) {
            view.gameOver
            model.updateRegisterObj
          }
        }

        if(!(preScore equals postScore)) view.updateLives(postScore)

        if(gameMatch.myCharacter.won) view.showResult(postScore.toString)

        if(!(preScore equals postScore)) view.renderScore(postScore)
    }
  }

  /**
    * Sets the model to be called when somethings changes about characters.
    *
    * @param model - model to be called.
    */
  override def setModel(model: PeerRegisterHandler) = this.model = model
}

/**
  * Represents the exception throws when the given ip doesn't belong to the current match's ips.
  *
  * @param message - message throws by the exception.
  */
case class ThisIpDoesNotExistException(val message: String = "") extends Exception(message)