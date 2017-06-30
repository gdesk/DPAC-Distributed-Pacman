package client.gameElement

import client.utils.Point

/**
  * A generic game item trait, represent every element in the map
  *
  * @author ManuBottax
  */
trait GameItem {

  /** the position of the item in the playground. */
  def position: Point[Int,Int]
}