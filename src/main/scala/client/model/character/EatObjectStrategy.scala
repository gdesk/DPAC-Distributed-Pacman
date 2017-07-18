package client.model.character

import client.model._
import client.model.gameElement.Eatable

/**
  * Rapresents the strategy to use when {@Pacman} eats an object
  *
  * @author Margherita Pecorelli
  */
trait EatObjectStrategy {

  /**
    * It's the method that deals with the strategy to use when Pacman eats an object.
    *
    * @param eatenObject - the Eatable eaten.
    */
  def eat(eatenObject: Eatable): Unit

}