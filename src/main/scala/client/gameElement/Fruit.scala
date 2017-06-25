package client.gameElement

import client._
import client.utils.Point

import scala.util.Random

/**
  * Created by ManuBottax on 25/06/2017.
  */
class Fruit (override val position: Point[Double, Double]) extends Eatable{

  override val score: Int = getScore
  override def effect (x: Match) : Unit = incrementScore(x)

  private def incrementScore = (x: Match) => x.score = x.score + score

  private val id: Int = Random.nextInt(9)

  private def getScore: Int = {
    FruitScore.score(id)
  }
}

object Fruit{
  def apply(position: Point[Double, Double]): Fruit = new Fruit(position)
}

object FruitScore {
  val score: List[Int] = List(100,300,500,700,1000,2000,3000,5000)
}
