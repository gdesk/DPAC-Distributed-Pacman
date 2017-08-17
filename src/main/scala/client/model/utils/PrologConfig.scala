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
  val engine = new Prolog
  engine.setTheory(_theory)



def addStreets(streets: Theory): Unit ={
  engine.clearTheory()
  streets.append(_theory)
  engine.setTheory(streets)
}
  def getPrologEngine: Prolog = engine

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
    *
    * @param prologList
    * @return
    */
  def prologToScalaList( prologList: String): List[String] = {
    val list = prologList.replace("[","").replace("]","").replace(" ","")
    list.split(",").toList
  }

}
