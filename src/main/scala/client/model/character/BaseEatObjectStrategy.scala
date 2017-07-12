package client.model.character

import client.model.{Match, MatchImpl, Playground, PlaygroundImpl}

/**
  * Created by margherita on 11/07/17.
  */
case class BaseEatObjectStrategy() extends EatObjectStrategy {

  private val playground: Playground = PlaygroundImpl instance()
  private val game: Match = MatchImpl instance()

  /**
    * It's the method that deals with the strategy to use when Pacman eats an object.
    *
    * @param eatenObjectFamily - the string that represents the family which the eatable object belongs to.
    */
  override def eat(eatenObjectFamily: String) = eatenObjectFamily match {
    case "pill" =>
      //val pacmanName: String = CharactersNames.Pacman.getName
      game.characters foreach(c => c.isInstanceOf[Pacman] match {
        case true => c.isKillable = false
        case _ => c.isKillable = true
      })
      val thread = new Thread {
        override def run: Unit = {
          val time = System.currentTimeMillis()
          while(System.currentTimeMillis() < time + BaseEatObjectStrategy.secondsToWait) {
            //non faccio nulla
          }
          game.characters foreach(c => c.isInstanceOf[Pacman] match {
            case true => c.isKillable = true
            case _ => c.isKillable = false
          })
        }
      }
      thread.start
  }
}

object BaseEatObjectStrategy {
  private val secondsToWait = 10000
}
