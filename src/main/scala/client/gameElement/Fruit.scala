package client.gameElement

import client.utils.{Point, ScoreUtils}

import scala.util.Random

/** Fruit, used in the game as object that Pacman can eat to gain extra point.
  * Is a subtype of trait [[client.gameElement.Eatable]]
  *
  * @constructor create a new fruit with a position in the playground.
  * @param position the position in the playground.
  * @param fruitTypes the type of this object. avery types has a different score. (see [[client.gameElement.Fruits]] )
  *
  * @author manuBottax
  */
class Fruit (override val position: Point[Int, Int], val fruitTypes: Fruits) extends Eatable{

  /** return the score value for that fruit, randomly chosen in a list of possible score value.
    *
    * @return the score
    */
  override val score: Int = fruitTypes.getScore

  override def itemType: String = "fruit"
}

/** Factory for [[client.gameElement.Fruit]] instances. */
object Fruit{

  /** Create a Fruit with a given position of default type (Cherry)
    *
    * @param position its position
    */
  def apply(position: Point[Int, Int]): Fruit = new Fruit(position, Fruits.Cherry)

  /** Create a Fruit with a given position and specified types (see [[client.gameElement.Fruits]] )
    *
    * @param position its position
    */
  def apply(position: Point[Int, Int], fruitTypes: Fruits): Fruit = new Fruit(position, fruitTypes)
}