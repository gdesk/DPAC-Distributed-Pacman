package client.model.utils

import java.io.FileInputStream

import alice.tuprolog.{Prolog, Theory}

/**
  * Created by lucch on 03/07/2017.
  */
//todo: scala doc da fare
object PrologConfig {

  private val FILE_NAME = "src/main/prolog/dpac-prolog.pl"
  private val theory: Theory =  new Theory(new FileInputStream(FILE_NAME))

  val ENGINE = ScalaProlog.mkPrologEngine(theory)

  def getPrologEngine(): Prolog = ScalaProlog.getPrologEngine(theory)


}
