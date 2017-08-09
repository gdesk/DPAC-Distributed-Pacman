package client.controller3

import java.awt.Image
import java.io.File
import java.util
import java.util.{Observable, Observer}

import client.communication.model.ToClientCommunication
import client.model._

import client.model.character.Character
import client.model.gameElement.GameItem
import client.model.utils.Dimension
import client.utils.IOUtils
import client.view._

import scala.collection.mutable
import scala.collection.JavaConverters._
import scala.collection.immutable.HashMap


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

  def view: View

  def view_=(view: View): Unit

  def model: ToClientCommunication

  def model_=(model: ToClientCommunication): Unit

//  def startMatch(players: Map[Character, String], character: Character, playgroundDimention: Dimension, ground: List[GameItem]): Unit

  def startMatch: Map[String, Map[Direction, Image]]

}

case class BaseControllerMatch private (private val view1: SelectCharacterView) extends ControllerMatch with Observer {


  private val gameMatch: Match = MatchImpl instance()
  private val playground: Playground = PlaygroundImpl instance()


  override def getRanges =  List( Range (3,5), Range(5,10), Range(10,15)) //model getRanges TODO cambia

  override def getCharacters = {
    val map = new HashMap[String,Image]
    var res = map + ("PACMAN" ->  new PacmanView().getCharacterRight)
    res = res + ("RED" ->  new RedGhostView().getCharacterRight)
    res = res + ("BLUE" ->  new BlueGhostView().getCharacterRight)
    res = res + ("PINK" ->  new PinkGhostView().getCharacterRight)
    res
  }// model getCharactersToChoose TODO cambia

  override def chooseCharacter(characterName: String) = true //model chooseCharacter characterName TODO cambia

  override def getPlaygrounds = {
    val map = new HashMap[Int,Image]
    var res: Map[Int, Image] = map + (1 -> Utils.getImage("/mazes/arancione"))
    res = res + (2 -> Utils.getImage("/mazes/rosso"))
    res = res + (3 -> Utils.getImage("/mazes/blu"))
    res = res + (4 -> Utils.getImage("/mazes/rosa"))
    res = res + (5 -> Utils.getImage("/mazes/rossoGiallo"))
    res
  }//model getPlaygrounds TODO cambia


  override def choosePlayground(playground: Int) = {}//model choosePlayground playground

  override def MatchResul(result: MatchResult, user: String) = model MatchResult(result, user)

  /*
  override def startMatch(players: Map[Character, String], character: Character, playgroundDimention: Dimension, ground: List[GameItem]) = {
    gameMatch addPlayers (mutable.Map(players.toSeq: _*)); //": _*" -> prima trasforma players in una sequenza e poi prende una coppia chiave-valore alla volta e l'aggiunge alla mutable.Map
    gameMatch myCharacter = character
    gameMatch playground = playground
    playground dimension = playgroundDimention
    playground ground = ground
  }
  */

  override def startMatch = {
    model startMatch;
    model getTeamCharacter
  }

  override def update(o: Observable, arg: scala.Any) = arg match {

    case x if x.isInstanceOf[Map[String, Map[Direction, Image]]] => {

      val x1 = arg.asInstanceOf[Map[String, Map[Direction, Image]]]
      val x2: util.Map[String, java.util.Map[Direction, Image]] = new util.HashMap[String, java.util.Map[Direction, Image]]()
      x1.keySet.foreach(k => x2 put(k, (x1.get(k) get).asJava))
      //view charactersChoosen x2
    }
    case _ => {
      //TODO salvare il file
      val file = arg.asInstanceOf[File]
      val playground = IOUtils.getPlaygroundFromFile(file)
     // view playgroundChoosen (playground)
    }
  }

  override def view: View = ???

  override def view_=(view: View): Unit = ???

  override def model: ToClientCommunication = ???

  override def model_=(model: ToClientCommunication): Unit = ???
}


object BaseControllerMatch {
  private var _instance: BaseControllerMatch = null
  def instance(view :SelectCharacterView): BaseControllerMatch = _instance match{
    case null => _instance = new BaseControllerMatch(view); _instance
    case _ => _instance
  }
}

