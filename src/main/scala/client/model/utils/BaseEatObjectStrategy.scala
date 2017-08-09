package client.model.utils

import java.util.{Observable, Observer}

import client.model.character.Pacman
import client.model.gameElement.{Eatable, Pill}
import client.model.{Match, MatchImpl, Playground, PlaygroundImpl}

/**
  * Created by margherita on 11/07/17.
  */
case class BaseEatObjectStrategy() extends EatObjectStrategy with Observer{

  private val playground: Playground = PlaygroundImpl instance()
  private val game: Match = MatchImpl instance()
  private val timer: Timer = TimerImpl(this)

  /**
    * It's the method that deals with the strategy to use when Pacman eats an object.
    *
    * @param eatenObject - the Eatable eaten.
    */
  override def eat(eatenObject: Eatable) = eatenObject.isInstanceOf[Pill] match {
  /*  case true =>
      game.characters foreach(c => c.isInstanceOf[Pacman] match {
        case true => c.isKillable = false
        case _ => c.isKillable = true
      })
      timer.counts(BaseEatObjectStrategy.millisecondsToWait)*/
    case _ =>
  }

  override def update(observable: Observable, arg: scala.Any) = {
   /* game.characters foreach (c => c.isInstanceOf[Pacman] match {
      case true => c.isKillable = true
      case _ => c.isKillable = false
    })*/

  }
}

object BaseEatObjectStrategy {
  private val millisecondsToWait = 10000

  def getMillisecondsToWait: Long = millisecondsToWait
}
