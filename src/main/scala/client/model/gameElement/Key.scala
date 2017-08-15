package client.model.gameElement

import client.model.utils.Point

/**
  * Represents all keys.
  *
  * @author Margherita Pecorelli
  */
case class Key(override val id: String, override val position: Point[Int, Int]) extends Eatable {

  /**
    * Returns the eatable object's score given to a character when it eats that eatable.
    *
    * @return eatable object's score.
    */
  override def score: Int = Key.score

}


/**
  * Represents the static Key's values.
  */
private object Key {

  private val score = 5000

}