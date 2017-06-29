package client.gameElement

import client.Match

/**
  * Created by ManuBottax on 25/06/2017.
  */
trait Eatable extends GameItem[Double, Double] {

    def score: Int
    def effect (x: Match) : Unit
}


