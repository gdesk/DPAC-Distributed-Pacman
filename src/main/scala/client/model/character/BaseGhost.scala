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

case class BaseGhost(override val name: String) extends CharacterImpl(false) {

  private val playground: Playground = PlaygroundImpl instance()
  private val game: Match = MatchImpl instance()

  setPosition(PointImpl[Int, Int](20, 20))

  //setPosition(InitializedInfoImpl.getStartPosition(ghostName.getName))
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
    /*solo per capire se funziona poi lo importeremo da scalaprolog e dovrò fare the convert ghost scala to ghost prolog
    val blueGhost = BaseGhost("blueGhost")
    val redGhost = BaseGhost("redGhost")
    val yellowGhost = BaseGhost("yellowGhost")
    val ghostList: List[String] = List(s" ghost(${blueGhost.position x}, ${blueGhost.position y}, ${blueGhost.score}, ${blueGhost.name})", s"ghost(${redGhost.position x}, ${redGhost.position y}, ${redGhost.score}, ${redGhost.name})")
    */
    //val ghostEaten: Int = 1
    //var EatenGhostColor: List[String] = List()// lista
    //val pacman = BasePacman("pacman", BaseEatObjectStrategy())
    //pacman.setPosition(PointImpl[Int,Int](20,20))
    //val listProlog = ScalaProlog.scalaToPrologList(ghostList)

    var ghosts: String = "["
    (game characters) filter (c => !(c.isInstanceOf[Pacman])) foreach(e =>
      ghosts = ghosts + "ghost(" + e.position.x + "," + e.position.y + "," + e.score + "," + e.name + "),"
      )
    ghosts = ghosts substring (0,(ghosts size)-2)
    ghosts = ghosts + "]"

    val pacman = ((game characters) filter (c => (c.isInstanceOf[Pacman])) head).asInstanceOf[Pacman]

    if(isKillable) {
      pacman.checkAllPositions()
      /*
      val solveInfo = PrologConfig.getPrologEngine().solve(s"ghost_defeat(pacman(${pacman.position x},${pacman.position y},${pacman.lives remainingLives},${pacman.score.toString}), ${listProlog}, ${ghostEaten}, PS, EG).")
      val eatenGhost = ScalaProlog.prologToScalaList(solveInfo.getTerm("EG").toString )
      pacman.score = valueOf(solveInfo.getTerm("PS").toString)
       */
    } else {
      val solveInfo = PrologConfig.getPrologEngine() solve (s"eat_pacman(pacman(${pacman.position x},${pacman.position y},${pacman.lives remainingLives},${pacman.score.toString}), ${ghosts}, NL, GS, CG).")
      val killerGhost = ((game characters) find (c => c.name equals (solveInfo getTerm ("CG") toString)) get)
      if(killerGhost equals this) {
        pacman.lives remainingLives = valueOf(solveInfo getTerm ("NL") toString)
        if((pacman.lives remainingLives) == 0) isAlive = false
        score = valueOf(solveInfo getTerm ("GS") toString)
      }
    }
    /*
    } else {
      val solveInfo = PrologConfig.getPrologEngine().solve(s"eat_pacman(pacman(${pacman.position x},${pacman.position y},${pacman.lives remainingLives},${pacman.score.toString}), ${listProlog}, NL, GS, CG).")
      val newPacmanLives: Int = valueOf(solveInfo.getTerm("NL").toString)
      pacman.lives.remainingLives_=(newPacmanLives)
      val scoreGhost: Int = valueOf(solveInfo.getTerm("GS").toString)
      score = scoreGhost
      val killerGhost: String = solveInfo.getTerm("CG").toString
    }
    */
  }

}

