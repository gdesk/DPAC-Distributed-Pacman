package client.model.character

import java.lang.Integer.valueOf

import client.model._
import client.model.utils.{LivesImpl, PrologConfig}

/**
  * Represents all ghosts' behaviors.
  *
  * @author Giulia Lucchi
  * @author Margherita Pecorelli
  */
trait Ghost extends Character {}

/**
  * Represents the implementation of all ghosts' behaviors.
  *
  * @author Giulia Lucchi
  * @author Margherita Pecorelli
  */
case class BaseGhost(override val name: String) extends CharacterImpl(false, LivesImpl(InitializedInfoImpl.getCharacterLives("ghost"))) with Ghost {

  private val playground: Playground = PlaygroundImpl
  private val game: Match = MatchImpl
  private var _won = false

  setPosition(InitializedInfoImpl.getGhostStartPosition)

  /**
    * Checks if the character can eat another character or if it can be eaten by another character.
    */
  override def checkAllPositions = {
    val ghosts = super.prologGhostsList
    val pacmans: List[Character] = game.allCharacters.filter(c => (c.isInstanceOf[Pacman]))
    isKillable match {
      case true =>
        pacmans.foreach(p => p.checkAllPositions)
      case _ =>
        pacmans.foreach{ p =>
          val solveInfo = PrologConfig.getPrologEngine.solve(s"eat_pacman(pacman(${p.position.x},${p.position.y},${p.lives.remainingLives},${p.score}),${ghosts},NL,GS,CG).")
          val killerGhost = game.allCharacters.find(c => c.name equals solveInfo.getTerm("CG").toString)
          if((killerGhost nonEmpty) && (killerGhost.get equals this)) {
            p.lives.remainingLives = valueOf(solveInfo.getTerm("NL").toString)
            if(p.lives.remainingLives == 0) p.isAlive = false
            score = valueOf(solveInfo.getTerm("GS").toString)
          }
          _won = PrologConfig.getPrologEngine.solve(s"ghosts_victory(pacman(${p.position.x},${p.position.y},${p.lives.remainingLives},${p.score})).").isSuccess && !hasLost
      }
    }
  }
  
  /**
    * Returns if the character won.
    *
    * @return true if the character won, false otherwise.
    */
  def won: Boolean = _won

}

