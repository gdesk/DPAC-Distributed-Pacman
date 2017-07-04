package client

import client.gameElement.{Block, Eatable, GameItem}
import client.utils.{Dimension, Point}

import scala.collection.mutable.ListBuffer

/** The current playground. Contains all the element created for a game and give control over them.
  *
  * @constructor create an empty playground
  * @param dimension the dimension of the playground
  *
  * @author manuBottax
  */
class Playground (val dimension: Dimension) {

  var blockList: ListBuffer[Block] = ListBuffer.empty[Block]
  var eatableList: ListBuffer[Eatable] = ListBuffer.empty[Eatable]
  // basta così o è più comodo se lo rendiamo una matrice di posizioni ?

  /** Add a block at his own position if is available.
    *
    * If the specified block has a position inside the border of the playground and that position is free the Block is added,
    * otherwise nothing happens and an error message is shown.
    *
    * @param block the block that is wanted to be added to the playground
    */
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


  /** Remove a block from the playground (if it is possible).
    *
    * If in the specified block's position the is an item and that item is a block it is removed,
    * otherwise nothing happens and an error message is shown.
    *
    * @param block the block that is wanted to be removed to the playground
    */
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

  /** Add a eatable at his own position if is available.
    *
    * If the specified eatable has a position inside the border of the playground and that position is free the eatable is added,
    * otherwise nothing happens and an error message is shown.
    *
    * @param eatable the eatable that is wanted to be added to the playground.
    */
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

  /** Remove an eatable from the playground (if it is possible).
    *
    * If in the specified eatable's position the is an item and that item is a eatable it is removed,
    * otherwise nothing happens and an error message is shown.
    *
    * @param eatable the eatable that is wanted to be removed to the playground.
    */
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


  /** Return the [[GameItem]] found in a given position. If the position is free or invalid return an empty [[Option]].
    *
    * @param position the position of the object to be returned.
    * @return the object found at that position if it exist.
    */
  def getElementAtPosition(position: Point[Double, Double]): Option[GameItem[Double, Double]] = {

    if (checkPosition(position)) {
      for (b <- blockList) {
        if (b.position.x == position.x && b.position.y == position.y) return Option(b)
      }

      for (e <- eatableList) {
        if (e.position.x == position.x && e.position.y == position.y) return Option(e)
      }

      Option.empty[GameItem[Double, Double]]
    }

    else {
      Option.empty[GameItem[Double, Double]]
    }
  }

  // check if a given position is inside the playground borders
  private def checkPosition(point: Point[Double, Double]): Boolean = point.x <= dimension.xDimension && point.y <= dimension.yDimension

}

/*

case class OutOfPlaygroundBoundAccessException (private val message: String = "Access Out of Playground Bound",
                                                private val cause: Throwable = None.orNull)
           extends Exception(message, cause)
*/