package prolog

import java.io.FileInputStream

import alice.tuprolog.Theory
import org.scalatest.FunSuite
import TestUtility._

/**
  * Created by Federica on 03/07/17.
  */
class GhostTest extends FunSuite{

  private var engine = mkPrologEngine(new Theory(new FileInputStream("src/main/prolog/logic.pl")))
  private var goal = None: Option[String]
  private var map = scala.collection.mutable.Map.empty[String,String]

  test("Checking if ghost's score is properly incremented and ghost's color when eating pacman"){
    map = scala.collection.mutable.Map("NL1" -> "1", "NGS" -> "500", "GN" -> "Blinky")
    goal = Some("eat_pacman(pacman(1,1,2,_),[ghost(2,2,500,Inky),ghost(1,1,0,Blinky),ghost(2,2,0,Pinky)],NL1,NGS,GN)")
    for((k,v) <- map){
      val ris = solveOneAndGetTerm(engine, goal.get, k).toString
      assert(ris.equals(v))
    }

  }

  test("Ghosts winning match") {
    goal = Some("pacman_victory(pacman(_,_,1,_),[ghost(2,2,500,Inky),ghost(1,1,0,Pinky),ghost(2,2,0,Blinky)])")
    val isPacmanVictory = solveWithSuccess(engine, goal.get)

    assert(isPacmanVictory.equals(false))

  }

  test("Testing that a ghost walks on,ly along streets\")") {
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

  }

  test("Ghost looosing match") {
    map = scala.collection.mutable.Map("NPS" -> "200", "L1" -> "[Blinky]")
    goal = Some("ghost_defeat(pacman(1,1,_,0),[ghost(1,1,_,Blinky),ghost(2,2,_,Inky),ghost(3,3,_,Pinky)],0,NPS,L1)")
    for ((k, v) <- map) {
      val ris = solveOneAndGetTerm(engine, goal.get, k).toString
      assert(ris.equals(v))
    }

  }


}
