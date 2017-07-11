package client.utils

import java.io.{File, PrintWriter}
import java.util.Calendar

import client.model.gameElement._
import client.model.{Playground, PlaygroundImpl}
import client.model.utils.{Dimension, Point, PointImpl}

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

  /**
    *
    * @param log
    */
  def saveLog(log: String): Unit = {
    println("log ricevuto: " + log)
      val cal : Calendar = Calendar.getInstance()
      writer.append("[ " + cal.get(Calendar.DAY_OF_MONTH) + "/" +  cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR)
        + " " + cal.get(Calendar.HOUR) + ":" +
        cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + " ]: " + log + "\n")
    writer.flush()
      //writer.close()
  }

  def getPlaygroundFromFile(fileName: String) : Playground = {
    val playground: Playground = PlaygroundImpl.instance()

    val playgroundFile: File = new File(BASE_PATH + fileName)

    // todo: con la versione vecchia del model era infinitamente più facile ed economico farlo
    // playground = parseFile(path)

    val block: List[Block] = parseBlock (playgroundFile)
    val eatable: List[Eatable] = parseEatable (playgroundFile)

    // todo: perchè character vuole i tipi ?
    //val character: List[Character[Int,Int]] = parseCharacter(playgroundFile)

    playground.dimension = parseDimension(playgroundFile)
    playground.blocks_=(block)
    playground.eatables_=(eatable)

    println("Created a Playground of dimension [ " + playground.dimension.x + " | " + playground.dimension.y
      + " ] with " + block.size + " blocks and " + eatable.size + " eatable elements")

    playground
  }

  private def parseBlock(file: File): List[Block] = {
    var blockList: ListBuffer[Block] = new ListBuffer[Block]
    var xPosition: Int = 0
    var yPosition: Int = 0

    Source.fromFile(file).foreach( _ match {
      case 'x' => {
        println("I'm a Block at pos [" + xPosition + " | " + yPosition + " ]")
        xPosition = xPosition + 1
        blockList.+=(Block(PointImpl (xPosition,yPosition)))
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


    //todo : perchè mela, ciliegia, uva e arancia funzionano e gli altri frutti no ???????

      Source.fromFile(file).foreach( _ match {
        case '.' => {
          println("I'm a dot at pos [" + xPosition + " | " + yPosition + " ]")
          xPosition = xPosition + 1
          eatableList.+=(Dot("",PointImpl (xPosition,yPosition)))
        }
        case 'p' => {
          println("I'm a pill at pos [" + xPosition + " | " + yPosition + " ]")
          xPosition = xPosition + 1
          eatableList.+=(Pill("",PointImpl (xPosition,yPosition)))
        }
        case 'a' => {
          println("I'm an apple at pos [" + xPosition + " | " + yPosition + " ]")
          xPosition = xPosition + 1
          eatableList.+=(Apple("",PointImpl (xPosition,yPosition)))
        }
        case 'b' => {
          println("I'm a bell at pos [" + xPosition + " | " + yPosition + " ]")
          xPosition = xPosition + 1
          eatableList.+=(Bell("",PointImpl (xPosition,yPosition)))
        }
        case 'c' => {
          println("I'm a cherry at pos [" + xPosition + " | " + yPosition + " ]")
          xPosition = xPosition + 1
          eatableList.+=(Cherry("",PointImpl (xPosition,yPosition)))
        }
        case 's' => {
          println("I'm a galaxian Ship at pos [" + xPosition + " | " + yPosition + " ]")
          xPosition = xPosition + 1
          eatableList.+=(GalaxianShip("",PointImpl (xPosition,yPosition)))
        }
        case 'g' => {
          println("I'm a grapes at pos [" + xPosition + " | " + yPosition + " ]")
          xPosition = xPosition + 1
          eatableList.+=(Grapes("",PointImpl (xPosition,yPosition)))
        }
        case 'k' => {
          println("I'm a key at pos [" + xPosition + " | " + yPosition + " ]")
          xPosition = xPosition + 1
          eatableList.+=(Key("",PointImpl (xPosition,yPosition)))
        }

        case 'o' => {
          println("I'm an orange at pos [" + xPosition + " | " + yPosition + " ]")
          xPosition = xPosition + 1
          eatableList.+=(Orange("",PointImpl (xPosition,yPosition)))
        }

        case 's' => {
          println("I'm an apple at pos [" + xPosition + " | " + yPosition + " ]")
          xPosition = xPosition + 1
          eatableList.+=(Strawberry("",PointImpl (xPosition,yPosition)))
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
