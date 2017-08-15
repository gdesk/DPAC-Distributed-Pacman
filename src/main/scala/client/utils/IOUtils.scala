package client.utils

import java.io.{File, PrintWriter}
import java.util.Calendar

import client.model.gameElement._
import client.model.utils.{Dimension, PointImpl}
import client.model.{Playground, PlaygroundImpl}

import scala.collection.mutable.ListBuffer
import scala.io.Source

/** Utils for handle file I/O.
  *
  * @author ManuBottax
  */
object IOUtils {

  private val BASE_PATH = "src/main/resources/playground/"
  private val PLAYGROUND_FILE_EXTENSION = ".dpac"

  private val writer : PrintWriter = new PrintWriter(new File("log.txt" ))

  /** Utils method for save a string on file, used as a logger feature for the application.
    *
    * @param log the string to be saved
    */
  def saveLog(log: String): Unit = {
    println("log ricevuto: " + log)
      val cal : Calendar = Calendar.getInstance()
      writer.append("[ " + cal.get(Calendar.DAY_OF_MONTH) + "/" +  cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR)
        + " " + cal.get(Calendar.HOUR) + ":" +
        cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + " ]: " + log + "\n")
    writer.flush()
  }


  /** Get the specified file from path and parse it to generate a playground.
    *
    * the syntax for that file is :
    * 'x' -> block
    * '.' -> dot
    * 'p' -> pill
    *
    * 'a' -> apple
    * 'b' -> bell
    * 'c' -> cherry
    * 'h' -> Galaxian Ship
    * 'g' -> grapes
    * 'k' -> key
    * 'o' -> orange
    * 's' -> strawberry
    *
    * other char are taken as blank space by default.
    *
    * dimension are taken from file counting the line and the length of the line in it.
    *
    * @param file the file to be parsed
    * @return the playground parsed from file
    *
    */
  def getPlaygroundFromFile(file: File): Playground = {
    val playground: Playground = PlaygroundImpl

    if (file.canRead) {
      val block: List[Block] = parseBlock(file)
      val eatable: List[Eatable] = parseEatable(file)

      //val character: List[Character] = parseCharacter(file)

      playground.dimension = parseDimension(file)

      var groundList: List[GameItem] = block ::: eatable
      playground.ground_=(groundList)

      println("Created a Playground of dimension [ " + playground.dimension.x + " | " + playground.dimension.y
        + " ] with " + block.size + " blocks and " + eatable.size + " eatable elements")

    }
    playground
  }


  /** Get the specified file from path and parse it to generate a playground.
    *
    * the syntax for that file is :
    * 'x' -> block
    * '.' -> dot
    * 'p' -> pill
    *
    * 'a' -> apple
    * 'b' -> bell
    * 'c' -> cherry
    * 'h' -> Galaxian Ship
    * 'g' -> grapes
    * 'k' -> key
    * 'o' -> orange
    * 's' -> strawberry
    *
    * other char are taken as blank space by default.
    *
    * dimension are taken from file counting the line and the length of the line in it.
    *
    * @param fileName the name of the file to be parsed. Suggested format is ''filename.dpac''
    * @return the playground parsed from file
    */
  def getPlaygroundFromPath(fileName: String) : Playground = {
    val playgroundFile: File = new File(BASE_PATH + fileName)
    getPlaygroundFromFile(playgroundFile)
  }

  private def parseBlock(file: File): List[Block] = {
    var blockList: ListBuffer[Block] = new ListBuffer[Block]
    var xPosition: Int = 0
    var yPosition: Int = 0

    Source.fromFile(file).foreach( _ match {
      case 'x' => {
       // println("I'm a Block at pos [" + xPosition + " | " + yPosition + " ]")
        blockList.+=(Block(PointImpl (xPosition,yPosition)))
        xPosition = xPosition + 1
      }
      case '\n' => {
        yPosition = yPosition + 1
        xPosition = 0
        false
      }
      case _ => {
        xPosition = xPosition + 1
        false
      }
    } )

    blockList.toList
  }


  private def parseEatable(file: File): List[Eatable] = {
      var eatableList: ListBuffer[Eatable] = new ListBuffer[Eatable]
      var xPosition: Int = 0
      var yPosition: Int = 0

      Source.fromFile(file).foreach( _ match {
        case '.' => {
          //println("I'm a dot at pos [" + xPosition + " | " + yPosition + " ]")
          eatableList.+=(Dot("",PointImpl (xPosition,yPosition)))
          xPosition = xPosition + 1
        }
        case 'p' => {
          //println("I'm a pill at pos [" + xPosition + " | " + yPosition + " ]")
          eatableList.+=(Pill("",PointImpl (xPosition,yPosition)))
          xPosition = xPosition + 1
        }
        case 'a' => {
          //println("I'm an apple at pos [" + xPosition + " | " + yPosition + " ]")
          eatableList.+=(Apple("",PointImpl (xPosition,yPosition)))
          xPosition = xPosition + 1
        }
        case 'b' => {
          //println("I'm a bell at pos [" + xPosition + " | " + yPosition + " ]")
          eatableList.+=(Bell("",PointImpl (xPosition,yPosition)))
          xPosition = xPosition + 1
        }
        case 'c' => {
          //println("I'm a cherry at pos [" + xPosition + " | " + yPosition + " ]")
          eatableList.+=(Cherry("",PointImpl (xPosition,yPosition)))
          xPosition = xPosition + 1
        }
        case 'h' => {
          //println("I'm a galaxian Ship at pos [" + xPosition + " | " + yPosition + " ]")
          eatableList.+=(GalaxianShip("",PointImpl (xPosition,yPosition)))
          xPosition = xPosition + 1
        }
        case 'g' => {
          //println("I'm a grapes at pos [" + xPosition + " | " + yPosition + " ]")
          eatableList.+=(Grape("",PointImpl (xPosition,yPosition)))
          xPosition = xPosition + 1
        }
        case 'k' => {
          //println("I'm a key at pos [" + xPosition + " | " + yPosition + " ]")
          eatableList.+=(Key("",PointImpl (xPosition,yPosition)))
          xPosition = xPosition + 1
        }
        case 'o' => {
          //println("I'm an orange at pos [" + xPosition + " | " + yPosition + " ]")
          eatableList.+=(Orange("",PointImpl (xPosition,yPosition)))
          xPosition = xPosition + 1
        }
        case 's' => {
         //println("I'm an apple at pos [" + xPosition + " | " + yPosition + " ]")
          eatableList.+=(Strawberry("",PointImpl (xPosition,yPosition)))
          xPosition = xPosition + 1
        }
        case '\n' => {
          yPosition = yPosition + 1
          xPosition = 0
          false
        }
        case _ => {
          xPosition = xPosition + 1
          false
        }
      } )

      eatableList.toList
    }

  private def parseDimension(file: File) :  Dimension = {

    var xPosition: Int = 0
    var yPosition: Int = 0

    var xDim: Int = 0
    var yDim: Int = 0

    Source.fromFile(file).foreach(_ match {
      case '\n' => {
        if (xPosition > xDim)
          xDim = xPosition
        xPosition = 0
        yPosition = yPosition + 1
      }

      case _ => {
        xPosition = xPosition + 1
      }
    } )

    yDim = yPosition

    Dimension(xDim,yDim)
  }

}
