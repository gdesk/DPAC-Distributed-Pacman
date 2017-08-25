package prolog
import java.io.FileInputStream

import org.scalatest.FunSuite
import client.utils.PrologUtility._
import alice.tuprolog.Theory

/**
  * Created by Federica on 01/07/17.
  *
  * a class to test prolog theory concerning pacman behaviour
  */
class PacmanTest extends FunSuite {

  private var engine = mkPrologEngine(new Theory(new FileInputStream("src/main/resources/prolog/logic.pl")))
  private var goal = None: Option[String]
  private var map = scala.collection.mutable.Map.empty[String,String]

  test("Pacman initial number of lives is 3") {
    goal = Some("pacman_lives(L)")
    val ris = solveOneAndGetTerm(engine, goal.get, "L").toString

    assert(ris.equals("3"))
  }

  test("Pacman loosing match") {
    var risXnewPos, risYnewPos: String = "30"
    goal = Some("eat_pacman(pacman(" + risXnewPos + "," + risYnewPos + ",1,_), [ghost(30,30,100,Inky),ghost(31,31,200,Blinky),ghost(32,32,300,Pinky)],NL1,_,_)")
    var risEat = solveOneAndGetTerm(engine, goal.get, "NL1").toString

    println("risEat " + risEat)
    assert(risEat.equals("0"))
    //var i: Int = 0

    //goal = Some("pacman_death(pacman(_,_," + risEat + ",_))")
    //var risDeath = solveWithSuccess(engine, goal.get)
    //assert(risDeath.equals(true))

  }

  test("Testing Pacman walking only along streets") {
    val theory = "street(1,0). " +
      "street(1,1). " +
      "street(0,1). "

    engine = modifyPrologEngine(theory)

    map = scala.collection.mutable.Map("X" -> "1", "Y" -> "1")
    goal = Some("move(0,1,right,X,Y)")

    for((k,v) <- map) {
      val ris = solveOneAndGetTerm(engine, goal.get, k).toString
      assert(ris.equals(v))
    }

  }

  test("Testing Pacman standing still if a block is present") {
    val theory = "street(1,0). " +
      "street(1,1). " +
      "street(0,1). "

    engine = modifyPrologEngine(theory)

    goal = Some("move(0,1,down,X,Y)")
    val risnewPos = solveWithSuccess(engine, goal.get)

    assert(risnewPos.equals(false))

  }

  test("Pacman has been eaten by a ghost and remained with one last life") {
    map = scala.collection.mutable.Map("NL1" -> "1", "NGS" -> "500", "C" -> "Inky")
    goal = Some("eat_pacman(pacman(1,1,2,_),[ghost(2,2,500,Blinky),ghost(1,1,0,Inky),ghost(2,2,0,Pinky)],NL1,NGS,C)")

    for((k,v) <- map) {
      val ris = solveOneAndGetTerm(engine, goal.get, k).toString
      assert(ris.equals(v))
    }

  }

  test("Pacman eating the only eatable object left in list") {
    map = scala.collection.mutable.Map("NS" -> "2100", "N" -> "cherry")
    goal = Some("eat_object(pacman(1,1,_,2000),[eatable_object(1,1,100,cherry)],NS,N)")

    for((k,v) <- map) {
      val ris = solveOneAndGetTerm(engine, goal.get, k).toString
      assert(ris.equals(v))
    }

  }

  test("Pacman winning match") {
    goal = Some("pacman_victory(pacman(_,_,1,_),[])")
    val risVictory = solveWithSuccess(engine, goal.get)
    assert(risVictory.equals(true))

  }

}
