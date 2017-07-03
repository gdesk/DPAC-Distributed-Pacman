package client

import java.util

import client.gameElement.{VirtualBlock, Eatable, GameItem}
import client.utils.{Dimension, Point, Position}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/** The playground for a game. Contains all the element for a single game and give control over them
  *
  * @author manuBottax
  */
trait Playground {

  /** the dimension of the playground*/
  def dimension: Dimension

  /** A container for the element of the current game. */
  def ground: mutable.Map[Position[Int,Int],GameItem]

  /** Add a block at his own position if is available.
    *
    * If the specified block has a position inside the border of the playground and that position is free the Block is added,
    * otherwise nothing happens and an error message is shown.
    *
    * @param block the block that is wanted to be added to the playground
    */
  def addBlock(block: VirtualBlock): Unit

  /** Remove a block from the playground (if it is possible).
    *
    * If in the specified block's position the is an item and that item is a block it is removed,
    * otherwise nothing happens and an error message is shown.
    *
    * @param block the block that is wanted to be removed to the playground
    */
  def removeBlock (block: VirtualBlock): Unit

  /** return a List of all the blocks in the current ground */
  def getAllBlocks: List[VirtualBlock]

  /** Add a eatable at his own position if is available.
    *
    * If the specified eatable has a position inside the border of the playground and that position is free the eatable is added,
    * otherwise nothing happens and an error message is shown.
    *
    * @param eatable the eatable that is wanted to be added to the playground.
    */
  def addEatable(eatable: Eatable): Unit

  /** Remove an eatable from the playground (if it is possible).
    *
    * If in the specified eatable's position the is an item and that item is a eatable it is removed,
    * otherwise nothing happens and an error message is shown.
    *
    * @param eatable the eatable that is wanted to be removed to the playground.
    */
  def removeEatable (eatable: Eatable): Unit

  /** return a List of all the eatables in the current ground */
  def getAllEatable: List[Eatable]

  /** Return the [[GameItem]] found in a given position. If the position is free or invalid return an empty [[Option]].
    *
    * @param position the position of the object to be returned.
    * @return the object found at that position if it exist.
    */
  def getElementAtPosition(position: Position[Int, Int]): Option[GameItem]
}


/** Implementation of [[Playground]] for a match in a virtual Playground.
  *
  * @constructor create an empty playground
  * @param dimension the dimension of the playground
  */
class VirtualPlayground(override val dimension: Dimension) extends Playground{

  var ground: mutable.Map[Position[Int,Int],GameItem] = new mutable.HashMap[Position[Int,Int],GameItem]()

  private var blockList: ListBuffer[VirtualBlock] = ListBuffer.empty[VirtualBlock]
  private var eatableList: ListBuffer[Eatable] = ListBuffer.empty[Eatable]

  def addBlock(block: VirtualBlock): Unit = {

    // se aggiungo fuori dalla posizione mi deve dare errore
    if (! checkPosition(block.position))
      println("Block position is out of playground, cannot add it !")
    //throw new OutOfPlaygroundBoundAccessException
    else {
      // controllo se esiste già qualcosa in quella posizione e nel caso do errore
      if (getElementAtPosition(block.position).isEmpty) {
        this.blockList += block
        val entry: (Position[Int,Int], GameItem) = (block.position, block)
        this.ground += entry
      }

      else
        println("Position already used, cannot add block !")
      //throw new AlredyUsedPositionException
    }
  }
  // e se la dimension copre fuori dai bordi ? devo controllare anche le dimensioni di quei cosi
  // fare questa cosa con i blocchi dimensionati è esageratamente complicato

  def removeBlock (block: VirtualBlock): Unit = {
    val item: Option[GameItem] = getElementAtPosition(block.position)
    item match {
      case None => println("Block not in playground, cannot remove !")
      case Some(b) => {
        if (b.isInstanceOf[VirtualBlock]) {
          this.blockList -= item.get.asInstanceOf[VirtualBlock]
          this.ground -= item.get.asInstanceOf[VirtualBlock].position
        }
        else println("element at that position is not a block, cannot remove it !")
      }
    }
  }

  def getAllBlocks: List[VirtualBlock] = blockList.toList


  def addEatable(eatable: Eatable): Unit = {

    // se aggiungo fuori dalla posizione mi deve dare errore
    if (!checkPosition(eatable.position))
      println("Eatable position is out of playground, cannot add it !")
    //throw new OutOfPlaygroundBoundAccessException
    else {
      // controllo se esiste già qualcosa in quella posizione e nel caso do errore
      if (getElementAtPosition(eatable.position).isEmpty){
        this.eatableList += eatable
        val entry: (Position[Int,Int], GameItem) = (eatable.position, eatable)
        this.ground += entry
      }

      else
        println("Position already used, cannot add eatable !")
      //throw new AlredyUsedPositionException
    }
  }

  def removeEatable (eatable: Eatable): Unit = {
    val item: Option[GameItem] = getElementAtPosition(eatable.position)
    item match {
      case None => println("Eatable not in playground, cannot remove !")
      case Some(e) => {
        if (e.isInstanceOf[Eatable]) {
          this.eatableList -= item.get.asInstanceOf[Eatable]
          this.ground -= item.get.asInstanceOf[Eatable].position
        }
        else println("element at that position is not an eatable, cannot remove it !")
      }
    }
  }

  def getAllEatable: List[Eatable] = eatableList.toList

  def getElementAtPosition(position: Position[Int,Int]): Option[GameItem] = {

    if (checkPosition(position))
      this.ground.get(position)
    else
      Option.empty[GameItem]

    /*
    if (checkPosition(position)) {
      for (b <- blockList) {
        if (b.position.x == position.x && b.position.y == position.y) return Option(b)
      }

      for (e <- eatableList) {
        if (e.position.x == position.x && e.position.y == position.y) return Option(e)
      }

      Option.empty[GameItem]
    }

    else {
      Option.empty[GameItem]
    }
    */
  }

  // check if a given position is inside the playground borders
  private def checkPosition(point: Position[Int, Int]): Boolean = point.x <= dimension.xDimension && point.y <= dimension.yDimension

}

/*

case class OutOfPlaygroundBoundAccessException (private val message: String = "Access Out of Playground Bound",
                                                private val cause: Throwable = None.orNull)
           extends Exception(message, cause)
*/