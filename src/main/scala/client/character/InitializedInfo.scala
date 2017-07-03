package client.character

import java.io.FileInputStream
import java.lang.Integer.valueOf

import alice.tuprolog.{Term, Theory}
import client.utils.{Point, ScalaProlog}

/**
  * Extraction of starting information of the game
  *
  * @author Giulia Lucchi
  */
trait InitializedInfo{
  /**
    *
    * Extracts from the logic of the game implemented in prolog
    * the ghost's or pacman's number of lives
    *
    * @param characterName  the goal in prolog
    * @return number of lives of ghost
  **/
  def getCharacterLives(characterName: String): Int


  /**
    * Extracts from the logic of the game implemented in prolog
    * the pacman's starting position
    *
    * @return the coordinate of pacman's starting position
    */
  def getStartPosition(): Point[Int, Int]
}


object InitializedInfoImpl extends InitializedInfo{
  val FILE_NAME = "src/main/scala/client/character/prolog.pl"
  val ENGINE = ScalaProlog.mkPrologEngine(new Theory(new FileInputStream(FILE_NAME)))

  /**
    *
    * Extracts from the logic of the game implemented in prolog
    * the ghost's or pacman's number of lives
    *
    * @param characterName the name of character
    * @return number of lives of ghost
    **/
  override def getCharacterLives(characterName: String): Int = {
    var term: Term =  null
    if (characterName equals "ghost"){
     term = ScalaProlog.solveOneAndGetTerm(ENGINE, Term.createTerm("ghost_lives(X)"), "X")
    }else if (characterName equals "pacman"){
      term = ScalaProlog.solveOneAndGetTerm(ENGINE, Term.createTerm("pacman_lives(X)"), "X")
    }
    valueOf(term.toString)
  }

  /**
    * Extracts from the logic of the game implemented in prolog
    * the pacman's starting position
    *
    * @return the coordinate of pacman's starting position
    */
  override def getStartPosition(): Point[Int, Int] ={
    val term = ScalaProlog.solveOneAndGetTerm(ENGINE, Term.createTerm("pacman_initial_position(X,X)"), "X")
    val value: Int = valueOf(term.toString)
    Point[Int, Int](value, value)
  }

}