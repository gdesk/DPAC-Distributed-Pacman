package client.model.gameElement

import client.model.utils.Point

/**
  * Represents all dots.
  *
  * @author Margherita Pecorelli
  */
case class Dot(override val id: String, override val position: Point[Int, Int]) extends Eatable {

  /**
    * Returns the eatable object's score given to a character when it eats that eatable.
    *
    * @return eatable object's score.
    */
  override def score: Int = Dot.score

}


/**
  * Represents the static Dot's values.
  */
private object Dot {

  private val score = 10

}