package client

import java.util.Calendar

/** Represent the current match result, holding some information about the game for leader-board and statistics.
  * Every Player receive one of this at the end of a game.
  *
  * @constructor create the result
  * @param result if the player has won the game or not
  *
  * @author manuBottax
  */
class MatchResult( val result : Boolean ) {

  val date : Calendar = Calendar.getInstance()

}
