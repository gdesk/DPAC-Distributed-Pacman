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

  def addBlock(block: Block): Unit = this.blockList += block
  def removeBlock (block: Block): Unit = this.blockList -= block
  def getAllBlocks: List[Block] = blockList.toList

  def addEatable(eatable: Eatable): Unit = this.eatableList += eatable
  def removeEatable (eatable: Eatable): Unit = this.eatableList -= eatable
  def getAllEatable: List[Eatable] = eatableList.toList

  def getElementAtPosition(point: Point[Double, Double]): Option[GameItem[Double, Double]] = {
    for (b <- blockList){
      if (b.position == point) return Option(b)
    }

    for (e <- eatableList){
      if (e.position == point) return Option(e)
    }

    Option.empty[GameItem[Double, Double]]
  }

}
