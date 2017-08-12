package client.view

import java.awt.image.BufferedImage
import java.awt.{Image, RenderingHints, Toolkit}
import java.net.URL
import javafx.scene.media.{Media, MediaPlayer}
import javax.sound.sampled.AudioSystem
import javax.swing.ImageIcon

import client.view.utils.ImagesResolutions
import Res._
import client.model.gameElement.Eatable
import client.view.utils.enumerations.{FruitsImages, ImagesResolutions}

import scala.collection.JavaConverters._
/**
  * Created by chiaravarini on 01/07/17.
  */
object Utils {

   private var mediaPlayer: MediaPlayer = null

  def getResource(path: String): URL = Utils.getClass.getResource(path)   //TODO lanciare eccezione nel caso in cui non trovi la risorsa!

  def getImage(path: String): Image = {
    new ImageIcon(getResource(IMAGES_BASE_PATH + path + IMAGES_EXTENSION)).getImage
  }

  def getScaledImage (srcImg: Image, w: Int, h: Int): Image = {
    val resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB)
    val g2 = resizedImg.createGraphics
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
    g2.drawImage(srcImg, 0, 0, w, h, null)
    g2.dispose()
    resizedImg
  }

  def getGif(name: String): Image = {
    new ImageIcon(getResource(GIF_BASE_PATH + name + GIF_EXTENSION)).getImage
  }

  def getResolution(): ImagesResolutions =  Toolkit.getDefaultToolkit().getScreenResolution() match{
    case x if x < 50 =>  ImagesResolutions.RES_24
    case x if x >= 50 && x < 100 =>  ImagesResolutions.RES_32
    case x if x >= 100 && x < 150 =>  ImagesResolutions.RES_48
    case _ =>  ImagesResolutions.RES_128

  }

  def getFruitsImage(etableType: Eatable): FruitsImages = etableType.getClass.getSimpleName match {
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

  def getJavaList[E](list: List[E]): java.util.List[E] = list.asJava

  def transformInString (array: Array[Char]): String = {
    var res = ""
    array.toSeq.foreach(c=> res += c)
    res
  }

  def getScalaMap[A,B](map: java.util.Map[A,B]): scala.collection.mutable.Map[A,B] = map.asScala

  def scalaRangeToString(ranges: List[Range]): java.util.List[client.view.utils.Range] ={
    ranges map(r => new client.view.utils.Range(r min, r max)) asJava
  }

  def getJavaMap[A,B](map: Map[A,B]) = map.asJava

   def playSound(sound: String):Unit = {
    val clip = AudioSystem.getClip
    clip.open(AudioSystem.getAudioInputStream(getResource(sound)))
    clip.start()
  }

  def backgroundSoundPlay(song: String):Unit = {

    var hit = new Media(getResource(song).toURI.toString)
    mediaPlayer = new MediaPlayer(hit)
    mediaPlayer.play()

  }

  def stopSong():Unit = if (mediaPlayer!=null) mediaPlayer.stop()
}
