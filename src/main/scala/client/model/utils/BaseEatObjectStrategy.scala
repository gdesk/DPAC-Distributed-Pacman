package client.model.utils

import java.util.{Observable, Observer}

import client.model.character.Pacman
import client.model.gameElement.{Eatable, Pill}
import client.model.{Match, MatchImpl, Playground, PlaygroundImpl}

/**
  * Represents the implementation of the strategy to use when a character eats an eatable object.
  *
  * @author Margherita Pecorelli
  */
case class BaseEatObjectStrategy() extends EatObjectStrategy with Observer{

  private val playground: Playground = PlaygroundImpl
  private val game: Match = MatchImpl
  private val timer: Timer = new TimerImpl

  timer.addObserver(this)

  /**
    * Implements the strategy to use when a character eats an eatable object.
    *
    * @param eatenObject - the eatable object eaten by th character.
    */
  override def eat(eatenObject: Eatable) = {
    if(eatenObject.isInstanceOf[Pill]) {
      game.allCharacters.foreach(c => c.isKillable = !c.isInstanceOf[Pacman])
      timer.counts(BaseEatObjectStrategy.getMillisecondsToWait)
    }
  }

  /**
    * Called when timer has ended up counting.
    *
    * @param observable - the observable who notified me.
    * @param arg - not used
    */
  override def update(observable: Observable, arg: scala.Any) = game.allCharacters.foreach(c => c.isKillable = c.isInstanceOf[Pacman])

}

/**
  * Represents the static BaseEatObjectStrategy's values.
  */
object BaseEatObjectStrategy {

  private val millisecondsToWait = 10000

  def getMillisecondsToWait: Long = millisecondsToWait

}