package client.controller

import java.awt.Image
import java.io.File
import java.util.{Observable, Observer}

import client.model._
import client.model.character.Character
import client.model.gameElement.GameItem
import client.model.utils.Dimension

import scala.collection.mutable.Map

/**
  * Created by margherita on 11/07/17.
  */
trait ControllerMatch {

  def getRanges: List[Range]

  def getCharacters: Map[String, Image]

  def chooseCharacter(character: Character): Boolean

  def getPlaygrounds: List[File]

  def choosePlayground(playground: File): Unit

  def MatchResul(result: MatchResult, user: String): Unit

  /**
    * Method called to start the match.
    *
    * @param players - the players' Map composed with characters and users.
    * @param character - the character of the main user.
    * @param playgroundDimention - the playground's dimension.
    * @param ground - the ground chosen.
    */
  def startMatch(players: Map[Character, String], character: Character, playgroundDimention: Dimension, ground: List[GameItem]): Unit

}

case class BaseControllerMatch(/*private val view: View*/) extends ControllerMatch with Observer {

  private val gameMatch: Match = MatchImpl instance()
  private val playeground: Playground = PlaygroundImpl instance()

  override def getRanges = ToClientCommunication getRangers

  override def getCharacters = ToClientCommunication getCharacters

  override def chooseCharacter(character: Character) = ToClientCommunication chooseCharacter(character)

  override def getPlaygrounds = ToClientCommunication getPlaygrounds

  override def choosePlayground(playground: File) = ToClientCommunication choosePlayground(playground)

  override def MatchResul(result: MatchResult, user: String) = ToClientCommunication MatchResul(result, user)

  /**
    * Method called to start the match.
    *
    * @param players             - the players' Map composed with characters and users.
    * @param character           - the character of the main user.
    * @param playgroundDimention - the playground's dimension.
    * @param ground              - the ground chosen.
    */
  override def startMatch(players: Map[Character, String], character: Character, playgroundDimention: Dimension, ground: List[GameItem]) = {
    gameMatch addPlayers players
    gameMatch myCharacter = character
    gameMatch playground = playeground
    playeground dimension = playgroundDimention
    playeground ground = ground
  }

  Map<NomePers: String, Map<direzione: Direction, immagine>>

  override def update(o:Observable, arg: scala.Any) = arg.isInstanceOf[] match {
    case true =>
  }


}