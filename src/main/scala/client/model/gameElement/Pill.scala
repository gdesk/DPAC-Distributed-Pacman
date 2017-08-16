package client.model.gameElement

import client.model.utils.Point

/**
  * Represents all pills.
  *
  * @author Margherita Pecorelli
  */
case class Pill(override val id: String, override val position: Point[Int, Int]) extends Eatable {

  /**
    * Returns the eatable object's score given to a character when it eats that eatable.
    *
    * @return eatable object's score.
    */
  override def score: Int = Pill.score

}

/**
  * Represents the static Pill's values.
  */
object Pill {

  private val score = 50

}