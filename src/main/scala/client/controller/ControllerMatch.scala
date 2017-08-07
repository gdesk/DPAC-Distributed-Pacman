package client.controller

import java.awt.Image
import java.util.{Observable, Observer}

import client.communication.model.ToClientCommunicationImpl
import client.model._
import client.model.character.Character
import client.model.gameElement.GameItem
import client.model.utils.Dimension

import scala.collection.mutable

/**
  * Created by margherita on 11/07/17.
  */
trait ControllerMatch {

  def getRanges: List[Range]

  def getCharacters: Map[String, Image]

  def chooseCharacter(characterName: String): Boolean

  def getPlaygrounds: Map[Int, Image]

  def choosePlayground(playgroundId: Int): Unit

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

case class BaseControllerMatch(private val view: View, private val model: ToClientCommunicationImpl) extends ControllerMatch with Observer {

  private val gameMatch: Match = MatchImpl instance()
  private val playground: Playground = PlaygroundImpl instance()

  override def getRanges = model getRanges

  override def getCharacters = model getCharactersToChoose

  override def chooseCharacter(characterName: String) = model chooseCharacter characterName

  override def getPlaygrounds = model getPlaygrounds

  override def choosePlayground(playground: Int) = model choosePlayground playground

  override def MatchResul(result: MatchResult, user: String) = model MatchResult (result, user)

  /**
    * Method called to start the match.
    *
    * @param players             - the players' Map composed with characters and users.
    * @param character           - the character of the main user.
    * @param playgroundDimention - the playground's dimension.
    * @param ground              - the ground chosen.
    */
  override def startMatch(players: Map[Character, String], character: Character, playgroundDimention: Dimension, ground: List[GameItem]) = {
    gameMatch addPlayers (mutable.Map(players.toSeq: _*)); //": _*" -> prima trasforma players in una sequenza e poi prende una coppia chiave-valore alla volta e l'aggiunge alla mutable.Map
    gameMatch myCharacter = character
    gameMatch playground = playground
    playground dimension = playgroundDimention
    playground ground = ground
  }

  override def update(o:Observable, arg: scala.Any) = arg match {
    case x if x.isInstanceOf[Map[String, Map[Direction, Image]]] => view charactersChoosen (arg/*.asInstanceOf[Map[String, Map[Direction, Image]]]*/)
    case _ => view playgroundChoosen (arg/*.asInstanceOf[File]*/)
  }


}