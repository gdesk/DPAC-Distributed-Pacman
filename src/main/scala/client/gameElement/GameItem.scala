package client.gameElement

import client.utils.Point

/**
  * A generic game item trait, represent every element in the map
  *
  * @tparam T type of the position on the x axis
  * @tparam W type of the position on the y axis
  *
  * @author ManuBottax
  */
trait GameItem [T,W] {

  /** the position of the item in the playground. */
  def position: Point[T,W]
}