package client.character

import java.lang.Integer.valueOf

import character.Direction
import client.gameElement.CharactersNames
import client.utils.{PointImpl, ScalaProlog}

/**
  * Ghost in one of Characters.
  *
  * @author Giulia Lucchi
  */

case class GhostImpl(override val name: String) extends CharacterImpl(false) {

  setPosition(PointImpl[Int, Int](20, 20))
  //setPosition(InitializedInfoImpl.getStartPosition(ghostName.getName))
  override val lives = LivesImpl(InitializedInfoImpl.getCharacterLives("ghost"))

  /**
    * Manages the character's movement.
    *
    * @param direction - character's of direction
    */
  override def go(direction: Direction): Unit = super.go(direction)

  /**
    * Manages the strategy of game, that is based on who is the killer and who is killable.
    *
    */
  override def checkAllPositions(): Unit = {
    /*solo per capire se funziona poi lo importeremo da scalaprolog e dovrò fare the convert ghost scala to ghost prolog*/
    val blueGhost = GhostImpl("blueGhost")
    val redGhost = GhostImpl("redGhost")
    val yellowGhost = GhostImpl("yellowGhost")
    val ghostList: List[String] = List(s" ghost(${blueGhost.position x}, ${blueGhost.position y}, ${blueGhost.score}, ${blueGhost.name})", s"ghost(${redGhost.position x}, ${redGhost.position y}, ${redGhost.score}, ${redGhost.name})")

    val ghostEaten: Int = 1
    var EatenGhostColor: List[String] = List()// lista
    val pacman = PacmanImpl("pacman", BaseStrategy())
    pacman.setPosition(PointImpl[Int,Int](20,20))
    val listProlog = ScalaProlog.scalaToPrologList(ghostList)

    if (isKillable) {
      val solveInfo = PrologConfig.getPrologEngine().solve(s"ghost_defeat(pacman(${pacman.position x},${pacman.position y},${pacman.lives remainingLives},${pacman.score.toString}), ${listProlog}, ${ghostEaten}, PS, EG).")
      val newScore = valueOf(solveInfo.getTerm("PS").toString)
      val eatenGhost = ScalaProlog.prologToScalaList(solveInfo.getTerm("EG").toString )
      pacman.score = newScore
    } else {
      val solveInfo = PrologConfig.getPrologEngine().solve(s"eat_pacman(pacman(${pacman.position x},${pacman.position y},${pacman.lives remainingLives},${pacman.score.toString}), ${listProlog}, NL, GS, CG).")
      val newPacmanLives: Int = valueOf(solveInfo.getTerm("NL").toString)
      pacman.lives.remainingLives_=(newPacmanLives)
      val scoreGhost: Int = valueOf(solveInfo.getTerm("GS").toString)
      score = scoreGhost
      val killerGhost: String = solveInfo.getTerm("CG").toString
    }
  }

}

