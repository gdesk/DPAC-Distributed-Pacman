package client.gameElement

import client.utils.Position

/**
  * A generic game item trait, represent every element in the map
  *
  * @author ManuBottax
  */
trait GameItem {

  /** the position of the item in the playground. */
  def position: Position[Int,Int]

  def itemType: ItemType
}