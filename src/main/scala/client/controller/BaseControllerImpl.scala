package client.controller

import client.model._
import client.model.character.{Character, Pacman}
import client.model.gameElement.{Eatable, GameItem}
import client.model.utils.{Dimension, Point}

import scala.collection.mutable.Map

/**
  * Created by margherita on 11/07/17.
  */
class BaseControllerImpl() extends Controller {

  private val gameMatch: Match = MatchImpl instance()
  private val playeground: Playground = PlaygroundImpl instance()
  private var eateaObj: List[Eatable] = List empty

  /**
    * Method called to start the match.
    *
    * @param players             - the players' Map composed with characters and users.
    * @param character           - the character of the main user.
    * @param playgroundDimention - the playground's dimension.
    * @param ground              - the ground chosen.
    */
  override def startMatch(players: Map[Character[Int, Int], String], character: Character[Int, Int], playgroundDimention: Dimension, ground: Map[Point[Int, Int], GameItem]) = {
    gameMatch addPlayers players
    gameMatch myCharacter() = character
    gameMatch playground() = playeground
    playeground dimension = playgroundDimention
    playeground ground = ground
  }

  /**
    * Method called when the user moves his character. This method calls the method in the model.
    *
    * //@param character - the character which is moving.
    *
    * @param direction - the direction of the movement.
    * @return the new character's position
    */
  override def move(/*character: Character[Int, Int], */ direction: Direction): Point[Int, Int] = {
    val character = gameMatch myCharacter()
    character go direction
    if(!(eateaObj equals playeground.eatenObjects)) {
      eateaObj = playeground eatenObjects
      //BaseViewImpl.eatenObject((playeground eatenObjects) take 0)
    }

    //vedere se ho mangiato un personaggio o se sono stato mangaito
    //BaseViewImpl.lives((character lives) remainingLives)

    //vedere se il score è cambiato
    //BaseViewImpl.score(character score)

    character position
  }

  //ci vorrebbe unwhile true per controllare se i personaggi sono killable o no
  /*
  private def isKillable(): Boolean = {
    val thread = new Thread {
      override def run: Unit = {


      }
    }
    thread.start
    true
  }*/

}