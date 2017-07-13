package client.model.gameElement

import client.model.utils.Point

/**
  * Created by margherita on 10/07/17.
  */
case class Bell(override val id: String, override val position: Point[Int, Int]) extends Eatable {
  /**
    * Returns the value that is given as score when Pacman eat that item.
    *
    * @return the score value.
    */
  override def score: Int = Bell.score

  /**
    * Returns the family to which the eatable object belongs
    *
    * @return object's family
    */
  override def belonginFamily: String = "bell"
}

object Bell {
  private val score = 3000
}