package client.utils

import alice.tuprolog._

/**
  * Manages the prolog interaction.
  *
  * @author Giulia Lucchi
  */
object ScalaProlog {

  def extractTerm(solveInfo:SolveInfo, i:Integer): Term =
    solveInfo.getSolution.asInstanceOf[Struct].getArg(i).getTerm

  def extractTerm(solveInfo:SolveInfo, s:String): Term =
    solveInfo.getTerm(s)

  def getPrologEngine(theory: Theory): Prolog = {
    val engine = new Prolog
    engine.setTheory(theory)
    engine
  }

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

  def multipleOutput(theory: Theory, goal: String) : SolveInfo = {
    val engine = new Prolog
    engine.setTheory(theory)
    engine.solve(goal)
  }

  def solveWithSuccess(engine: Term => Stream[SolveInfo], goal: Term): Boolean ={
    engine(goal).map(_.isSuccess).headOption == Some(true)
  }

  def scalaToPrologList(scalaList : List[String]): String = {
    var string : String  = "["
    /*var c=0
    scalaList.toStream.foreach(x =>
     if(c ==0){
       string = string.concat(x)
        c=1
     }else{
       string = string.concat(","+x)
     })*/
    scalaList.toStream.foreach(x =>
      string = string.concat(x+",")
      )
    string = string substring (0,string.size-2)
    string = string concat ("]")
    string
  }

  def prologToScalaList( prologList: String): List[String] = {
    val list = prologList.replace("[","").replace("]","").replace(" ","")
    list.split(",").toList
  }


  def solveOneAndGetTerm(engine: Term => Stream[SolveInfo], goal: Term, term: String): Term =
    engine(goal).headOption map (extractTerm(_,term)) get

  def solveAllAndGetTerm(engine: Term => Stream[SolveInfo], goal: Term): Stream[SolveInfo] = {
    engine(goal)
  }
}
