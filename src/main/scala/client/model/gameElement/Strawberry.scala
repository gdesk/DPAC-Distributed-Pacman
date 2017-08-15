package client.model.gameElement

import client.model.utils.Point

/**
  * Represents all strawberries.
  *
  * @author Margherita Pecorelli
  */
case class Strawberry(override val id: String, override val position: Point[Int, Int]) extends Fruit {

  /**
    * Returns the eatable object's score given to a character when it eats that eatable.
    *
    * @return eatable object's score.
    */
  override def score: Int = Strawberry.score
}


/**
  * Represents the static Strawberry's values.
  */
private object Strawberry {

  private val score = 300

}