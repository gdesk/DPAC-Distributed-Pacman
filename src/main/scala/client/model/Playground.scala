package client.model

import client.model.gameElement.{Block, Eatable, GameItem}
import client.model.utils.{Dimension, Point, PointImpl}

import scala.collection.mutable.{HashMap, ListBuffer, Map}

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
  def ground: List[GameItem]

  /**
    * Sets the Map which contains all the elements of the current match.
    *
    * @param elementsMap - the map with all match's elements.
    */
  def ground_=(elementsMap: List[GameItem]): Unit

  /**
    * Returns a List of all the blocks in the current match.
    *
    * @return a List of all the blocks.
    */
  def blocks: List[Block]

  /**
    * Returns the List of all the eatables in the current match.
    *
    * @return the List of all the eatables.
    */
  def eatables: List[Eatable]

  /**
    * Remove an eatable from the playground (if it is possible).
    *
    * If in the specified eatable's position the is an item and that item is a eatable it is removed,
    * otherwise nothing happens and an error message is shown.
    *
    * @param eatable the eatable that is wanted to be removed to the playground.
    */
  def removeEatable(eatable: Eatable): Unit

  /**
    * Returns the List of all the eaten objects in the current match.
    *
    * @return the List of all the eaten objects.
    */
  def eatenObjects: List[Eatable]

  /**
    * Returns a list of all street's positions.
    *
    * @return a list of street's positions.
    */
  def streetPositions: List[Point[Int, Int]]

  /**
    * Returns the [[GameItem]] found in a given position. If the position is free or invalid return an empty [[Option]].
    *
    * @param position the position of the object to be returned.
    * @return the object found at that position if it exist.
    */
  def elementAtPosition(position: Point[Int, Int]): Option[GameItem]
}


/**
  * Implementation of [[Playground]] for a match in a virtual Playground.
  *
  * @constructor create an empty playground.
  */
case class PlaygroundImpl private() extends Playground{

  private var _blocks: ListBuffer[Block] = ListBuffer empty
  private var _eatables: ListBuffer[Eatable] = ListBuffer empty
  private var _eatenObjects: ListBuffer[Eatable] = ListBuffer empty
  private var _streetPositions: ListBuffer[Point[Int,Int]] = ListBuffer empty
  private var _ground: ListBuffer[GameItem] = ListBuffer empty

  override var dimension: Dimension = Dimension(0,0)

  /**
    * Returns the Map which contains all the elements of the current match.
    *
    * @return the map with all match's elements.
    */
  override def ground = _ground toList

  /**
    * Sets the Map which contains all the elements of the current match.
    *
    * @param elementsMap - the map with all match's elements.
    */
  override def ground_=(elementsMap: List[GameItem]) = {
    _ground clear;
    _blocks clear;
    _eatables clear;
    _eatenObjects clear;
    _streetPositions clear;
    elementsMap foreach (e => _ground += e)
    _ground.foreach(p => checkItemPosition(p))
  }

  /**
    * Returns a List of all the blocks in the current match.
    *
    * @return a List of all the blocks.
    */
  override def blocks = {
    if(_blocks.isEmpty) {
      ground foreach { p =>
        if (p.isInstanceOf[Block]) {
          _blocks += p.asInstanceOf[Block]
        }
      }
    }
    _blocks toList
  }

  /**
    * Returns the List of all the eatables in the current match.
    *
    * @return the List of all the eatables.
    */
  override def eatables = {
    if(_eatables.isEmpty) {
      ground.foreach(p =>
        if(p.isInstanceOf[Eatable]) {
          _eatables += (p.asInstanceOf[Eatable])
        }
      )
    }
    _eatables.toList
  }

  /**
    * Remove an eatable from the playground (if it is possible).
    *
    * If in the specified eatable's position the is an item and that item is a eatable it is removed,
    * otherwise nothing happens and an error message is shown.
    *
    * @param eatable the eatable that is wanted to be removed to the playground.
    */
  override def removeEatable(eatable: Eatable) = {
    var eatableObj: Eatable = _ground.find(p => p equals eatable).get.asInstanceOf[Eatable]
    _ground -= eatableObj
    _eatables -= eatableObj
    _eatenObjects += eatableObj
  }

  /**
    * Returns the List of all the eaten objects in the current match.
    *
    * @return the List of all the eaten objects.
    */
  override def eatenObjects = _eatenObjects.toList

  /**
    * Checks if item's position is inside the playground and if is empty.
    *
    * @param item - the item to which the position must be controlled.
    */
  private def checkItemPosition(item: GameItem) = {
    checkPosition(item.position) match {
      case false =>
        throw new OutOfPlaygroundBoundAccessException("Position ("+ item.position.x + "," + item.position.y + ") is out of playground!")
      case true =>
        hawManyItemInPosition(item position) match {
          case x if(x>1) =>
            throw new AlredyUsedPositionException("Position ("+ item.position.x + "," + item.position.y + ") already occupied by another item!")
          case _ =>
        }
        /*
        val option: Option[GameItem] = elementAtPosition(item position)
        ((option isEmpty) || (option.get equals item)) match {
          case true =>
            val entry: (Point[Int,Int], GameItem) = (item.position, item)
            this.ground += entry
          case false =>
            throw new AlredyUsedPositionException("Position ("+ item.position.x + "," + item.position.y + ") already occupied by another item!")
        }
        */
    }
    /*
    // se aggiungo fuori dalla posizione mi deve dare errore
    if(!checkPosition(item.position)) {
      //println("Position ("+ item.position.x + "," + item.position.y + ") is out of playground!")
      throw new OutOfPlaygroundBoundAccessException("Position ("+ item.position.x + "," + item.position.y + ") is out of playground!")
    } else {
      // controllo se esiste già qualcosa in quella posizione e nel caso do errore
      val option: Option[GameItem] = elementAtPosition(item position)
      if((option.isEmpty) || (option.get equals item)) {
        val entry: (Point[Int,Int], GameItem) = (item.position, item)
        this.ground += entry
      } else {
        //println("Position already used, cannot add block !")
        throw new AlredyUsedPositionException("Position ("+ item.position.x + "," + item.position.y + ") already occupied by another item!")
      }
    }
    */
  }

  /**
    * Checks if a given position is inside the playground.
    *
    * @param position - the position to check.
    * @return true if the position is inside the playground, false otherwise.
    */
  private def checkPosition(position: Point[Int, Int]): Boolean = position.x < dimension.x && position.y < dimension.y && position.x >= 0 && position.y >= 0

  /**
    * Computes how many items are in the same position.
    *
    * @param position - the position to check.
    * @return the number of items in the same position.
    */
  private def hawManyItemInPosition(position: Point[Int, Int]): Int = _ground filter (e => e.position equals position) size

  /**
    * Returns a list of all street's positions.
    *
    * @return a list of street's positions.
    */
  override def streetPositions = {
    if(_streetPositions.isEmpty) {
      for(i <- 0 to dimension.x-1) {
        for(j <- 0 to dimension.y-1) {
          if((_ground filter (e => e.position equals PointImpl(i,j)) size) equals 0) _streetPositions += PointImpl(i,j)
        }
      }
    }
    _streetPositions toList
  }

  /**
    * Returns the [[GameItem]] found in a given position. If the position is free or invalid return an empty [[Option]].
    *
    * @param position the position of the object to be returned.
    * @return the object found at that position if it exist.
    */
  override def elementAtPosition(position: Point[Int,Int]) = {
    if(checkPosition(position)) {
      _ground find (e => e.position equals position)
    } else {
      throw new OutOfPlaygroundBoundAccessException("Position ("+ position.x + "," + position.y + ") is out of playground!")    }
  }
}

object PlaygroundImpl {

  private var _instance: PlaygroundImpl = null

  /**
    * Returns the only {@PlaygroundImpl}'s instance. Pattern Singleton.
    *
    * @return the only PlaygroundImpl's instance.
    */
  def instance(): PlaygroundImpl = {
    if(_instance == null) _instance = PlaygroundImpl()
    _instance
  }

}

case class OutOfPlaygroundBoundAccessException(private val message: String = "") extends Exception(message)

case class AlredyUsedPositionException(private val message: String = "") extends Exception(message)