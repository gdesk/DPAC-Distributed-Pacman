package prolog

import java.io.FileInputStream

import alice.tuprolog.Theory
import org.scalatest.FunSuite
import TestUtility._

/**
  * Created by Federica on 03/07/17.
  */
class GhostTest extends FunSuite{

  private var engine = mkPrologEngine(new Theory(new FileInputStream("src/main/prolog/dpac-prolog.pl")))
  private var goal = None: Option[String]
  private var map = scala.collection.mutable.Map.empty[String,String]

  test("Checking if ghost's score is properly incremented and his color when eating pacman two times" +
    "and then, verifying ghosts' winning"){

    map = scala.collection.mutable.Map("NL1" -> "1", "NGS" -> "500", "C" -> "green")
    goal = Some("eat_pacman(pacman(1,1,2,_),[ghost(2,2,500,red),ghost(1,1,0,green),ghost(2,2,0,yellow)],NL1,NGS,C)")
    for((k,v) <- map){
      val ris = solveOneAndGetTerm(engine, goal.get, k).toString
      assert(ris.equals(v))
    }

    map = scala.collection.mutable.Map("NGS" -> "1000", "C" -> "red")
    goal = Some("eat_pacman(pacman(2,2,1,_),[ghost(2,2,500,red),ghost(1,1,0,green),ghost(2,2,0,yellow)],NL1,NGS,C)")
    for((k,v) <- map){
      val ris = solveOneAndGetTerm(engine, goal.get, k).toString
      assert(ris.equals(v))
    }

    goal = Some("pacman_victory(pacman(_,_,1,_),[ghost(2,2,500,red),ghost(1,1,0,green),ghost(2,2,0,yellow)])")
    val isPacmanVictory = solveWithSuccess(engine, goal.get)

    assert(isPacmanVictory.equals(false))

  }

  test("Checking for right ghost's moves until he becomes vulnerable and dies when impacting pacman") {

    val theory = "street(0,1). " +
      "street(0,0). " +
      "street(1,0). "

    engine = modifyPrologEngine(theory)

    goal = Some("move(0,1,right,X,Y)")
    val isGhostMoving = solveWithSuccess(engine, goal.get)

    assert(isGhostMoving.equals(false))

    map = scala.collection.mutable.Map("X" -> "0", "Y" -> "0")
    goal = Some("move(0,1,down,X,Y)")
    for((k,v) <- map) {
      val ris = solveOneAndGetTerm(engine, goal.get, k).toString
      assert(ris.equals(v))
    }

    map = scala.collection.mutable.Map("NPS" -> "200", "L1" -> "[green]")
    goal = Some("ghost_defeat(pacman(1,1,_,0),[ghost(1,1,_,green),ghost(2,2,_,blue),ghost(3,3,_,red)],0,NPS,L1)")
    for((k,v) <- map) {
      val ris = solveOneAndGetTerm(engine, goal.get, k).toString
      assert(ris.equals(v))
    }

  }


}
