package client.gameElement

import client.Match
import client.utils.Point

/**
  * Created by ManuBottax on 25/06/2017.
  */


class Dot (override val position: Point[Double, Double] ) extends Eatable {

  override val score: Int = 10
  override def effect (x: Match) : Unit = incrementScore(x)

  private def incrementScore = (x: Match) => x.score = x.score + score
}

object Dot {
  def apply(position: Point[Double, Double]): Dot = new Dot(position)
}

class Pill (override val position: Point[Double, Double]) extends Eatable {

  override val score: Int = 50
  override def effect (x: Match) : Unit  = scareGhostAndIncrement (x)

  private def scareGhostAndIncrement = (x: Match) => {
      x.score = x.score + score
      x.scareGhost()
  }
}

object Pill {
  def apply (position: Point[Double, Double]) : Pill = new Pill(position)
}
