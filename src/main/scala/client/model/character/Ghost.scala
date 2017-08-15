package client.model.character

import java.lang.Integer.valueOf

import client.model._
import client.model.utils.{LivesImpl, PointImpl}

/**
  * Ghost in one of Characters.
  *
  * @author Giulia Lucchi
  * @author Margherita Pecorelli
  */
trait Ghost extends Character

case class BaseGhost(override val name: String) extends CharacterImpl(false) with Ghost {

  private val playground: Playground = PlaygroundImpl instance()
  private val game: Match = MatchImpl

  setPosition(PointImpl[Int, Int](20, 20))
  //setPosition(InitializedInfoImpl.getStartPosition("ghost"))

  override val lives = LivesImpl(InitializedInfoImpl.getCharacterLives("ghost"))

  /**
    * Manages the strategy of game, that is based on who is the killer and who is killable.
    *
    */
  override def checkAllPositions = {
    val ghosts = super.prologGhostsList
    val pacmans: List[Character] = game.allCharacters.filter(c => (c.isInstanceOf[Pacman]));
    if(isKillable) {
      pacmans.foreach(p => p.checkAllPositions)
    } else {
      pacmans.foreach { p =>
        val solveInfo = PrologConfig.getPrologEngine.solve(s"eat_pacman(pacman(${p.position.x},${p.position.y},${p.lives.remainingLives},${p.score.toString}), ${ghosts}, NL, GS, CG).")
        val killerGhost = game.allCharacters.find(c => c.name equals solveInfo.getTerm("CG").toString)
        if(killerGhost.nonEmpty && (killerGhost.get equals this)) {
          p.lives.remainingLives = valueOf(solveInfo.getTerm("NL").toString)
          if (p.lives.remainingLives == 0) p.isAlive = false
          score = valueOf(solveInfo.getTerm("GS").toString)
        }
      }
    }
  }

}

