package client.model.utils

import java.io.{FileInputStream, InputStream}

import alice.tuprolog.{Prolog, Theory}
import client.view.Utils

/**
  * Manages the interfacing with the logic of the game, written in prolog.
  *
  * @author Giulia Lucchi
  */
object PrologConfig {
  private val FILE_NAME = "/prolog/logic.pl"
  val is: InputStream = this.getClass.getResourceAsStream(FILE_NAME)

  private val _theory: Theory =  new Theory(is)

  val engine = new Prolog
  engine.setTheory(_theory)

  /**
    * Used to execute the goal on the prolog theory.
    *
    * @return the prolog object already set with the theory
    */
  def getPrologEngine: Prolog = engine

  /**
    * Adds the theory in prolog to create a block of playground's street.
    *
    * @param streets theory to add in game's logic
    */
  def addStreets(streets: Theory): Unit ={
  engine.clearTheory()
  streets.append(_theory)
  engine.setTheory(streets)
}

  /**
    * Convert the scala list to prolog list.
    * @param scalaList list written in scala
    *
    * @return list in prolog, as a string to pass as param in the goal.
    */
  def scalaToPrologList(scalaList : List[String]): String = {
    var string : String  = "["
    scalaList.toStream.foreach(x =>
      string = string.concat(x+",")
    )
    string = string substring (0,string.size-2)
    string = string concat ("]")
    string
  }

  /**
    * Convert the prolog list to scala list.
    *
    * @param prologList list written in prolog
    * @return list written in scala.
    */
  def prologToScalaList( prologList: String): List[String] = {
    val list = prologList.replace("[","").replace("]","").replace(" ","")
    if(list.size == 0) {
      List.empty
    } else {
      list.split(",").toList
    }
  }

}
