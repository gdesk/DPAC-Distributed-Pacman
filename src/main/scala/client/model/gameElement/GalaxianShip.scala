package client.model.gameElement

import client.model.utils.Point

/**
  * Represents all galaxian ships.
  *
  * @author Margherita Pecorelli
  */
case class GalaxianShip(override val id: String, override val position: Point[Int, Int]) extends Eatable {

  /**
    * Returns the eatable object's score given to a character when it eats that eatable.
    *
    * @return eatable object's score.
    */
  override def score: Int = GalaxianShip.score

}


/**
  * Represents the static GalaxianShip's values.
  */
private object GalaxianShip {

  private val score = 2000

}