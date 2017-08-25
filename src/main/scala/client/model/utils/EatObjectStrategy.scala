package client.model.utils

import client.model.gameElement.Eatable

/**
  * Represents the strategy to use when a character eats an eatable object.
  *
  * @author Margherita Pecorelli
  */
trait EatObjectStrategy {

  /**
    * Implements the strategy to use when a character eats an eatable object.
    *
    * @param eatenObject - the eatable object eaten by the character.
    */
  def eat(eatenObject: Eatable): Unit

}