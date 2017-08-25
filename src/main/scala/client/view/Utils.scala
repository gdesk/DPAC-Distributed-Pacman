package client.view

import java.awt.image.BufferedImage
import java.awt.{Image, RenderingHints, Toolkit}
import java.io.InputStream
import java.net.URL
import javax.imageio.ImageIO
import Res.{IMAGES_BASE_PATH, _}
import client.model.gameElement.Eatable
import client.view.utils.enumerations.{FruitsImages, ImagesResolutions}

import scala.collection.JavaConverters._

/**
  * In this class, the methods for the game's view part are implemented
  * Created by Chiara Varini on 01/07/17.
  */
object Utils {

  /**
    * Method used to load any resource
    * @param path
    * @return resource's URL
    */
  def getResource(path: String): URL = Utils.getClass.getResource(path)

  /**
    * Method used to load game's images
    * @param path
    * @return
    */
  def getImage(path: String): Image = ImageIO.read(Utils.getClass.getResource(IMAGES_BASE_PATH + path + IMAGES_EXTENSION))

  /**
    * Method used to resize images
    * @param image
    * @param width
    * @param height
    * @return
    */
  def getScaledImage (image: Image, width: Int, height: Int): Image = {
    val resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g2 = resizedImage.createGraphics
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
    g2.drawImage(image, 0, 0, width, height, null)
    g2.dispose()
    resizedImage
  }

  /**
    * Method used to load game's gif
    * @param name
    * @return
    */
  def getGif(name: String): Image = {
    val in: InputStream = Utils.getClass.getClassLoader.getResourceAsStream(GIF_BASE_PATH + name + GIF_EXTENSION)
    Toolkit.getDefaultToolkit.createImage(getResource(GIF_BASE_PATH + name + GIF_EXTENSION))
  }

  /**
    * Method used to manage image resolution
    * @return
    */
  def getResolution: ImagesResolutions =  Toolkit.getDefaultToolkit.getScreenResolution match{
    case x if x < 50 =>  ImagesResolutions.RES_24
    case x if x >= 50 && x < 100 =>  ImagesResolutions.RES_32
    case x if x >= 100 && x < 150 =>  ImagesResolutions.RES_48
    case _ =>  ImagesResolutions.RES_128

  }

  /**
    * Method used for load eatable images
    * @param eatableType
    * @return
    */
  def getFruitsImage(eatableType: Eatable): FruitsImages = eatableType.getClass.getSimpleName match {
    case "Cherry" => FruitsImages.CHERRY
    case "Strawberry" => FruitsImages.STRAWBERRY
    case "Orange" => FruitsImages.ORANGE
    case "Apple" => FruitsImages.APPLE
    case "Grapes" => FruitsImages.GRAPES
    case "GalaxianShip" => FruitsImages.GALAXIAN
    case "Bell" => FruitsImages.BELL
    case "Key" => FruitsImages.KEY
    case _ => FruitsImages.APPLE
  }

  //These conversion methods could have been implicits

  /**
    * Method used to convert an Array[Char] to a String
    * @param array
    * @return
    */
  def arrayToString(array: Array[Char]): String = {
    var res = ""
    array.toSeq.foreach(c=> res += c)
    res
  }

  /**
    * Method used to convert an scala.List[E] to a java.util.List[E]
    * @param list
    * @tparam E
    * @return
    */
  def getJavaList[E](list: List[E]): java.util.List[E] = list asJava

  /**
    * Method used to convert an scala.Map[A,B] to a  java.util.Map[A,B]
    * @param map
    * @tparam A
    * @tparam B
    * @return
    */
  def getJavaMap[A,B](map: Map[A,B]): java.util.Map[A,B] = map asJava

  /**
    * Method used to convert an java.util.Map[A,B] to a scala.Map[A,B]
    * @param map
    * @tparam A
    * @tparam B
    * @return
    */
  def getScalaMap[A,B](map: java.util.Map[A,B]): scala.collection.mutable.Map[A,B] = map asScala

  /**
    * Method used to convert an scala.List[Range] to a java.util.List[client.view.utils.Range]
    * @param ranges
    * @return
    */
  def scalaRangeToString(ranges: List[Range]): java.util.List[client.view.utils.Range] = ranges map(r => new client.view.utils.Range(r min, r max)) asJava

}
