package client.gameElement

import client._
import client.utils.{Point, ScoreUtils}

import scala.util.Random

/**
  * Created by ManuBottax on 25/06/2017.
  */
class Fruit (override val position: Point[Double, Double]) extends Eatable{

  private val id: Int = Random.nextInt(ScoreUtils.FRUIT_SCORE_LIST_DIMENSION)

  override val score: Int = getScore
  override def effect (x: Match) : Unit = incrementScore(x)

  private def getScore: Int = ScoreUtils.FRUIT_SCORE_LIST(id)
  private def incrementScore = (x: Match) => x.score = x.score + score

}

object Fruit{
  def apply(position: Point[Double, Double]): Fruit = new Fruit(position)
}
