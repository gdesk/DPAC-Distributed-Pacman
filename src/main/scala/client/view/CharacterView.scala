package client.view

import java.awt.Image

/**
  * Wrapper for the specific images of each client.model.character.gameElement.character.
  *
  * Created by chiaravarini on 01/07/17.
  */
trait CharacterView {

  /**
    * @return The client.model.character.gameElement.character's image when it moves upward
    */
  def getCharacterUp: Image

  /**
    * @return The client.model.character.gameElement.character's image when it moves down
    */
  def getCharacterDown: Image

  /**
    * @return The client.model.character.gameElement.character's image when it moves left
    */
  def getCharacterLeft: Image

  /**
    * @return The client.model.character.gameElement.character's image when it moves right
    */
  def getCharacterRight: Image


}

//class CharacterViewImpl(var path:String) extends CharacterView {

class CharacterViewImpl(val characterPath: CharacterPath) extends CharacterView {

 var pathAndResolution: String = characterPath.path + Utils.getResolution().asString() + "/"
  
  //resources/images/pacman/24x24/Up.png

  def getCharacterUp: Image = getImage("Up")

  def getCharacterDown: Image = getImage("Down")

  def getCharacterLeft: Image = getImage("Left")

  def getCharacterRight: Image = getImage("Right")

  private def getImage(direction: String): Image = Utils.getImage(pathAndResolution+direction)

}

trait CharacterPath {
  def path: String
}

class CharacterPathImpl (name: String) extends CharacterPath {

  override val path: String = name.toLowerCase + "/"
}

class GhostPath (color: String ) extends CharacterPath {

  override val path: String = "ghosts/" + color.toLowerCase + "/"
}

/*
class RedGhostView extends CharacterViewImpl(new GhostPath("red"))
class BlueGhostView extends CharacterViewImpl(new GhostPath("blue"))
class PinkGhostView extends CharacterViewImpl(new GhostPath("pink"))
class YellowGhostView extends CharacterViewImpl(new GhostPath("yellow"))
class PacmanView extends CharacterViewImpl(new CharacterPathImpl("pacman"))

*/