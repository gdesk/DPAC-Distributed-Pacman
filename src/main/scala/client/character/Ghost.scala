package client.character

import java.awt.Color
import java.lang.Integer.valueOf

import characterjava.Direction
import client.utils.{Point, ScalaProlog}

/**
  * Ghost in one of Characters.
  *
  * @author Giulia Lucchi
  */

case class GhostImpl(override val name: String) extends CharacterImpl(false, new LivesImpl(InitializedInfoImpl.getCharacterLives("ghost"))) {
  setPosition(Point[Int, Int](20, 20))

  override def go(direction: Direction): Unit = super.go(direction)

  /**
    * Manages the strategy of game, that is based on who is the killer and who is killable.
    * 
    */
  override def checkAllPositions(): Unit = {
    /*solo per capire se funziona poi lo importeremo da scalaprolog e dovrò fare the convert ghost scala to ghost prolog*/
    val blueGhost = GhostImpl("blue")
    val redGhost = GhostImpl("red")
    val yellowGhost = GhostImpl("yellow")
    val ghostList: List[String] = List(s" ghost(${blueGhost.position x}, ${blueGhost.position y}, ${blueGhost.score}, ${blueGhost.name})", s"ghost(${redGhost.position x}, ${redGhost.position y}, ${redGhost.score}, ${redGhost.name})")
    val ghostEaten: Int = 1
    var EatenGhostColor: List[Color] = List()// lista
    val pacman : Pacman = PacmanImpl("pacman")
    pacman.setPosition(Point[Int,Int](20,20))
    val listProlog = ScalaProlog.scalaToPrologList(ghostList)

    if (isKillable) {
      val solveInfo = PrologConfig.getPrologEngine().solve(s"ghost_defeat(pacman(${pacman.position x},${pacman.position y},${pacman.lives.remainingLives()},${pacman.score.toString}), ${listProlog}, ${ghostEaten}, PS, EG).")
      val newScore = valueOf(solveInfo.getTerm("PS").toString)
      val eatenGhost = ScalaProlog.prologToScalaList(solveInfo.getTerm("EG").toString )
      pacman.score = newScore
    } else {
      val solveInfo = PrologConfig.getPrologEngine().solve(s"eat_pacman(pacman(${pacman.position x},${pacman.position y},${pacman.lives.remainingLives()},${pacman.score.toString}), ${listProlog}, NL, GS, CG).")
      val newPacmanLives: Int = valueOf(solveInfo.getTerm("NL").toString)
      pacman.lives.remainingLives_=(newPacmanLives)
      val scoreGhost: Int = valueOf(solveInfo.getTerm("GS").toString)
      score = scoreGhost
      val killerGhost: String = solveInfo.getTerm("CG").toString
    }
  }
}

