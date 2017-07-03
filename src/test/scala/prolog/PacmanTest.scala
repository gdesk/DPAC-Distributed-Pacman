import java.io.FileInputStream

import alice.tuprolog.{Prolog, SolveInfo, Term, Theory}
import org.scalatest.FunSuite

/**
  * Created by Federica on 01/07/17.
  */
class PacmanTest extends FunSuite{

  implicit def stringToTerm(s: String): Term = Term.createTerm(s)
  implicit def listToTerm[T](l: List[T]): Term = l.mkString("[",",","]")

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

  /**
    * method to link prolog with scala
    * @param theory
    * @return
    */
  def mkPrologEngine(theory: Theory): Term => Stream[SolveInfo] = {
    val engine = new Prolog
    engine.setTheory(theory)

    goal => new Iterable[SolveInfo]{

      override def iterator = new Iterator[SolveInfo]{
        var solution: Option[SolveInfo] = Some(engine.solve(goal))

        override def hasNext = solution.isDefined &&
          (solution.get.isSuccess || solution.get.hasOpenAlternatives)

        override def next() =
          try solution.get
          finally solution = if (solution.get.hasOpenAlternatives) Some(engine.solveNext()) else None
      }
    }.toStream
  }

  def extractTerm(solveInfo:SolveInfo, s:String): Term =
    solveInfo.getTerm(s)

  def solveOneAndGetTerm(engine: Term => Stream[SolveInfo], goal: Term, term: String): Term =
    engine(goal).headOption map (extractTerm(_,term)) get

  def solveWithSuccess(engine: Term => Stream[SolveInfo], goal: Term): Boolean =
    engine(goal).map(_.isSuccess).headOption == Some(true)

  private val engine = mkPrologEngine(new Theory(new FileInputStream("src/main/prolog/dpac-prolog.pl")))


}
