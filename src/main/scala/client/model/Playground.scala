package client.model

import alice.tuprolog.Theory
import client.model.gameElement.{Block, Eatable, GameItem}
import client.model.utils._

import scala.collection.mutable.ListBuffer

/**
  * Represents current match's playground.
  *
  * @author Manuel Bottazzi
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
    * Returns the List which contains all the elements of the current match.
    *
    * @return the list with all match's elements.
    */
  def ground: List[GameItem]

  /**
    * Sets the list of all the elements in the current match.
    *
    * @param elements - the list with all match's elements.
    * @throws AlredyUsedPositionException when the position is already occupied by another object.
    */
  def ground_=(elements: List[GameItem]): Unit

  /**
    * Returns the list of all the blocks in the current match.
    *
    * @return the list of all blocks.
    */
  def blocks: List[Block]

  /**
    * Returns the list of all the eatable objects in the current match.
    *
    * @return the list of all eatable objects.
    */
  def eatables: List[Eatable]

  /**
    * Removes an eatable from the playground.
    *
    * @param eatable the eatable to remove from the playground.
    * @throws EatablesNotPresentInThisPlaygroundException when the eatable object to be removed isn't in the playground.
    */
  def removeEatable(eatable: Eatable): Unit

  /**
    * Returns the list of all the eaten objects in the current match.
    *
    * @return the list of all the eaten objects.
    */
  def eatenObjects: List[Eatable]

  /**
    * Returns a list of all street's positions.
    *
    * @return a list of street's positions.
    */
  def streetPositions: List[Point[Int, Int]]

  /**
    * Returns an Option containing the item found in the given position or None if the position is free.
    *
    * @param position - the position to check.
    * @throws OutOfPlaygroundBoundAccessException when the position is out of the ground.
    * @return an Option containing the item found in the given position or None if the position is free.
    */
  def elementAtPosition(position: Point[Int, Int]): Option[GameItem]

  /**
    * Checks if a given position is inside the playground.
    *
    * @param position - the position to check.
    * @throws OutOfPlaygroundBoundAccessException when the position is out of the ground.
    */
  def checkPosition(position: Point[Int, Int]): Unit
}

/**
  * Represents the implementation of the current match's playground.
  *
  * @author Margherita Pecorelli
  */
object  PlaygroundImpl extends Playground{

 // private var engine = ScalaProlog.mkPrologEngine(new Theory(new FileInputStream("src/main/prolog/dpac-prolog.pl")))
  private var _blocks: ListBuffer[Block] = ListBuffer empty
  private var _eatables: ListBuffer[Eatable] = ListBuffer empty
  private var _eatenObjects: ListBuffer[Eatable] = ListBuffer empty
  private var _streetPositions: ListBuffer[Point[Int,Int]] = ListBuffer empty
  private var _ground: ListBuffer[GameItem] = ListBuffer empty

  override var dimension: Dimension = Dimension(0,0)

  /**
    * Returns the List which contains all the elements of the current match.
    *
    * @return the list with all match's elements.
    */
  override def ground = _ground.toList

  /**
    * Sets the list of all the elements in the current match.
    *
    * @param elements - the list with all match's elements.
    * @throws AlredyUsedPositionException when the position is already occupied by another object.
    * @throws OutOfPlaygroundBoundAccessException when the position is out of the ground.
    */
  override def ground_=(elements: List[GameItem]) = {
    _ground.clear
    _blocks.clear
    _eatables.clear
    _eatenObjects.clear
    _streetPositions.clear
    elements.foreach(e => _ground += e)
    _ground.foreach(p => checkItemPosition(p))

    blocks
    streetPositions
    var theory = ""
    _streetPositions.foreach(s => theory = theory + "street("+s.x+","+s.y+")." + "\n")
    PrologConfig.addStreets(new Theory(theory))
    println("AGGIUNTA:"+PrologConfig.getPrologEngine.getTheory.toString)
  }

  /**
    * Checks if item's position is inside the playground and if it's empty.
    *
    * @param item - the item to which the position must be controlled.
    * @throws AlredyUsedPositionException when the position is already occupied by another object.
    * @throws OutOfPlaygroundBoundAccessException when the position is out of the ground.
    */
  private def checkItemPosition(item: GameItem) = {
    checkPosition(item.position)
    hawManyItemInPosition(item.position) match {
      case x if x>1 =>
        throw new AlredyUsedPositionException("Position ("+ item.position.x + "," + item.position.y + ") already occupied by another item!")
      case _ =>
    }
  }

  /**
    * Calculates how many items are in the same position.
    *
    * @param position - the position to check.
    * @return the number of items in the same position.
    */
  private def hawManyItemInPosition(position: Point[Int, Int]): Int = _ground.filter(e => e.position equals position).size

  /**
    * Returns the list of all the blocks in the current match.
    *
    * @return the list of all blocks.
    */
  override def blocks = {
    if(_blocks isEmpty) ground.foreach(p => if(p.isInstanceOf[Block]) _blocks += p.asInstanceOf[Block])
    _blocks.toList
  }

  /**
    * Returns the list of all the eatable objects in the current match.
    *
    * @return the list of all eatable objects.
    */
  override def eatables = {
    if(_eatables isEmpty) ground.foreach(p => if(p.isInstanceOf[Eatable]) _eatables += (p.asInstanceOf[Eatable]))
    _eatables.toList
  }

  /**
    * Removes an eatable from the playground.
    *
    * @param eatable the eatable to remove from the playground.
    * @throws EatablesNotPresentInThisPlaygroundException when the eatable object to be removed isn't in the playground.
    */
  override def removeEatable(eatable: Eatable) = {
    val option = _ground.find(p => p equals eatable)
    option nonEmpty match {
      case true =>
        val eatableObj: Eatable = option.get.asInstanceOf[Eatable]
        _ground -= eatableObj
        _eatables -= eatableObj
        _eatenObjects += eatableObj
      case _ =>
        throw EatablesNotPresentInThisPlaygroundException("The eatable object" + eatable + "is not present in this playground, so it can not be removed!")
    }
  }

  /**
    * Returns the list of all the eaten objects in the current match.
    *
    * @return the list of all the eaten objects.
    */
  override def eatenObjects = _eatenObjects.toList

  /**
    * Returns a list of all street's positions.
    *
    * @return a list of street's positions.
    */
  override def streetPositions = {
    if(_streetPositions isEmpty) {
      for(i <- 0 until dimension.x) {
        for(j <- 0 until dimension.y) {
          if((_blocks.filter(e => e.position equals PointImpl(i,j)).size) equals 0) _streetPositions += PointImpl(i,j)
        }
      }
    }
    _streetPositions.toList
  }

  /**
    * Returns an Option containing the item found in the given position or None if the position is free.
    *
    * @param position - the position to check.
    * @throws OutOfPlaygroundBoundAccessException when the position is out of the ground.
    * @return an Option containing the item found in the given position or None if the position is free.
    */
  override def elementAtPosition(position: Point[Int,Int]) = {
    checkPosition(position)
    _ground.find(e => e.position equals position)
  }

  /**
    * Checks if a given position is inside the playground.
    *
    * @param position - the position to check.
    * @throws OutOfPlaygroundBoundAccessException when the position is out of the ground.
    */
  override def checkPosition(position: Point[Int, Int]) = {
    if(!(position.x < dimension.x && position.y < dimension.y && position.x >= 0 && position.y >= 0)) {
      throw new OutOfPlaygroundBoundAccessException("Position ("+ position.x + "," + position.y + ") is out of playground!")
    }
  }

}

/**
  * Represents the exception throws when trying to access in a position out of the playground.
  *
  * @param message - message throws by the exception.
  */
case class OutOfPlaygroundBoundAccessException(private val message: String = "") extends Exception(message)

/**
  * Represents the exception throws when a playground's position is occupied by two or more objects.
  *
  * @param message - message throws by the exception.
  */
case class AlredyUsedPositionException(private val message: String = "") extends Exception(message)

/**
  * Represents the exception throws when trying to find a specific eatable object that is not in the playground.
  *
  * @param message - message throws by the exception.
  */
case class EatablesNotPresentInThisPlaygroundException(private val message: String = "") extends Exception(message)