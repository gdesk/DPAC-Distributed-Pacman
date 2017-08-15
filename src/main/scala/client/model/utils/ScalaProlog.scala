package client.model.utils

import alice.tuprolog._

/**
  * Manages the prolog interaction.
  *
  * @author Giulia Lucchi
  */
object ScalaProlog {

  /**
    * To extract the term in a result of goal.
    *
    * @param solveInfo result of goal
    * @param i index of term
    *
    * @return the term
    */
  def extractTerm(solveInfo:SolveInfo, i:Integer): Term =
    solveInfo.getSolution.asInstanceOf[Struct].getArg(i).getTerm

  /**
    * To extract the term in a result of goal.
    *
    * @param solveInfo result of goal
    * @param s string of the name of term
    * @return the term
    */
  def extractTerm(solveInfo:SolveInfo, s:String): Term =
    solveInfo.getTerm(s)

  /**
    * Return to Prolog object to execute the goal.
    * @param theory
    *
    * @return the prolog object
    */
  def getPrologEngine(theory: Theory): Prolog = {
    val engine = new Prolog
    engine.setTheory(theory)
    engine
  }

  /**
    * Create the Stream of SolveInfo with all the alternatives.
    * @param theory the theory of prolog
    *
    * @return the stream of result of goal
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

  /**
    * Return the multiple output of goal
    * @param theory
    * @param goal
    *
    * @return a SolveInfo as reasult of goal
    */
  def multipleOutput(theory: Theory, goal: String) : SolveInfo = {
    val engine = new Prolog
    engine.setTheory(theory)
    engine.solve(goal)
  }

  /**
    *
    * @param engine
    * @param goal
    * @return
    */
  def solveWithSuccess(engine: Term => Stream[SolveInfo], goal: Term): Boolean ={
    engine(goal).map(_.isSuccess).headOption == Some(true)
  }

  /**
    *
    * @param scalaList
    * @return
    */
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

  /**
    *
    * @param engine
    * @param goal
    * @param term
    * @return
    */
  def solveOneAndGetTerm(engine: Term => Stream[SolveInfo], goal: Term, term: String): Term =
    engine(goal).headOption map (extractTerm(_,term)) get

  /**
    *
    * @param engine
    * @param goal
    * @return
    */
  def solveAllAndGetTerm(engine: Term => Stream[SolveInfo], goal: Term): Stream[SolveInfo] = {
    engine(goal)
  }
}
