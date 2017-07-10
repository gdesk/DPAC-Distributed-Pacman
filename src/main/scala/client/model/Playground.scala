package client.model

import client.model.character.gameElement.{Block, Eatable, GameItem}
import client.model.utils.{Dimension, Point, PointImpl}

import scala.collection.mutable.{HashMap, Map}

/**
  * The game's playground. It contains all the elements in the game platform (except characters).
  *
  * @author manuBottax
  * @author Margherita Pecorelli
  */
trait Playground {

  /**
    * Returns the dimension of the playground.
    *
    * @return the dimension of the playground.
    */
  def dimension: Dimension

  /**
    * Sets the dimension of the playground.
    *
    * @param dimension - the dimension of the playground.
    */
  def dimension_=(dimension: Dimension): Unit

  /**
    * Returns the Map which contains all the elements of the current match.
    *
    * @return the map with all match's elements.
    */
  def ground: Map[Point[Int,Int],GameItem]

  /**
    * Sets the Map which contains all the elements of the current match.
    *
    * @param elementsMap - the map with all match's elements.
    */
  def ground_=(elementsMap: Map[Point[Int,Int],GameItem]): Unit

  /*
  /**
    * Add a block at his own position if is available.
    *
    * If the specified block has a position inside the border of the playground and that position is free the Block is added,
    * otherwise nothing happens and an error message is shown.
    *
    * @param block the block that is wanted to be added to the playground
    */
  def addBlock(block: Block): Unit

  /**
    * Remove a block from the playground (if it is possible).
    *
    * If in the specified block's position the is an item and that item is a block it is removed,
    * otherwise nothing happens and an error message is shown.
    *
    * @param block the block that is wanted to be removed to the playground
    */
  def removeBlock (block: Block): Unit
  */

  /**
    * Returns a List of all the blocks in the current match.
    *
    * @return a List of all the blocks.
    */
  def blocks: List[Block]

  /**
    * Sets the playground of the current match with the blocks' list.
    *
    * @param blocks - current match's blocks' list
    */
  def blocks_=(blocks: List[Block]): Unit

  /*
  /**
    * Add a eatable at his own position if is available.
    *
    * If the specified eatable has a position inside the border of the playground and that position is free the eatable is added,
    * otherwise nothing happens and an error message is shown.
    *
    * @param eatable the eatable that is wanted to be added to the playground.
    */
  def addEatable(eatable: Eatable): Unit

  /**
    * Remove an eatable from the playground (if it is possible).
    *
    * If in the specified eatable's position the is an item and that item is a eatable it is removed,
    * otherwise nothing happens and an error message is shown.
    *
    * @param eatable the eatable that is wanted to be removed to the playground.
    */
  def removeEatable(eatable: Eatable): Unit
  */

  /**
    * Returns a List of all the eatables in the current match.
    *
    * @return a List of all the eatables.
    */
  def eatables: List[Eatable]

  /**
    *Sets the playground of the current match with the eatables' list.
    *
    * @param eatables - current match's eatables' list
    */
  def eatables_=(eatables: List[Eatable]): Unit

  /**
    * Returns the [[GameItem]] found in a given position. If the position is free or invalid return an empty [[Option]].
    *
    * @param position the position of the object to be returned.
    * @return the object found at that position if it exist.
    */
  def elementAtPosition(position: Point[Int, Int]): Option[GameItem]

  /**
    * Returns a list of all street's positions.
    *
    * @return a list of street's positions.
    */
  def streetPositions(): List[Point[Int, Int]]
}


/**
  * Implementation of [[Playground]] for a match in a virtual Playground.
  *
  * @constructor create an empty playground.
  */
case class PlaygroundImpl private() extends Playground{

  //private var blockList: ListBuffer[Block] = ListBuffer.empty[Block]
  //private var eatableList: ListBuffer[Eatable] = ListBuffer.empty[Eatable]
  private var blockList: List[Block] = List empty
  private var eatableList: List[Eatable] = List empty

  override var dimension: Dimension = Dimension(0,0)
  override var ground: Map[Point[Int,Int],GameItem] = HashMap[Point[Int,Int],GameItem]()

  /*
  /**
    * Add a block at his own position if is available.
    *
    * If the specified block has a position inside the border of the playground and that position is free the Block is added,
    * otherwise nothing happens and an error message is shown.
    *
    * @param block the block that is wanted to be added to the playground
    */
  override def addBlock(block: Block): Unit = {
    // se aggiungo fuori dalla posizione mi deve dare errore
    if (! checkPosition(block.position))
      println("Block position is out of playground, cannot add it !")
    //throw new OutOfPlaygroundBoundAccessException
    else {
      // controllo se esiste già qualcosa in quella posizione e nel caso do errore
      if (elementAtPosition(block.position).isEmpty) {
        this.blockList += block
        val entry: (Point[Int,Int], GameItem) = (block.position, block)
        this.ground += entry
      }
      else
        println("Position already used, cannot add block !")
      //throw new AlredyUsedPositionException
    }
  }*/
  // e se la dimension copre fuori dai bordi ? devo controllare anche le dimensioni di quei cosi
  // fare questa cosa con i blocchi dimensionati è esageratamente complicato

  /*
  /**
    * Remove a block from the playground (if it is possible).
    *
    * If in the specified block's position the is an item and that item is a block it is removed,
    * otherwise nothing happens and an error message is shown.
    *
    * @param block the block that is wanted to be removed to the playground
    */
  override def removeBlock (block: Block): Unit = {
    val item: Option[GameItem] = elementAtPosition(block.position)
    item match {
      case None => println("Block not in playground, cannot remove !")
      case Some(b) => {
        if (b.isInstanceOf[Block]) {
          this.blockList -= item.get.asInstanceOf[Block]
          this.ground -= item.get.asInstanceOf[Block].position
        }
        else println("element at that position is not a block, cannot remove it !")
      }
    }
  }*/

  /**
    * Returns a List of all the blocks in the current match.
    *
    * @return a List of all the blocks.
    */
  override def blocks: List[Block] = blockList

  /**
    * Sets the playground of the current match with the blocks' list.
    *
    * @param blocks - current match's blocks' listscala.collection.mutable.HashMap
    */
  override def blocks_=(blocks: List[Block]) = {
    blockList = blocks
    blockList foreach(b => checkItemPosition(b))
  }

  /**
    * Returns a List of all the eatables in the current match.
    *
    * @return a List of all the eatables.
    */
  override def eatables: List[Eatable] = eatableList

  /**
    * Sets the playground of the current match with the eatables' list.
    *
    * @param eatables - current match's eatables' list
    */
  override def eatables_=(eatables: List[Eatable]) = {
    eatableList = eatables
    eatableList foreach(e => checkItemPosition(e))
  }

  private def checkItemPosition(item: GameItem): Unit = {
    // se aggiungo fuori dalla posizione mi deve dare errore
    if(!checkPosition(item.position)) {
      println("Position ("+ item.position.x + "," + item.position.y + ") is out of playground!")
      //throw new OutOfPlaygroundBoundAccessException
    } else {
      // controllo se esiste già qualcosa in quella posizione e nel caso do errore
      val option: Option[GameItem] = elementAtPosition(item position)
      if((option.isEmpty) || (option.get equals item)) {
        val entry: (Point[Int,Int], GameItem) = (item.position, item)
        this.ground += entry
      } else {
        println("Position already used, cannot add block !")
        //throw new AlredyUsedPositionException
      }
    }
  }

  /**
    * Checks if a given position is inside the playground.
    *
    * @param point - the position to check.
    * @return true if the position is inside the playground, false otherwise.
    */
  private def checkPosition(point: Point[Int, Int]): Boolean = point.x <= dimension.x && point.y <= dimension.y

  /*
  def addEatable(eatable: Eatable): Unit = {
    // se aggiungo fuori dalla posizione mi deve dare errore
    if (!checkPosition(eatable.position))
      println("Eatable position is out of playground, cannot add it !")
    //throw new OutOfPlaygroundBoundAccessException
    else {
      // controllo se esiste già qualcosa in quella posizione e nel caso do errore
      if (elementAtPosition(eatable.position).isEmpty){
        this.eatableList += eatable
        val entry: (Point[Int,Int], GameItem) = (eatable.position, eatable)
        this.ground += entry
      }
      else
        println("Position already used, cannot add eatable !")
      //throw new AlredyUsedPositionException
    }
  }
  */

  /*def removeEatable (eatable: Eatable): Unit = {
    val item: Option[GameItem] = elementAtPosition(eatable.position)
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
  }*/

  /**
    * Returns the [[GameItem]] found in a given position. If the position is free or invalid return an empty [[Option]].
    *
    * @param position the position of the object to be returned.
    * @return the object found at that position if it exist.
    */
  override def elementAtPosition(position: Point[Int,Int]): Option[GameItem] = {
    if(checkPosition(position)) {
      this.ground.get(position)
    } else {
      Option.empty[GameItem]
    }
    /*if (checkPosition(position)) {
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
    }*/
  }

  /**
    * Returns a list of all street's positions.
    *
    * @return a list of street's positions.
    */
  override def streetPositions(): List[Point[Int, Int]] = {
    var street: List[Point[Int, Int]] = List.empty
    for(i <- 0 to dimension.x) {
      for(j <- 0 to dimension.y) {
        if(!ground.contains(PointImpl(i,j))) PointImpl(i,j) :: street
      }
    }
    street
  }
}

object PlaygroundImpl {

  private var _instance: PlaygroundImpl = null

  /**
    * Returns the only {@PlaygroundImpl}'s instance. Pattern Singleton.
    *
    * @return the only PlaygroundImpl's instance.
    */
  def instance(): Playground = {
    if(_instance == null) _instance = PlaygroundImpl()
    _instance
  }
}

/*

case class OutOfPlaygroundBoundAccessException (private val message: String = "Access Out of Playground Bound",
                                                private val cause: Throwable = None.orNull)
           extends Exception(message, cause)
*/