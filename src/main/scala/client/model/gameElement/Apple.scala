package client.model.gameElement

import client.model.utils.Point

/**
  * Represents all apples.
  *
  * @author Margherita Pecorelli
  */
case class Apple(override val id: String, override val position: Point[Int, Int]) extends Fruit {

  /**
    * Returns the eatable object's score given to a character when it eats that eatable.
    *
    * @return eatable object's score.
    */
  override def score: Int = Apple.score

}

/**
  * Represents the static Apple's values.
  */
object Apple {

  private val score = 700

}