package client.character

import java.io.FileInputStream

import alice.tuprolog.{Prolog, Theory}
import client.utils.ScalaProlog

/**
  * Created by lucch on 03/07/2017.
  */
object PrologConfig {

  private val FILE_NAME = "src/main/scala/client/character/prolog.pl"
  private val theory: Theory =  new Theory(new FileInputStream(FILE_NAME))

  val ENGINE = ScalaProlog.mkPrologEngine(theory)

  def getPrologEngine(): Prolog = ScalaProlog.getPrologEngine(theory)


}
