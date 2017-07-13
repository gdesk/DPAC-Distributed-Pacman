package client.view

import java.awt.{Image, Toolkit}
import java.net.URL
import javafx.scene.media.{Media, MediaPlayer}
import javax.sound.sampled.AudioSystem
import javax.swing.ImageIcon

import client.view.utils.ImagesResolutions
import Res._

/**
  * Created by chiaravarini on 01/07/17.
  */
object Utils {

  // private var mediaPlayer: MediaPlayer = null



  def getResource(path: String): URL = Utils.getClass.getResource(path)   //TODO lanciare eccezione nel caso in cui non trovi la risorsa!


  def getImage(path: String): Image = {
    new ImageIcon(getResource(IMAGES_BASE_PATH + path + IMAGES_EXTENSION)).getImage
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

  def getJavaList[E](list: List[E]): java.util.List[E] = {
    import scala.collection.JavaConverters._
    list.asJava
  }

  def transformInString (array: Array[Char]): String = {
    var res = ""
    array.toSeq.foreach(c=> res += c)
    res
  }

  /* def playSound(sound: String):Unit = {
    val clip = AudioSystem.getClip
    clip.open(AudioSystem.getAudioInputStream(getResource(sound)))
    clip.start()
  }

  def backgroundSoundPlay(song: String):Unit = {

    var hit = new Media(getResource(song).toURI.toString)
    mediaPlayer = new MediaPlayer(hit)
    mediaPlayer.play()

  }

  def stopSong():Unit = if (mediaPlayer!=null) mediaPlayer.stop()*/

}
