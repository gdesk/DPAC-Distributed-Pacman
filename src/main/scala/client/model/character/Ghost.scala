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

case class BaseGhost(override val name: String) extends CharacterImpl(false) with Ghost {

  private val playground: Playground = PlaygroundImpl
  private val game: Match = MatchImpl

  setPosition(InitializedInfoImpl.getGhostStartPosition)

  override val lives = LivesImpl(InitializedInfoImpl.getCharacterLives("ghost"))

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
          val solveInfo = PrologConfig.getPrologEngine.solve(s"eat_pacman(pacman(${p.position.x},${p.position.y},${p.lives.remainingLives},${p.score.toString}), ${ghosts}, NL, GS, CG).")
          val killerGhost = game.allCharacters.find(c => c.name equals solveInfo.getTerm("CG").toString)
          if((killerGhost nonEmpty) && (killerGhost.get equals this)) {
            p.lives.remainingLives = valueOf(solveInfo.getTerm("NL").toString)
            if(p.lives.remainingLives == 0) p.isAlive = false
            score = valueOf(solveInfo.getTerm("GS").toString)
          }
        }
    }
  }

}

