package client.model.gameElement

import client.model.utils.Point

/**
  * Represents all bells.
  *
  * @author Margherita Pecorelli
  */
case class Bell(override val id: String, override val position: Point[Int, Int]) extends Eatable {

  /**
    * Returns the eatable object's score given to a character when it eats that eatable.
    *
    * @return eatable object's score.
    */
  override def score: Int = Bell.score

}

/**
  * Represents the static Bell's values.
  */
object Bell {

  private val score = 3000

}