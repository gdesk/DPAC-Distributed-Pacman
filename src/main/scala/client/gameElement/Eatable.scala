package client.gameElement

/** Trait that specify an item as eatable by Pacman.*/
trait Eatable extends GameItem {

    /** the value that is given as score when Pacman eat that item.
      *
      * @return the score value
      */
    def score: Int
}


