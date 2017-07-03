package client.character

import alice.tuprolog.{SolveInfo, Term, Theory}
import characterjava.Direction
import client.utils.{Point, ScalaProlog}

/**
  * @author Giulia Lucchi
  */


case class GhostImpl(override val name: String) extends CharacterImpl(false, new LivesImpl(InitializedInfoImpl.getCharacterLives("ghost"))){
  setPosition(Point[Int,Int](20,20))


  override def go(direction: Direction): Unit = super.go(direction)
  /**
    * Manages the strategy of game, that is based on who is the killer and who is killable
    */
  override def checkAllPositions(): Unit = ???



}

