package client.gameElement

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
abstract class Fruit (override val position: Point[Int, Int]) extends Eatable{

  private val id: Int = Random.nextInt(ScoreUtils.FRUIT_SCORE_LIST_DIMENSION)

  /** return the score value for that fruit, randomly chosen in a list of possible score value.
    *
    * @return the score
    */
  override val score: Int = getScore

  private def getScore: Int = ScoreUtils.FRUIT_SCORE_LIST(id)

}
