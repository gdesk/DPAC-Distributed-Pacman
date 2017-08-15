package client.model.character

import java.io.FileInputStream

import alice.tuprolog.{Prolog, Theory}
import client.model.utils.ScalaProlog

/**
  * Created by lucch on 03/07/2017.
  */
object PrologConfig {

  private val FILE_NAME = "src/main/prolog/dpac-prolog.pl"
  private val _theory: Theory =  new Theory(new FileInputStream(FILE_NAME))

  val ENGINE = ScalaProlog.mkPrologEngine(_theory)

  def getPrologEngine: Prolog = ScalaProlog.getPrologEngine(_theory)

  def theory: Theory = _theory


}
