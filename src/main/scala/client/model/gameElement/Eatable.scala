package client.model.gameElement

/**
  * Represents all eatable items in the game.
  *
  * @author Margherita Pecorelli
  */
trait Eatable extends GameItem {

    /**
      * Returns the eatable object's score given to a character when it eats that eatable.
      *
      * @return eatable object's score.
      */
    def score: Int

    /**
      * Returns the identifier of the eatable object.
      *
      * @return the eatable object's identifier.
      */
    def id: String

}