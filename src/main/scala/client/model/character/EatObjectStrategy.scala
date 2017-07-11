package client.model.character

import client.model._

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