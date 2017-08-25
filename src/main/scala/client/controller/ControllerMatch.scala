package client.controller

import java.awt.Image
import java.util.{Observable, Observer}
import javax.swing.ImageIcon

import client.communication.model.ToClientCommunication
import client.model._
import client.view._
import client.view.`match`.CreateTeamView
import client.view.base.{LoadingView, SelectCharacterView}

import scala.collection.mutable.HashMap

/**
  * Represents the controller for the match management and initialization.
  * Implements Observer since it has to be notify when the match is ready to be played and when other characters send an invitation or a response.
  *
  * @author Margherita Pecorelli
  */
trait ControllerMatch extends Observer {

  /**
    * Returns all ranges of players that can play in the same match.
    *
    * @return a list of all possible ranges of players that can play in the same match.
    */
  def getRanges: List[Range]

  /**
    * Tells the model the chosen range.
    *
    * @param range - the range chosen.
    */
  def chosenRange(range: client.view.utils.Range): Unit

  /**
    * Returns all characters that can be chosen and their image.
    *
    * @return a map of all characters and their image.
    */
  def getCharacters: Map[String, Image]

  /**
    * Tells the model the chosen character.
    *
    * @param characterName - the chosen character.
    */
  def chosenCharacter(characterName: String): Boolean

  /**
    * Returns all playgrounds that can be chosen.
    *
    * @return a map of all playgrounds (identify by the number of their position in the Map) and their image.
    */
  def getPlaygrounds: Map[Int, Image]

  /**
    * Tells the model the chosen playground.
    *
    * @param playgroundId - the chosen playground identify by its id (the number of its position in the map given by method getPlaygrounds).
    */
  def chosenPlayground(playgroundId: Int): Unit

  /**
    * Sends to model an invitation for playing with another user in the same match.
    *
    * @param username - the username of the user to play with.
    */
  def sendRequestAt(username: String): Unit

  /**
    * Sends to model a response of an invitation from another player to play in the same match.
    *
    * @param response - the response of an invitation from another player (true = I want to play; false = I don't want to play).
    */
  def sendResponse(response: Boolean): Unit

  /**
    * Tells the model to start the match.
    */
  def startMatch: Unit

  /**
    * Sends to model the match result to save in the user's profile.
    *
    * @param result - the match result to save.
    * @param user - the user who has achieved the result.
    */
  def loadMatchResul(result: MatchResult, user: String): Unit

  /**
    * Sets the view to be called when the match is ready to start.
    *
    * @param view - the view to be called when the match is ready to start.
    */
  def setLoadingView(view: LoadingView): Unit

  /**
    * Sets the view to be called when the response of an invitation arrives.
    *
    * @param view - the view to be called when the response of an invitation arrives.
    */
  def setTeamView(view: CreateTeamView): Unit

  /**
    * Sets the model to be called.
    *
    * @param model - the model to be called.
    */
  def setModel(model: ToClientCommunication): Unit

  /**
    * Called when the match is ready to be played or and when other characters change the initial match settings.
    * Invokes views to perform actions that result from the changes of the characters.
    *
    * @param observable - the observable who notified me.
    * @param arg - just a message if the match is ready to be played or a tuple with the message about what had changed and the corresponding value.
    */
  override def update(observable:Observable, arg: scala.Any)

}

/**
  * Represents the implementation of the controller for the match management and initialization.
  *
  * @author Margherita Pecorelli
  */
object BaseControllerMatch extends ControllerMatch {

  private val gameMatch: Match = MatchImpl
  private val playground: Playground = PlaygroundImpl
  private var loadingView: LoadingView = _
  private var teamView: CreateTeamView = _
  private var _model: ToClientCommunication = _

  /**
    * Returns all ranges of players that can play in the same match.
    *
    * @return a list of all possible ranges of players that can play in the same match.
    */
  override def getRanges = _model.getRanges

  /**
    * Tells the model the chosen range.
    *
    * @param range - the range chosen.
    */
  override def chosenRange(range: client.view.utils.Range) = _model.selectRange(Range(range.getMin,range.getMax))

  /**
    * Returns all characters that can be chosen and their image.
    *
    * @return a map of all characters and their image.
    */
  override def getCharacters = _model.getCharactersToChoose

  /**
    * Tells the model the chosen character.
    *
    * @param characterName - the chosen character.
    */
  override def chosenCharacter(characterName: String) = _model.chooseCharacter(characterName)

  /**
    * Returns all playgrounds that can be chosen.
    *
    * @return a map of all playgrounds (identify by the number of their position in the Map) and their image.
    */
  override def getPlaygrounds = {
    val grounds: Int = _model.getPlaygrounds
    val playgrounds: HashMap[Int, Image] = HashMap.empty
    for(i <- 0 until grounds) {
      playgrounds += (i -> new ImageIcon("src/main/resources/playground/images/" + i + ".png").getImage)
    }
    playgrounds.toMap
  }

  /**
    * Tells the model the chosen playground.
    *
    * @param playgroundId - the chosen playground identify by its id (the number of its position in the map given by method getPlaygrounds).
    */
  override def chosenPlayground(playgroundId: Int) = _model.choosePlayground(playgroundId)

  /**
    * Sends to model an invitation for playing with another user in the same match.
    *
    * @param username - the username of the user to play with.
    */
  override def sendRequestAt(username: String) = _model.sendRequest(username)

  /**
    * Sends to model a response of an invitation from another player to play in the same match.
    *
    * @param response - the response of an invitation from another player (true = I want to play; false = I don't want to play).
    */
  override def sendResponse(response: Boolean) = _model.sendResponse(response)

  /**
    * Tells the model to start the match.
    */
  override def startMatch = {
    _model.initializedCharatcter()
    var characterMap: scala.collection.mutable.Map[String, Map[Direction, Image]] = scala.collection.mutable.Map.empty
    MatchImpl.allCharacters.foreach(c => characterMap += ((c.name, _model.getTeamCharacter(c.name))))
    BaseControllerCharacter.allCharactersImages = characterMap.toMap
    _model.startMatch
  }

  /**
    * Sends to model the match result to save in the user's profile.
    *
    * @param result - the match result to save.
    * @param user - the user who has achieved the result.
    */
  override def loadMatchResul(result: MatchResult, user: String) = _model.matchResult(result, user)

  /**
    * Sets the view to be called when the match is ready to start.
    *
    * @param view - the view to be called when the match is ready to start.
    */
  override def setLoadingView(view: LoadingView) = loadingView = view

  /**
    * Sets the view to be called when the response of an invitation arrives.
    *
    * @param view - the view to be called when the response of an invitation arrives.
    */
  override def setTeamView(view: CreateTeamView) = teamView = view

  /**
    * Sets the model to be called.
    *
    * @param model - the model to be called.
    */
  override def setModel(model: ToClientCommunication) = _model = model

  /**
    * Called when the match is ready to be played or and when other characters change the initial match settings.
    * Invokes views to perform actions that result from the changes of the characters.
    *
    * @param observable - the observable who notified me.
    * @param arg - just a message if the match is ready to be played or a tuple with the message about what had changed and the corresponding value.
    */
  override def update(observable:Observable, arg: scala.Any) = {
    arg match {
      case message: String =>
        message match {
          case "StartMatch" => loadingView.renderGamePanel
          case "notifyStart" => teamView.nextView
        }
      case _ =>
        val game: (String, scala.Any) = arg match {
          case tuple: (String, scala.Any) => tuple
          case _ => null
        }
        if(game != null) {
          game._1 match {
            case "GameRequest" => MainFrame.getInstance.showRequest(game._2.asInstanceOf[String])
            case "GameResponse" => teamView.renderPlayerResponse(game._2.asInstanceOf[Boolean])
            case "playerInMatch" => teamView.renderPlayerInMatch(Integer.valueOf(game._2.asInstanceOf[Int]))
            case "notifySelection" => MainFrame.getInstance.getContentPane.asInstanceOf[SelectCharacterView].disableCharacter(game._2.asInstanceOf[String])
          }
        }
    }
  }

}