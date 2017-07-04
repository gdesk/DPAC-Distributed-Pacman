package client.gameElement

import client.Match

/** Trait that specify an item as eatable by Pacman.*/
trait Eatable extends GameItem[Double, Double] {

    /** the value that is given as score when Pacman eat that item.
      *
      * @return the score value
      */
    def score: Int

    /** the effect generated on the current match when Pacman eat that item.
      * e.g. increase the score, scare ghosts, ...
      *
      * @param x reference to the current match
      */
    def effect (x: Match) : Unit
}


