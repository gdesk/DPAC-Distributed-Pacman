package client.controller

import client.model._
import client.model.character.Character
import client.model.gameElement.GameItem
import client.model.utils.{Dimension, Point}

import scala.collection.mutable.Map

/**
  * This trait represents the controller's interface with which the view can interact.
  */
trait Controller {

  /**
    * Method called to start the match.
    *
    * @param players - the players' Map composed with characters and users.
    * @param character - the character of the main user.
    * @param playgroundDimention - the playground's dimension.
    * @param ground - the ground chosen.
    */
  def startMatch(players: Map[Character, String], character: Character, playgroundDimention: Dimension, ground: List[GameItem]): Unit

  /**
    * Method called when the user moves his character. This method calls the method in the model.
    *
    * //@param character - the character which is moving.
    * @param direction - the direction of the movement.
    * @return the new character's position
    */
  def move(/*character: Character[Int, Int], */direction: Direction): Point[Int, Int]

}