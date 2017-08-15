package client.model.utils

import java.io.FileInputStream

import alice.tuprolog.{Prolog, Theory}

/**
  * Add the the unique theory of prolog from file.
  *
  * @author Giulia Lucchi
  */
object PrologConfig {
  private val FILE_NAME = "src/main/prolog/dpac-prolog.pl"
  private val _theory: Theory =  new Theory(new FileInputStream(FILE_NAME))

  val ENGINE = ScalaProlog.mkPrologEngine(_theory)


  def getPrologEngine: Prolog = ScalaProlog.getPrologEngine(_theory)
  
  def theory: Theory = _theory

}
