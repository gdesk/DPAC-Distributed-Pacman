package client.model

import client.model.gameElement.{Block, Eatable, GameItem}
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

  /**
    * Returns a List of all the blocks in the current match.
    *
    * @return a List of all the blocks.
    */
  def blocks: List[Block]

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
    * Returns the List of all the eatables in the current match.
    *
    * @return the List of all the eatables.
    */
  def eatables: List[Eatable]

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
  def streetPositions(): List[Point[Int, Int]]

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

  private var blockList: List[Block] = List empty
  private var eatableList: List[Eatable] = List empty
  private var eatenList: List[Eatable] = List empty
  private var streetPositionsList: List[Point[Int,Int]] = List empty
  private var groundMap: Map[Point[Int,Int],GameItem] = HashMap empty

  override var dimension: Dimension = Dimension(0,0)

  /**
    * Returns the Map which contains all the elements of the current match.
    *
    * @return the map with all match's elements.
    */
  override def ground = groundMap

  /**
    * Sets the Map which contains all the elements of the current match.
    *
    * @param elementsMap - the map with all match's elements.
    */
  override def ground_=(elementsMap: Map[Point[Int, Int], GameItem]) = {
    groundMap = elementsMap
    groundMap.foreach(p => checkItemPosition(p._2))
  }

  /**
    * Returns a List of all the blocks in the current match.
    *
    * @return a List of all the blocks.
    */
  override def blocks: List[Block] = {
    if(blockList.isEmpty) {
      ground.foreach(p =>
        if(p._2.isInstanceOf[Block]) {
          p._2.asInstanceOf[Block]
          p._2 :: blockList
        }
      )
    }
    blockList
  }

  /**
    * Returns the List of all the eatables in the current match.
    *
    * @return the List of all the eatables.
    */
  override def eatables: List[Eatable] = {
    if(eatableList.isEmpty) {
      ground.foreach(p =>
        if(p._2.isInstanceOf[Eatable]) {
          p._2.asInstanceOf[Eatable]
          p._2 :: eatableList
        }
      )
    }
    eatableList
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
    var point: Point[Int, Int] = groundMap.find(p => p._2 equals eatable).get._1
    val eatableObj = (groundMap get point get).asInstanceOf[Eatable]
    eatableObj :: eatenList
    groundMap -= point
  }

  /**
    * Returns the List of all the eaten objects in the current match.
    *
    * @return the List of all the eaten objects.
    */
  override def eatenObjects: List[Eatable] = eatenList

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

  /**
    * Returns a list of all street's positions.
    *
    * @return a list of street's positions.
    */
  override def streetPositions(): List[Point[Int, Int]] = {
    if(streetPositionsList.isEmpty) {
      for(i <- 0 to dimension.x) {
        for(j <- 0 to dimension.y) {
          if(!ground.contains(PointImpl(i,j))) PointImpl(i,j) :: streetPositionsList
        }
      }
    }
    streetPositionsList
  }

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