package client.controller

import java.awt.Image
import java.util.{Observable, Observer}

import client.communication.model.ToClientCommunication
import client.model._
import client.view.SelectCharacterView

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

  def view: SelectCharacterView

  def view_=(view: SelectCharacterView): Unit

  def model: ToClientCommunication

  def model_=(model: ToClientCommunication): Unit

  def sendRequestAt(username: String): Unit

  def sendResponse(response: Boolean): Unit

  def startMatch: Map[String, Map[Direction, Image]]

}

case class BaseControllerMatch private() extends ControllerMatch with Observer {

  private val gameMatch: Match = MatchImpl instance()
  private val playground: Playground = PlaygroundImpl instance()

  override var view: SelectCharacterView = null
  override var model: ToClientCommunication = null

  override def getRanges = model getRanges

  override def getCharacters = model getCharactersToChoose

  override def chooseCharacter(characterName: String) = model chooseCharacter characterName

  override def getPlaygrounds = model getPlaygrounds

  override def choosePlayground(playground: Int) = model choosePlayground playground

  override def MatchResul(result: MatchResult, user: String) = model MatchResult (result, user)

  override def sendRequestAt(username: String) = model.sendRequest(username)

  override def sendResponse(response: Boolean) = model.sendResponse(response)

  override def startMatch = {
    model startMatch;
    view.???????(model getTeamCharacter)
  }

  override def update(o:Observable, arg: scala.Any) = {
    if(arg equals "StartMatch") {
     view startMatch
    } else {
      val game: (String, _) = if(arg.isInstanceOf[(String, _)]) {arg.asInstanceOf[(String, _)]} else {null}
      if(game != null) {
        game._1 match {
          case "GameRequest" => view.haveRequest(game._2)
          case "GameResponse" => view.haveResponse(game._2)
        }
      }
    }
  }

}

object BaseControllerMatch {

  private var _instance: BaseControllerMatch = null

  def instance(): BaseControllerMatch = {
    if(_instance == null) _instance = BaseControllerMatch()
    _instance
  }

}