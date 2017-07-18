package client.model.character

import java.lang.Integer.valueOf

import client.model._
import client.model.utils.{PointImpl, ScalaProlog}

/**
  * Ghost in one of Characters.
  *
  * @author Giulia Lucchi
  * @author Margherita Pecorelli
  */
trait Ghost extends Character

case class BaseGhost(override val name: String) extends CharacterImpl(false) with Ghost {

  private val playground: Playground = PlaygroundImpl instance()
  private val game: Match = MatchImpl instance()

  setPosition(PointImpl[Int, Int](20, 20))
  //setPosition(InitializedInfoImpl.getStartPosition("ghost"))

  override val lives = LivesImpl(InitializedInfoImpl.getCharacterLives("ghost"))

  /**
    * Manages the client.model.character.gameElement.character's movement.
    *
    * @param direction - client.model.character.gameElement.character's of direction
    */
  override def go(direction: Direction) = super.go(direction)

  /**
    * Manages the strategy of game, that is based on who is the killer and who is killable.
    *
    */
  override def checkAllPositions() = {
    val ghosts = super.prologGhostsList
    val pac: List[Character] = game.characters filter (c => (c.isInstanceOf[Pacman]))
    val pacman = pac isEmpty match {
      case true =>
        game.myCharacter.asInstanceOf[Pacman]
      case false =>
        pac.head.asInstanceOf[Pacman]
    }
    if(isKillable) {
      pacman.checkAllPositions
    } else {
      val solveInfo = PrologConfig.getPrologEngine() solve (s"eat_pacman(pacman(${pacman.position x},${pacman.position y},${pacman.lives remainingLives},${pacman.score.toString}), ${ghosts}, NL, GS, CG).")
      val killerGhost = (game characters) find (c => c.name equals (solveInfo getTerm ("CG") toString))
      val killer: Ghost = killerGhost isEmpty match {
        case true =>
          game.myCharacter.asInstanceOf[Ghost]
        case false =>
          killerGhost.get.asInstanceOf[Ghost]
      }
      if(killer equals this) {
        pacman.lives remainingLives = valueOf(solveInfo getTerm ("NL") toString)
        if(pacman.lives.remainingLives == 0) pacman isAlive = false
        score = valueOf(solveInfo getTerm ("GS") toString)
      }
    }
  }

}

