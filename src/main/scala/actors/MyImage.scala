package actors

import java.awt.image.BufferedImage
import java.io.Serializable

/**
  * Created by lucch on 10/08/2017.
  */
@SerialVersionUID(42l)
case class MyImage() extends BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB) with Serializable

