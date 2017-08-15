package client.model.utils

/**
  * Created by Giulia Lucchi on 28/06/2017.
  */
//todo:sistemare scala doc
trait Lives {
  /**
    * Decrement of one the remaining lives
    */
  def decrement: Unit

  /**
    * Decrement of a particular gap the remaining lives
    *
    * @param gap  number of lives to decrement
    */
  def decrementOf(gap: Int): Unit

  /**
    * getter of remaining lives
    *
    * @return RemainingLives  the actual number of lives in the match
    */
  def remainingLives: Int

  /**
    * getter of remaining lives
    *
    * @param remainingLives  the number of lives to set
    */
  def remainingLives_=(remainingLives: Int): Unit
}

case class LivesImpl(override var remainingLives: Int) extends Lives{
  /**
    * Decrement of one the remaining lives
    */
  override def decrement = remainingLives  = remainingLives - 1

  /**
    * Decrement of a particular gap the remaining lives
    *
    * @param gap number of lives to decrement
    */
  override def decrementOf(gap: Int) = remainingLives = remainingLives - gap
}
