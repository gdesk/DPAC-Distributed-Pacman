package client.model.character

import java.lang.Integer.valueOf

import client.model._
import client.model.gameElement.Eatable
import client.model.utils.{Point, ScalaProlog}


/**
  * Manages the Pacman client.model.character.gameElement.character.
  *
  * @author Margherita Pecorelli
  * @author Giulia Lucchi
  */
trait Pacman extends Character[Int, Int] {
  /**
    * This method checks if Pacman can eat some {@Eatable} object
    */
 def eatObject: Unit   //Option[Eatable]

}

case class BasePacman(override val name: String, val strategy: EatObjectStrategy) extends CharacterImpl(true) with Pacman {

  private val playground: Playground = PlaygroundImpl instance()
  private val game: Match = MatchImpl instance()

  setPosition(InitializedInfoImpl.getStartPosition("pacman"))
  override val lives = LivesImpl(InitializedInfoImpl.getCharacterLives("pacman"))

  /**
    * This method checks if Pacman can eat some {@Eatable} object
    */
  override def eatObject = {
    var eatables: String = "["
    playground.eatables foreach(e =>
      eatables = eatables + "eatable_object(" + e.position.x + "," + e.position.y + "," + e.score + "," + e.belonginFamily + "," + e.id + "),"
    )
    eatables = eatables substring (0,(eatables size)-2)
    eatables = eatables + "]"

    val solveInfo = PrologConfig.getPrologEngine().solve(s"eat_object(pacman(${position x},${position y},${lives remainingLives},${score toString}), ${eatables}, NS, L, N).")
    val remainingEatableObjectsId: List[String] = ScalaProlog prologToScalaList((solveInfo getTerm("L")) toString)
    val remainingEatableObjects: List[Eatable] = List()
    remainingEatableObjectsId.foreach(r => (playground.eatables.find(e => e.id.equals(r)) get) :: remainingEatableObjects)
    val eatenObj: List[Eatable] = playground.eatables.diff(remainingEatableObjects)
    if(!eatenObj.isEmpty) {
      //val eatenObject: Eatable =
      playground.removeEatable(eatenObj.head) //eatenObject
      score = valueOf(solveInfo getTerm ("NS") toString)
      val eatenObjectFamily = (solveInfo getTerm("N") toString)
      strategy.eat(eatenObjectFamily)
      //Option.apply(eatenObject)
    } /*else {
      Option.empty
    }*/
  }

  /**
    * Manage the the strategy of game, that is based on who the killer is and who the killable
    */
  override def checkAllPositions() = {
    var ghosts: String = "["
    (game characters) filter (c => !(c.isInstanceOf[Pacman])) foreach(e =>
      ghosts = ghosts + "ghost(" + e.position.x + "," + e.position.y + "," + e.score + "," + e.name + "),"
    )
    ghosts = ghosts substring (0,(ghosts size)-2)
    ghosts = ghosts + "]"

    isKillable match {
      case true =>
        (game characters) filter (c => !(c.isInstanceOf[Pacman])) foreach (g => g.checkAllPositions())
        /*val solveInfo = PrologConfig.getPrologEngine().solve(s"eat_pacman(pacman(${position x},${position y},${lives remainingLives},${score toString}), ${ghosts}, NL, GS, CG).")
        lives remainingLives = valueOf(solveInfo getTerm ("NL") toString)
        if((lives remainingLives) == 0) isAlive = false
        val assassinGhost = ((game characters) find (c => c.name equals (solveInfo getTerm ("CG") toString)) get)
        assassinGhost score = valueOf(solveInfo getTerm ("GS") toString)*/
      case false =>
        val numberOfGhostAlreadyEaten: Int = (game deadCharacters) size
        val solveInfo = PrologConfig.getPrologEngine().solve(s"ghost_defeat(pacman(${position x},${position y},${lives remainingLives},${score toString}), ${ghosts}, ${numberOfGhostAlreadyEaten}, PS, EG).")
        score = valueOf(solveInfo getTerm ("PS") toString)
        val eatenGhost: List[String] = ScalaProlog.prologToScalaList(solveInfo getTerm ("EG") toString)
        if(!(eatenGhost isEmpty)) {
          var ghost: BaseGhost = null
          eatenGhost foreach { g =>
            ghost = ((game characters) find (c => c.name equals g) get).asInstanceOf[BaseGhost]
            game addDeadCharacters ghost
            ghost.lives decrement;
            if(ghost.lives.remainingLives.equals(0)) {ghost isAlive = false}
          }
        }
    }

  }

  /**
    * Manages the client.model.character.gameElement.character's movement.
    *
    * @param direction - client.model.character.gameElement.character's direction
    */
  override def go(direction: Direction) = {
    val prePosition: Point[Int, Int] = position
    super.go(direction)
    val pos: Point[Int, Int] = position
    pos match {
      case x if !(x equals prePosition) => eatObject
      case _ =>
    }
  }
}
