package client.character

import java.lang.Integer.valueOf
import client.Playground
import client.utils.{Dimension, Point, ScalaProlog}
import characterjava.Direction
import client.character.InitializedInfoImpl.{getCharacterLives, getStartPosition}

/**
  * Manages the Pacman character.
  *
  * @author Margherita Pecorelli
  * @author Giulia Lucchi
  */
trait Pacman {
  /**
    * This method checks if Pacman can eat some {@Eatable} object
    */
 def eatObject(): Unit
}

case class PacmanImpl(override val name: String) extends CharacterImpl(true, new LivesImpl(InitializedInfoImpl.getCharacterLives("pacman"))) with Pacman {

  setPosition(InitializedInfoImpl.getStartPosition())

  /**
    * This method checks if Pacman can eat some {@Eatable} object
    */
  override def eatObject(): Unit = {
    val solveInfo = PrologConfig.getPrologEngine().solve(s"eat_object(pacman(${position x},${position y},${lives remainingLives()},${score toString}),[],S,[],).")

    val eatableObjects: List[String] = List(s"eatable_object(${blueGhost.position x}, ${blueGhost.position y}, ${blueGhost.score}, ${blueGhost.name})", s"ghost(${redGhost.position x}, ${redGhost.position y}, ${redGhost.score}, ${redGhost.name})")

    val playground: Playground = Playground(Dimension(2,2))
    var eatables: String = "["
    playground.getAllEatable.foreach(e =>
      eatables = eatables + "eatable_object(" + e.position.x + "," + e.position.y + "," + e.score + "," + e + ","
    )



    scalaList.toStream.foreach(x =>
      string = string.concat(x+",")
    )
    string = string substring (0,string.size-2)
    string = string concat ("]")
    string





         [eatable_object(PX,PY,V,N)|T]


       val solveInfo = PrologConfig.getPrologEngine().solve(s"ghost_defeat(pacman(${pacman.position x},${pacman.position y},${pacman.lives.remainingLives()},${pacman.score.toString}), ${listProlog}, ${ghostEaten}, PS, EG).")
       val newScore = valueOf(solveInfo.getTerm("PS").toString)
       val eatenGhost = ScalaProlog.prologToScalaList(solveInfo.getTerm("EG").toString )
  }

    /* Input parameters:
       % 	-pacman with this structure: pacman(X,Y,Lives,Score);
     % 	-list of objects that can be eaten.
       % Output parameters:
       % 	-the new pacman's score resulting after summing eatable object value to pacman's score;
     % 	-list containing objects that have not been eaten yet.*/


  /**
    * Manage the the strategy of game, that is based on who the killer is and who the killable
    */
  override def checkAllPositions(): Unit = {}

  /**
    * Manages the character's movement and consequently the contact with other item of the game.
    *
    * @param direction    character's of direction
    */
  override def go(direction: Direction): Unit = {
    val prePosition: Point[Int, Int] = position
    super.go(direction)
    val pos: Point[Int, Int] = position
    pos match {
      case x if !(x equals prePosition) => eatObject()
      case _ => pos
    }
  }
}
