package view

import java.awt.Image

/**
  * Wrapper for the specific images of each character.
  *
  * Created by chiaravarini on 01/07/17.
  */
trait CharacterView {

  /**
    * @return The character's image when it moves upward
    */
  def getCharacterUp: Image

  /**
    * @return The character's image when it moves down
    */
  def getCharacterDown: Image

  /**
    * @return The character's image when it moves left
    */
  def getCharacterLeft: Image

  /**
    * @return The character's image when it moves right
    */
  def getCharacterRight: Image


}

class CharacterViewImpl(var path:String) extends CharacterView {

 var pathAndResolution = path + Utils.getResolution().asString() + "/"


  /**
    * resources/images/pacman/24x24/Up.png
    * *
    * resources/images/  --> Utils
    * pacman/ --> path
    * 24x24/ -->CharacterViewImpl
    * Up --> getCharacterUp
    * .png --> Utils
    */
  def getCharacterUp: Image = Utils.getImage (pathAndResolution + "Up")

  def getCharacterDown: Image = Utils.getImage (pathAndResolution + "Down")

  def getCharacterLeft: Image = Utils.getImage (pathAndResolution + "Left")

  def getCharacterRight: Image = Utils.getImage (pathAndResolution + "Right")

}

class RedGhostView extends CharacterViewImpl("ghosts/red/")
class BlueGhostView extends CharacterViewImpl("ghosts/blue/")
class PinkGhostView extends CharacterViewImpl("ghosts/pink/")
class YellowGhostView extends CharacterViewImpl("ghosts/yellow/")
class PacmanView extends CharacterViewImpl("pacman/")