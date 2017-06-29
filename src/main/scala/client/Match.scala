package client

/** Represent the current match status, holding some information about the game.
  * Is a common object for all the characters.
  *
  * @author manuBottax
  */
class Match {

  /** the current match score*/
  var score: Int = 0
  var ghostState: Boolean = true

  /**  change the ghost state, used when Pacman eat the pill and the ghost became scared and can be eaten by Pacman */
  def scareGhost () : Unit = {
    ghostState = false
    println("Ghost can now be eaten by pacman")
    //dopo un tot ritorna normale
  }
  // lo facciamo booleano e lo risetta il controller ? o fa da solo cin un timer ?
}
