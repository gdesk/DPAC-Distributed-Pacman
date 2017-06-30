package client.utils

/**
  * Created by Giulia Lucchi on 28/06/2017.
  */
trait Lives {
  def decrement(): Unit
  def decrementOf(gap: Int): Unit
  def remainingLives(): Int
}
case class LivesImpl( var remainingLives: Int) extends Lives {
  override def decrement() = remainingLives = remainingLives-1
  override def decrementOf(gap: Int) = remainingLives = remainingLives - gap
}
