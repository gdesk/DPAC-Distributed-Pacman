package client.model.gameElement

import client.model.utils.Point

/**
  * Represents all grapes.
  *
  * @author Margherita Pecorelli
  */
case class Grape(override val id: String, override val position: Point[Int, Int]) extends Fruit {

  /**
    * Returns the eatable object's score given to a character when it eats that eatable.
    *
    * @return eatable object's score.
    */
  override def score: Int = Grape.score

}

/**
  * Represents the static Grape's values.
  */
object Grape {

  private val score = 1000

}
