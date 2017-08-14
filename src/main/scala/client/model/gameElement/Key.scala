package client.model.gameElement

import client.model.utils.Point

/**
  * Created by margherita on 10/07/17.
  */
case class Key(override val id: String, override val position: Point[Int, Int]) extends Eatable {
  /**
    * Returns the value that is given as score when Pacman eat that item.
    *
    * @return the score value.
    */
  override def score: Int = Key.score

}

object Key {
  private val score = 5000
}