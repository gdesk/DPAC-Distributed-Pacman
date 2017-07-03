package prolog

import java.io.FileInputStream

import alice.tuprolog.{Prolog, SolveInfo, Term, Theory}

/**
  * Created by Federica on 03/07/17.
  */
object TestUtility {

  implicit def stringToTerm(s: String): Term = Term.createTerm(s)
  implicit def listToTerm[T](l: List[T]): Term = l.mkString("[",",","]")

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

 

}
