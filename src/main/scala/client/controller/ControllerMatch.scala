package client.controller

import java.awt.Image
import java.util.{Observable, Observer}
import javax.swing.ImageIcon

import client.communication.model.ToClientCommunication
import client.model._
import client.view._
import client.view.`match`.CreateTeamView
import client.view.base.LoadingView

import scala.collection.mutable.HashMap



/**
  * Created by margherita on 11/07/17.
  */
trait ControllerMatch extends Observer {

  def getRanges: List[Range]

  def rangeChoosed(range: client.view.utils.Range): Unit

  def getCharacters: Map[String, Image]

  def chooseCharacter(characterName: String): Boolean

  def getPlaygrounds: Map[Int, Image]

  def choosePlayground(playgroundId: Int): Unit

  def MatchResul(result: MatchResult, user: String): Unit

  def loadingView(view: LoadingView): Unit

  def teamView(view: CreateTeamView): Unit

  def model(model: ToClientCommunication): Unit

  def sendRequestAt(username: String): Unit

  def sendResponse(response: Boolean): Unit

  def startMatch: Unit

}

case class BaseControllerMatch private() extends ControllerMatch {

  private val gameMatch: Match = MatchImpl.instance()
  private val playground: Playground = PlaygroundImpl.instance()
  private var _loadingView: LoadingView = null
  private var _teamView: CreateTeamView = null
  private var _model: ToClientCommunication = null

  override def model(model: ToClientCommunication) = _model = model

  override def loadingView(view: LoadingView): Unit = _loadingView = view

  override def teamView(view: CreateTeamView): Unit = _teamView = view

  override def getRanges = _model.getRanges

  override def rangeChoosed(range: client.view.utils.Range) = _model.selectRange(Range(range.getMin,range.getMax))

  override def getCharacters = _model.getCharactersToChoose


  override def chooseCharacter(characterName: String) = _model.chooseCharacter(characterName)

  override def getPlaygrounds = {
    val grounds: Int = _model.getPlaygrounds
    val playgrounds: HashMap[Int, Image] = HashMap.empty
    for(i <- 0 to grounds) {
      playgrounds += (i -> new ImageIcon("resources/playground/images/" + i + ".png").getImage)
    }
    playgrounds.toMap
  }


  override def choosePlayground(playground: Int) = _model.choosePlayground(playground)

  override def MatchResul(result: MatchResult, user: String) = _model.matchResult(result, user)

  override def sendRequestAt(username: String) = _model.sendRequest(username)

  override def sendResponse(response: Boolean) = _model.sendResponse(response)

  override def startMatch = {
    _model.startMatch;
    BaseControllerCharacter.instance().setCharacterImages(_model.getTeamCharacter)
  }

  override def update(o:Observable, arg: scala.Any) = {
    if(arg equals "StartMatch") {
      _loadingView renderGamePanel
    } else {
      val game: (String, _) = if(arg.isInstanceOf[(String, _)]) {arg.asInstanceOf[(String, _)]} else {null}
      if(game != null) {
        game._1 match {
          case "GameRequest" => MainFrame.getInstance().showRequest(game._2.asInstanceOf[String])
          case "GameResponse" => _teamView.renderPlayerResponse(game._2.asInstanceOf[Boolean])
          case "playerInMatch" => _teamView.renderPlayerResponse(true)
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