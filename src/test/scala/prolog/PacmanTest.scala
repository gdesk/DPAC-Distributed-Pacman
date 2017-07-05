package prolog
import java.io.FileInputStream

import org.scalatest.FunSuite
import TestUtility._
import alice.tuprolog.Theory

/**
  * Created by Federica on 01/07/17.
  */
class PacmanTest extends FunSuite {

  private var engine = mkPrologEngine(new Theory(new FileInputStream("src/main/prolog/dpac-prolog.pl")))


  test("Pacman initial number of lives is 3") {
    val goal = "pacman_lives(L)"
    val ris = solveOneAndGetTerm(engine, goal, "L").toString

    assert(ris.equals("3"))
  }

  test("Pacman loosing match") {
    var PX, PY: String = "30"
    var goal = "eat_pacman(pacman(" + PX + "," + PY + ",3,_), [ghost(30,30,100,red),ghost(31,31,200,blue),ghost(32,32,300,green)],NL1,_,_)"
    var risDeath: Boolean = false
    var risEat = solveOneAndGetTerm(engine, goal, "NL1")
    var i: Int = 0

    for (i <- 1 to 2) {
      PX = String.valueOf(Integer.valueOf(PX) + 1)
      PY = String.valueOf(Integer.valueOf(PY) + 1)
      goal = "eat_pacman(pacman(" + PX + "," + PY + ", " + risEat + ",_), [ghost(30,30,100,red),ghost(31,31,200,blue),ghost(32,32,300,green)],NL1,_,_)"
      risEat = solveOneAndGetTerm(engine, goal, "NL1")
    }

    goal = "pacman_death(pacman(_,_," + risEat + ",_))"
    risDeath = solveWithSuccess(engine, goal)
    assert(risDeath.equals(true))

  }

  test("Testing Pacman walking only along streets") {
    val t: Theory = new Theory("street(1,0). " +
      "street(1,1). " +
      "street(0,1). "
    )

    engine = modifyPrologEngine(t)

    var goal = "move(0,1,right,X,Y)"
    var risXnewPos = solveOneAndGetTerm(engine, goal, "X").toString
    var risYnewPos = solveOneAndGetTerm(engine, goal, "Y").toString

    assert(risXnewPos.equals("1") && risYnewPos.equals("1"))

  }

   test("Testing Pacman standing still if a block is present") {

     val t: Theory = new Theory("street(1,0). " +
       "street(1,1). " +
       "street(0,1). "
     )

     engine = modifyPrologEngine(t)

     var goal = "move(0,1,down,X,Y)"
     var risnewPos = solveWithSuccess(engine, goal)

     assert(risnewPos.equals(false))

   }

  test("Checking the three following pacman's actions:" +
    "- being eaten by ghost and remaining with one last life" +
    "- eating the only eatable object left in list" +
    "- match winning") {

    var goal = "eat_pacman(pacman(1,1,2,_),[ghost(2,2,500,red),ghost(1,1,0,green),ghost(2,2,0,yellow)],NL1,NGS,C)"
    val risLives = solveOneAndGetTerm(engine, goal, "NL1").toString
    var risScore = solveOneAndGetTerm(engine, goal, "NGS").toString
    val risColor = solveOneAndGetTerm(engine, goal, "C").toString

    assert(risScore.equals("500"))
    assert(risColor.equals("green"))

    assert(risLives.equals("1"))
    goal = "eat_object(pacman(1,1,_,2000),[eatable_object(1,1,100,cherry)],NS,T,N)"
    risScore = solveOneAndGetTerm(engine, goal, "NS").toString
    val risObjects = solveOneAndGetTerm(engine, goal, "T").toString
    val risName = solveOneAndGetTerm(engine, goal, "N").toString

    assert(risScore.equals("2100"))
    assert(risObjects.equals("[]"))
    assert(risName.equals("cherry"))

    goal = "pacman_victory(pacman(_,_,"+risLives+",_),[])"
    val risVictory = solveWithSuccess(engine, goal)
    assert(risVictory.equals(true))

  }
}
