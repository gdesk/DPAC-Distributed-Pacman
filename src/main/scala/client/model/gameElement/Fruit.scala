package client.model.gameElement

import client.model.utils.Point

/**
  * Created by margherita on 10/07/17.
  */
abstract class Fruit(override val id: String, override val position: Point[Int, Int]) extends Eatable {

  /**
    * Returns the family to which the eatable object belongs
    *
    * @return object's family
    */
  override def belonginFamily: String = "fruit"
}