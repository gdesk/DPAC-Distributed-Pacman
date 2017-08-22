package client.model.character

import java.lang.Integer.valueOf

import client.model._
import client.model.gameElement.Eatable
import client.model.utils._

/**
  * Represents all pacman' behaviors.
  *
  * @author Giulia Lucchi
  * @author Margherita Pecorelli
  */
trait Pacman extends Character {

  /**
    * Checks if Pacman can eat some eatable object.
    */
  def eatObject: Unit

}

/**
  * Represents the implementation of all pacman' behaviors.
  *
  * @author Giulia Lucchi
  * @author Margherita Pecorelli
  */
case class BasePacman(override val name: String, val strategy: EatObjectStrategy) extends CharacterImpl(true, LivesImpl(InitializedInfoImpl.getCharacterLives("pacman"))) with Pacman {

  private val playground: Playground = PlaygroundImpl
  private val game: Match = MatchImpl
  private var _won = false
  private var _isAlive = true

  setPosition(InitializedInfoImpl.getPacmanStartPosition)

  /**
    * Checks if Pacman can eat some eatable object.
    */
  override def eatObject = {
    val eatables = prologEatablesList
    val solveInfo = PrologConfig.getPrologEngine.solve(s"eat_object(pacman(${position.x},${position.y},${lives.remainingLives},${score}),${eatables},NS,N).")
    val eatenObjId: String = solveInfo.getTerm("N").toString
    /*
    val remainingEatableObjectsId: List[String] = ScalaProlog.prologToScalaList(solveInfo.getTerm("L").toString)
    val remainingEatableObjects: List[Eatable] = List.empty
    remainingEatableObjectsId.foreach(r => playground.eatables.find(e => e.id equals r).get :: remainingEatableObjects)
    val eatenObjects: List[Eatable] = playground.eatables.diff(remainingEatableObjects)
    if(eatenObjects nonEmpty) {
      playground.removeEatable(eatenObjects.head)
      score = valueOf(solveInfo.getTerm("NS").toString)
      strategy.eat(eatenObjects.head)
    }
    */
    if(eatenObjId != "''") {
      val eatenObj: Eatable = playground.eatables.filter(e => e.id == eatenObjId).head
      playground.removeEatable(eatenObj)
      score = valueOf(solveInfo.getTerm("NS").toString)
      strategy.eat(eatenObj)
    }
  }

  /**
    * Manages character's movement and consequently the contact with other item of the game.
    *
    * @param direction - direction of character's movement.
    */
  override def go(direction: Direction) = {
    val prePosition: Point[Int, Int] = position
    super.go(direction)
    val postPosition: Point[Int, Int] = position
    if(!(prePosition equals  postPosition)){
      eatObject
      won
    }
  }

  /**
    * Checks if the character can eat another character or if it can be eaten by another character.
    */
  override def checkAllPositions = {
    val ghosts = super.prologGhostsList
    isKillable match {
      case true =>
        game.allCharacters.filter(c => !(c.isInstanceOf[Pacman])).foreach(g => g.checkAllPositions)
      case _ =>
        val numberOfGhostAlreadyEaten = game.deadCharacters.size
        val solveInfo = PrologConfig.getPrologEngine.solve(s"ghost_defeat(pacman(${position.x},${position.y},${lives.remainingLives},${score}),${ghosts},${numberOfGhostAlreadyEaten},PS,EG).")
        score = valueOf(solveInfo.getTerm("PS").toString)
        val eatenGhost: List[String] = PrologConfig.prologToScalaList(solveInfo.getTerm("EG").toString)
        if(eatenGhost nonEmpty) {
          eatenGhost.foreach{ g =>
            val ghost = game.allCharacters.find(c => c.name equals g).get
            game.addDeadCharacters(ghost)
            ghost.lives.decrement
            if(ghost.lives.remainingLives equals 0) ghost.isAlive = false
          }
        }
    }
  }

  /**
    * Returns if the character won.
    *
    * @return true if the character won, false otherwise.
    */
  override def won = {
    val dots = prologDotsList
    _won = PrologConfig.getPrologEngine.solve(s"pacman_victory(pacman(${position.x},${position.y},${lives.remainingLives},${score}),${dots}).").isSuccess && !hasLost
    if(_won) MatchImpl.allCharacters.filter(c => !c.isInstanceOf[Pacman]).foreach(g => g.hasLost = true)
    _won
  }

  /**
    * Returns if character is alive.
    *
    * @return true if character is alive, false otherwise.
    */
  override def isAlive = {
    if(lives.remainingLives <= 0) _isAlive = false
    if(hasLost) MatchImpl.allCharacters.filter(c => !c.isInstanceOf[Pacman]).foreach(g => g.won = true)
    _isAlive
  }

  /**
    * Sets if charater is alive.
    *
    * @param alive - true if character is alive, false otherwise.
    */
  override def isAlive_=(alive: Boolean) = {
    _isAlive = alive
    if(!_isAlive) {
      hasLost = true
      MatchImpl.allCharacters.filter(c => !c.isInstanceOf[Pacman]).foreach(g => g.won = true)
      //println("GAME OVER!")
    }
  }

  /**
    * Returns a string representing the prolog eatable objects list containing all match's eatable objects. It can be pass as Term in prolog.
    *
    * @return a string representing the prolog eatable objects list containing all match's eatable objects.
    */
  private def prologEatablesList: String = {
    var eatables: String = "["
    playground.eatables.foreach{ e =>
      eatables = eatables + "eatable_object(" + e.position.x + "," + e.position.y + "," + e.score + "," + e.id + "),"
    }
    eatables.size match {
      case 1 =>
        eatables = eatables + "]"
      case _ =>
        eatables = eatables.substring(0,eatables.size-1)
        eatables = eatables + "]"
    }
    eatables
  }

  /**
    * Returns a string representing the prolog dots list containing all match's dots. It can be pass as Term in prolog.
    *
    * @return a string representing the prolog dots list containing all match's dots.
    */
  private def prologDotsList: String = {
    var dots: String = "["
    playground.eatables.filter(x => x.id.contains("dot")).foreach{ e =>
      dots = dots + "eatable_object(" + e.position.x + "," + e.position.y + "," + e.score + "," + e.id + "),"
    }
    dots.size match {
      case 1 =>
        dots = dots + "]"
      case _ =>
        dots = dots.substring(0,dots.size-1)
        dots = dots + "]"
    }
    dots
  }

}
