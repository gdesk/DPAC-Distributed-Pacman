package client

import client.gameElement.{Block, Eatable, GameItem}
import client.utils.{Dimension, Point}

import scala.collection.mutable.ListBuffer

/**
  * Created by ManuBottax on 25/06/2017.
  */

class Playground (val dimension: Dimension) {

  var blockList: ListBuffer[Block] = ListBuffer.empty[Block]
  var eatableList: ListBuffer[Eatable] = ListBuffer.empty[Eatable]
  // basta così o è più comodo se lo rendiamo una matrice di posizioni ?

  def addBlock(block: Block): Unit = {

    // se aggiungo fuori dalla posizione mi deve dare errore
    if (! checkPosition(block.position))
      println("Block position is out of playground, cannot add it !")
    //throw new OutOfPlaygroundBoundAccessException
    else {
      // controllo se esiste già qualcosa in quella posizione e nel caso do errore
      if (getElementAtPosition(block.position).isEmpty)
        this.blockList += block
      else
        println("Position already used, cannot add block !")
      //throw new AlredyUsedPositionException
    }
  }
  // e se la dimension copre fuori dai bordi ? devo controllare anche le dimensioni di quei cosi
  // fare questa cosa con i blocchi dimensionati è esageratamente complicato


  def removeBlock (block: Block): Unit = {
    val item: Option[GameItem[Double,Double]] = getElementAtPosition(block.position)
    item match {
      case None => println("Block not in playground, cannot remove !")
      case Some(b) => {
        if (b.isInstanceOf[Block]) this.blockList -= item.get.asInstanceOf[Block]
        else println("element at that position is not a block, cannot remove it !")
      }
    }
  }

  def getAllBlocks: List[Block] = blockList.toList
  // questo metodo non serve se i campi non sono private ( hanno getter e setter essendo var )

  def addEatable(eatable: Eatable): Unit = {

    // se aggiungo fuori dalla posizione mi deve dare errore
    if (!checkPosition(eatable.position))
      println("Eatable position is out of playground, cannot add it !")
    //throw new OutOfPlaygroundBoundAccessException
    else {
      // controllo se esiste già qualcosa in quella posizione e nel caso do errore
      if (getElementAtPosition(eatable.position).isEmpty)
        this.eatableList += eatable
      else
        println("Position already used, cannot add eatable !")
      //throw new AlredyUsedPositionException
    }
  }


  def removeEatable (eatable: Eatable): Unit = {
    val item: Option[GameItem[Double,Double]] = getElementAtPosition(eatable.position)
    item match {
      case None => println("Eatable not in playground, cannot remove !")
      case Some(e) => {
        if (e.isInstanceOf[Eatable]) this.eatableList -= item.get.asInstanceOf[Eatable]
        else println("element at that position is not an eatable, cannot remove it !")
      }
    }
  }
  def getAllEatable: List[Eatable] = eatableList.toList

  def getElementAtPosition(point: Point[Double, Double]): Option[GameItem[Double, Double]] = {

    if (checkPosition(point)) {
      for (b <- blockList) {
        if (b.position.x == point.x && b.position.y == point.y) return Option(b)
      }

      for (e <- eatableList) {
        if (e.position.x == point.x && e.position.y == point.y) return Option(e)
      }

      Option.empty[GameItem[Double, Double]]
    }

    else {
      Option.empty[GameItem[Double, Double]]
    }
  }

  private def checkPosition(point: Point[Double, Double]): Boolean = point.x <= dimension.xDimension && point.y <= dimension.yDimension

}

/*

case class OutOfPlaygroundBoundAccessException (private val message: String = "Access Out of Playground Bound",
                                                private val cause: Throwable = None.orNull)
           extends Exception(message, cause)
*/