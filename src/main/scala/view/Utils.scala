package view

import java.awt.{Image, Toolkit}
import java.net.URL
import javafx.scene.media.{Media, MediaPlayer}
import javax.sound.sampled.AudioSystem
import javax.swing.ImageIcon

import view.utils.ImagesResolutions

/**
  * Created by chiaravarini on 01/07/17.
  */
object Utils {

  // private var mediaPlayer: MediaPlayer = null
  private val IMAGES_BASE_PATH = "/images/"
  private val IMAGES_EXTENSION = ".png"


  def getResource(path: String): URL = Utils.getClass.getResource(path)


  def getImage(path: String): Image = {
    new ImageIcon(getResource(IMAGES_BASE_PATH + path + IMAGES_EXTENSION)).getImage
  }

  def getResolution(): ImagesResolutions =  Toolkit.getDefaultToolkit().getScreenResolution() match{
    case x if x < 50 =>  ImagesResolutions.RES_24
    case x if x >= 50 && x < 100 =>  ImagesResolutions.RES_32
    case x if x >= 100 && x < 150 =>  ImagesResolutions.RES_48
    case _ =>  ImagesResolutions.RES_128

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
