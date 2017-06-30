package client.gameElement

import client.utils.Point

/**
  * A generic game item trait, represent every element in the map
  *
  * @author ManuBottax
  */

trait GameItem [T,W] {
  def getPosition: Point[T,W]
}