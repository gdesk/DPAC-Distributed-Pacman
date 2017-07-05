package client.gameElement

import client._
import client.utils.{Point, ScoreUtils}

import scala.util.Random

/** Fruit, used in the game as object that Pacman can eat to gain extra point.
  * Is a subtype of trait [[client.gameElement.Eatable]]
  *
  * @constructor create a new fruit with a position in the playground.
  * @param position the position in the playground.
  *
  * @author manuBottax
  */
class Fruit (override val position: Point[Double, Double]) extends Eatable{

  private val id: Int = Random.nextInt(ScoreUtils.FRUIT_SCORE_LIST_DIMENSION)

  /** return the score value for that fruit, randomly chosen in a list of possible score value.
    *
    * @return the score
    */
  override val score: Int = getScore
  override def effect (x: Match) : Unit ={}


  private def getScore: Int = ScoreUtils.FRUIT_SCORE_LIST(id)

}

/** Factory for [[client.gameElement.Fruit]] instances. */
object Fruit{

  /** Create a Fruit with a given position
    *
    * @param position its position
    */
  def apply(position: Point[Double, Double]): Fruit = new Fruit(position)
}
