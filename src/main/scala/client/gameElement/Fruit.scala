package client.gameElement

import client.utils.Point

/** Fruit, used in the game as object that Pacman can eat to gain extra point.
  * Is a subtype of trait [[client.gameElement.Eatable]]
  *
  * @author manuBottax
  */
trait Fruit extends Eatable {

  def fruitTypes: Fruits
}

/** Implementation of [[Fruit]] for the virtual version of the game.
  *
  * @constructor create a new fruit with a position in the playground.
  * @param position the position in the playground.
  * @param fruitTypes the type of this object. avery types has a different score. (see [[client.gameElement.Fruits]] )
  *
  * @author manuBottax
  */
class VirtualFruit (override val position: Point[Int, Int], override val fruitTypes: Fruits) extends Fruit{

  /** return the score value for that fruit, randomly chosen in a list of possible score value.
    *
    * @return the score
    */
  override val score: Int = fruitTypes.getScore

  override def itemType: ItemType = ItemType.Fruit
}

/** Factory for [[client.gameElement.VirtualFruit]] instances. */
object VirtualFruit{

  /** Create a Fruit with a given position of default type (Cherry)
    *
    * @param position its position
    */
  def apply(position: Point[Int, Int]): VirtualFruit = new VirtualFruit(position, Fruits.Cherry)

  /** Create a Fruit with a given position and specified types (see [[client.gameElement.Fruits]] )
    *
    * @param position its position
    */
  def apply(position: Point[Int, Int], fruitTypes: Fruits): VirtualFruit = new VirtualFruit(position, fruitTypes)
}