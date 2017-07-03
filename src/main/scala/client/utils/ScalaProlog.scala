package client.utils

import alice.tuprolog._

/**
  * Created by Giulia Lucchi on 02/07/2017.
  */
object ScalaProlog {

  def extractTerm(solveInfo:SolveInfo, i:Integer): Term =
    solveInfo.getSolution.asInstanceOf[Struct].getArg(i).getTerm

  def extractTerm(solveInfo:SolveInfo, s:String): Term =
    solveInfo.getTerm(s)

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

  def solveWithSuccess(engine: Term => Stream[SolveInfo], goal: Term): Boolean =
    engine(goal).map(_.isSuccess).headOption == Some(true)

  def solveOneAndGetTerm(engine: Term => Stream[SolveInfo], goal: Term, term: String): Term =
    engine(goal).headOption map (extractTerm(_,term)) get

  def solveAllAndGetTerm(engine: Term => Stream[SolveInfo], goal: Term, term: String): Stream[SolveInfo] = {
    engine(goal)
  }

}
