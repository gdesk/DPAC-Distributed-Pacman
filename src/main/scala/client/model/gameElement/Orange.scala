package client.model.gameElement

import client.model.utils.Point

/**
  * Represents all oranges.
  *
  * @author Margherita Pecorelli
  */
case class Orange(override val id: String, override val position: Point[Int, Int]) extends Fruit {

  /**
    * Returns the eatable object's score given to a character when it eats that eatable.
    *
    * @return eatable object's score.
    */
  override def score: Int = Orange.score

}

/**
  * Represents the static Orange's values.
  */
object Orange {

  private val score = 500

}