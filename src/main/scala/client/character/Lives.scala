package client.character

/**
  * Created by Giulia Lucchi on 28/06/2017.
  */
trait Lives {
  /**
    * Decrement of one the remaining lives
    */
  def decrement(): Unit

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
  def remainingLives(): Int
}

class LivesImpl( var remainingLives: Int) extends Lives{
  /**
    * Decrement of one the remaining lives
    */
  override def decrement(): Unit = ???

  /**
    * Decrement of a particular gap the remaining lives
    *
    * @param gap number of lives to decrement
    */
  override def decrementOf(gap: Int): Unit = ???
}
