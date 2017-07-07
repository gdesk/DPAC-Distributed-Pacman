package client.gameElement

import client.utils.Point

/**
  * Trait that specify an item as eatable by Pacman.
  */
trait Eatable extends GameItem {

    /**
      * Returns the value that is given as score when Pacman eat that item.
      *
      * @return the score value.
      */
    def score: Int

    /**
      * Returns the family to which the eatable object belongs
      *
      * @return object's family
      */
    def belonginFamily: String
}

case class EatableImpl(override val position: Point[Int, Int], val eatableObject: Eatables) extends Eatable {
    /**
      * Returns the value that is given as score when Pacman eat that item.
      *
      * @return the score value.
      */
    override def score: Int = eatableObject getScore

    /**
      * Returns the family to which the eatable object belongs
      *
      * @return object's family
      */
    override def belonginFamily: String = eatableObject getFamily
}


