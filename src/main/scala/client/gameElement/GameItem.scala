package client.gameElement

import client.utils.Point

/**
  * Created by ManuBottax on 25/06/2017.
  */

trait GameItem [T,W] {
  def position: Point[T, W]
}


