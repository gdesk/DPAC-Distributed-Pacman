package client.character

import client.gameElement.CharactersNames
import client.{Match, MatchImpl, Playground, PlaygroundImpl}

/**
  * Rapresents the strategy to use when {@Pacman} eats an object
  *
  * @author Margherita Pecorelli
  */
trait EatObjectStrategy {

  /**
    * It's the method that deals with the strategy to use when Pacman eats an object.
    *
    * @param eatenObjectFamily - the string that represents the family which the eatable object belongs to.
    */
  def eat(eatenObjectFamily: String):Unit

}

case class BaseStrategy() extends EatObjectStrategy {

  private val playground: Playground = PlaygroundImpl instance()
  private val game: Match = MatchImpl instance()

  override def eat(family: String) = family match {
    case "pill" =>
      //val pacmanName: String = CharactersNames.Pacman.getName
      game.characters() foreach(c => c.isInstanceOf[Pacman] match {
        case true => c.isKillable = false
        case _ => c.isKillable = true
      })
      val thread = new Thread {
        override def run: Unit = {
          val time = System.currentTimeMillis()
          while(System.currentTimeMillis() < time + BaseStrategy.secondsToWait) {
            //non faccio nulla
          }
          game.characters() foreach(c => c.isInstanceOf[Pacman] match {
            case true => c.isKillable = true
            case _ => c.isKillable = false
          })
        }
      }
      thread.start
  }
}

object BaseStrategy {
  private val secondsToWait = 10000
}
