package client.model.character

import java.lang.Integer.valueOf

import client.model._
import client.model.gameElement.Eatable
import client.model.utils.{EatObjectStrategy, LivesImpl, Point, ScalaProlog}


/**
  * Manages the Pacman client.model.character.gameElement.character.
  *
  * @author Margherita Pecorelli
  * @author Giulia Lucchi
  */
trait Pacman extends Character {

  /**
    * This method checks if Pacman can eat some {@Eatable} object
    */
 def eatObject: Unit   //Option[Eatable]

}

case class BasePacman(override val name: String, val strategy: EatObjectStrategy) extends CharacterImpl(true) with Pacman {

  private val playground: Playground = PlaygroundImpl
  private val game: Match = MatchImpl

  setPosition(InitializedInfoImpl.getStartPosition("pacman"))

  override val lives = LivesImpl(InitializedInfoImpl.getCharacterLives("pacman"))

  /**
    * This method checks if Pacman can eat some {@Eatable} object
    */
  override def eatObject = {
    var eatables: String = "["
    playground.eatables.foreach(e =>
      eatables = eatables + "eatable_object(" + e.position.x + "," + e.position.y + "," + e.score + "," + "," + e.id + "),"
    )
    eatables = eatables.substring(0,(eatables size)-1)
    eatables = eatables + "]"

    val solveInfo = PrologConfig.getPrologEngine().solve(s"eat_object(pacman(${position x},${position y},${lives remainingLives},${score toString}), ${eatables}, NS, L, N).")
    val remainingEatableObjectsId: List[String] = ScalaProlog.prologToScalaList(solveInfo.getTerm("L").toString)
    val remainingEatableObjects: List[Eatable] = List()
    remainingEatableObjectsId.foreach(r => playground.eatables.find(e => e.id equals r).get :: remainingEatableObjects)
    val eatenObj: List[Eatable] = playground.eatables.diff(remainingEatableObjects)
    if(eatenObj nonEmpty) {
      playground.removeEatable(eatenObj.head)
      score = valueOf(solveInfo.getTerm("NS").toString)
      val eatenObjectName = solveInfo.getTerm("N").toString
      val eatenObject = playground.eatables.find(_.id equals eatenObjectName).get
      strategy.eat(eatenObject)
    }
  }

  /**
    * Manage the the strategy of game, that is based on who the killer is and who the killable
    */
  override def checkAllPositions = {
    val ghosts = super.prologGhostsList
    isKillable match {
      case true =>
        game.allCharacters.filter(c => !(c.isInstanceOf[Pacman])).foreach(g => g.checkAllPositions)
      case false =>
        val numberOfGhostAlreadyEaten: Int = game.deadCharacters.size
        val solveInfo = PrologConfig.getPrologEngine.solve(s"ghost_defeat(pacman(${position x},${position y},${lives remainingLives},${score toString}), ${ghosts}, ${numberOfGhostAlreadyEaten}, PS, EG).")
        score = valueOf(solveInfo.getTerm("PS").toString)
        val eatenGhost: List[String] = ScalaProlog.prologToScalaList(solveInfo.getTerm("EG").toString)
        if(eatenGhost nonEmpty) {
          eatenGhost.foreach{g =>
            val ghost = game.allCharacters.find(c => c.name equals g).get
            game.addDeadCharacters(ghost)
            ghost.lives.decrement
            if(ghost.lives.remainingLives equals 0) ghost.isAlive = false
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
