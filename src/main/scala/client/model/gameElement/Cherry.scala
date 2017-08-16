package client.model.gameElement

import client.model.utils.Point

/**
  * Represents all cherries.
  *
  * @author Margherita Pecorelli
  */
case class Cherry(override val id: String, override val position: Point[Int, Int]) extends Fruit {

  /**
    * Returns the eatable object's score given to a character when it eats that eatable.
    *
    * @return eatable object's score.
    */
  override def score: Int = Cherry.score

}

/**
  * Represents the static Cherry's values.
  */
object Cherry {

  private val score = 100

}