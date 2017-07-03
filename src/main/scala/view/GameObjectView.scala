package view

import java.awt.Image

import view.utils.Fruits
import view.utils.Fruits._

/**
  * Wrapper for the specific images of dots, pills and fruits
  * Created by chiaravarini on 03/07/17.
  */
trait GameObjectView {

  /**
    * @return The image of the dot
    */
  def getDot(): Image

  /**
    * @return The image of the pill
    */
  def getPill(): Image

  /**
    * @param fruit
    * @return The image of the specified fruit
    */
  def getFruit(fruit: Fruits): Image

}

class GameObjectViewImpl extends GameObjectView{

  override def getDot(): Image = getImage("dot/YellowDot")

  override def getPill(): Image = getImage("pill/YellowPill")

  override def getFruit(fruit: Fruits): Image = getImage("fruit/"+Utils.getResolution().asString()+"/"+fruit.getImageFileName)

  private def getImage(imageName: String): Image = Utils.getImage(imageName)

}
