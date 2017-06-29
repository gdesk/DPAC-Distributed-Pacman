package client.character

import client.utils.LivesImpl

/**
  * Created by Giulia Lucchi on 28/06/2017.
  */
//trait Pacman[X, Y] extends Character[X, Y]{
  //def eatItem(eatable: Eatable) GUARDA PROLOG probabilmente non serve perchè verrà richiamato il metodo prolog direttamente da go
  //si potrebbe fare privato e richiamare prolog . tutto richimto su go del quale facciamo un override
//}
case class PacmanImpl(override val name: String) extends CharacterImpl(true, new LivesImpl(3/*da prolog*/)) /*with Pacman[Int, Int]*/ {
}
