package client.character


import java.awt.Color

import characterjava.Direction
import client.utils.Point

/**
  * @author Giulia Lucchi
  */

trait Ghost{
  def color: Color
  def color_=(color: Color): Unit
}

case class GhostImpl(override val name: String, override var color: Color) extends CharacterImpl(false, new LivesImpl(InitializedInfoImpl.getCharacterLives("ghost"))) with Ghost {
  setPosition(Point[Int, Int](20, 20))

  override def go(direction: Direction): Unit = super.go(direction)

  /**
    * Manages the strategy of game, that is based on who is the killer and who is killable
    */
  override def checkAllPositions(): Unit = {
    if (isKillable) {
      val fruit: List[GhostImpl] = List(GhostImpl("ghost", Color.BLUE), GhostImpl("ghost1", Color.RED), GhostImpl("ghost1", Color.CYAN))
      val ghostEaten: Int = 1
      var EatenGhostColor: List[Color] = List()
      val pacman : Pacman = PacmanImpl("pacman")

      val solveInfo = PrologConfig.getPrologEngine().solve(s"ghost_defeat(+pacman(${pacman.position x},${pacman.position y},${pacman.lives.remainingLives()},${pacman.}), +GhostList, +NumberOfGhostEaten, -NewPacmanScore, -ListOfEatenGhostsColor).")
      val x = Integer.valueOf(solveInfo.getTerm("X").toString)
      val y = Integer.valueOf(solveInfo.getTerm("Y").toString)

      //ghost_defeat
    } else {
      //eat_pacman
    }
  }

  /*%ghost_defeat checks if one or more ghosts have been eaten from pacman.
%Input parameters:
% 	-pacman with this structure: pacman(X,Y,Lives,Score);
% 	-a ghosts' list (each ghost has the following structure: ghost(X, Y, Score, Color));
% 	-number of ghosts already eaten.
% Output parameters:
% 	-the new pacman score thus, current score plus value of ghost eaten;
% 	-a list containg colors associated to each ghost that pacman ate.
%ghost_defeat(+pacman(X,Y,_,Score), +GhostList, +NumberOfGhostEaten, -NewPacmanScore, -ListOfEatenGhostsColor).
ghost_defeat(pacman(_, _, _, PS),[],N,PS,[]).
ghost_defeat(pacman(PX,PY,_,PS),[ghost(PX,PY,_,C)| T],N,NPS,[C|L1]):- ghost_defeat(pacman(PX, PY, _,PS),T,N+1,NPS1,L1), NPS is NPS1+(200*(N+1)), !.
ghost_defeat(pacman(PX,PY,_,PS),[_|T],N,NPS,L1):- ghost_defeat(pacman(PX,PY,_,PS),T,N,NPS,L1), !.
  *
  *       */

  /*%eat_pacman checks if pacman has been eaten by ghost because they are in the same position
% Input parameters:
% 	-pacman with this structure: pacman(X,Y,Lives,Score);
% 	-list of ghosts (each ghost has the following structure: ghost(X, Y, Score, Color));
% Output parameters:
% 	-number of pacman lives left
% 	-the new ghost score thus, current score plus value of pacman eaten
% 	-the color of ghost that ate pacman
%eat_pacman(+pacman(X,Y,Lives,_), +GhostList, -NewPacmanLives, -NewGhostScore, -GhostColor).
eat_pacman(pacman(_,_,NL,_),[],NL,0,"").
eat_pacman(pacman(PX,PY,NL,_),[ghost(PX,PY,GS,C)|T],NL1,NGS,C):- NL1 is NL-1, NGS is GS+500, !.
eat_pacman(pacman(PX,PY,NL,_),[_|T],NL1,NGS,C):- eat_pacman(pacman(PX,PY,NL,_),T,NL1,NGS,C).*/
}

