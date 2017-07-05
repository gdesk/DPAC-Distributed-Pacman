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

  test("Checking if ghost's score is properly incremented and his color when eating pacman two times" +
    "and then, verifying ghosts' winning"){

    var goal = "eat_pacman(pacman(1,1,2,_),[ghost(2,2,500,red),ghost(1,1,0,green),ghost(2,2,0,yellow)],NL1,NGS,C)"
    var risLives = solveOneAndGetTerm(engine, goal, "NL1").toString
    var risScore = solveOneAndGetTerm(engine, goal, "NGS").toString
    var risColor = solveOneAndGetTerm(engine, goal, "C").toString

    assert(risScore.equals("500"))
    assert(risColor.equals("green"))
    assert(risLives.equals("1"))

    goal = "eat_pacman(pacman(2,2,1,_),[ghost(2,2,500,red),ghost(1,1,0,green),ghost(2,2,0,yellow)],NL1,NGS,C)"
    risScore = solveOneAndGetTerm(engine, goal, "NGS").toString
    risColor = solveOneAndGetTerm(engine, goal, "C").toString

    assert(risScore.equals("1000"))
    assert(risColor.equals("red"))

    goal = "pacman_victory(pacman(_,_,L,_),[])"
    val isPacmanVictory = solveWithSuccess(engine, goal)

    assert(isPacmanVictory.equals(false))

  }

  test("Checking for right ghost's moves until he becomes vulnerable and dies when impacting pacman") {

    val t: Theory = new Theory("street(0,1). " +
      "street(0,0). " +
      "street(1,0). "
    )
    engine = modifyPrologEngine(t)

    var goal = "move(0,1,right,X,Y)"
    val isGhostMoving = solveWithSuccess(engine, goal)

    assert(isGhostMoving.equals(false))

    goal = "move(0,1,down,X,Y)"
    val risXnewPos = solveOneAndGetTerm(engine, goal, "X").toString
    var risYnewPos = solveOneAndGetTerm(engine, goal, "Y").toString

    assert(risXnewPos.equals("0"))
    assert(risYnewPos.equals("0"))

    goal = "ghost_defeat(pacman(1,1,_,0),[ghost(1,1,_,green),ghost(2,2,_,blue),ghost(3,3,_,red)],0,NPS,L1)"
    val risScore =solveOneAndGetTerm(engine, goal, "NPS").toString
    val risColor = solveOneAndGetTerm(engine, goal, "L1").toString

    assert(risScore.equals("200"))
    assert(risColor.equals("[green]"))
  }


}
