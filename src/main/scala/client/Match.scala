package client

/**
  * Created by ManuBottax on 25/06/2017.
  */
class Match {

  var score: Int = 0
  var ghostState: Boolean = true

  def scareGhost () : Unit = {
    ghostState = false
    println("Ghost can now be eaten by pacman")
    //dopo un tot ritorna normale
  }
}
