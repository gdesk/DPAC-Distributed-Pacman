package view

import java.awt.Image

import org.scalatest.path

/**
  * Wrapper for the specific images of each block.
  *
  * Created by chiaravarini on 03/07/17.
  */
trait BlockView {

  /**
    * @return The image of the horizontal block
    */
  def getHorizontal(): Image

  /**
    * @return The image of the horizontal block closed to the left
    */
  def getLeftEnd(): Image

  /**
    * @return The image of the vertical block with the left opening
    */
  def getLeftUpLower(): Image //LeftOpen

  /**
    * @return The image of the vertical block closed at the bottom
    */
  def getLowerEnd(): Image

  /**
    * @return The block image of the lower left corner
    */
  def getLowerLeftEdge(): Image

  /**
    * @return The image of the horizontal block with the lower opening
    */
  def getLeftRightLower(): Image //LowerOpen

  /**
    * @return The block image of the lower right corner
    */
  def getLowerRightEdge(): Image

  /**
    * @return The image of the horizontal block closed to the right
    */
  def getRightEnd(): Image

  /**
    * @return The image of the vertical block with the right opening
    */
  def getUpRightLower(): Image   //rightOpen

  /**
    * @return The image of the vertical block closed at the top
    */
  def getUpperEnd(): Image

  /**
    * @return The block image of the upper left corner
    */
  def getUpperLeftEdge(): Image

  /**
    * @return The image of the horizontal block with the upper opening
    */
  def getLeftUpperRight(): Image  //UpperOpen

  /**
    * @return The block image of the upper right corner
    */
  def getUpperRightEdge(): Image

  /**
    * @return The image of the vertical block
    */
  def getVertical(): Image

}

class BlockViewImpl extends BlockView {

  var path = "block/" + Utils.getResolution().asString() + "/"

  override def getHorizontal(): Image = getImage("horizontalBlock")

  override def getLeftEnd(): Image = getImage("leftEnd")

  override def getLeftUpLower(): Image = getImage("leftOpen")

  override def getLowerEnd(): Image = getImage("lowerEnd")

  override def getLowerLeftEdge(): Image = getImage("lowerLeftEdge")

  override def getLeftRightLower(): Image = getImage("lowerOpen")

  override def getLowerRightEdge(): Image = getImage("lowerRightEdge")

  override def getRightEnd(): Image = getImage("rightEnd")

  override def getUpRightLower(): Image = getImage("rightOpen")

  override def getUpperEnd(): Image = getImage("upperEnd")

  override def getUpperLeftEdge(): Image = getImage("upperLeftEdge")

  override def getLeftUpperRight(): Image = getImage("upperOpen")

  override def getUpperRightEdge(): Image = getImage("upperRightEdge")

  override def getVertical(): Image = getImage("verticalBlock")

  private def getImage( fileName: String): Image = Utils.getImage(path + fileName)

}
