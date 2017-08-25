package client.view

import org.scalatest.FunSuite

/**
  * Created by chiaravarini on 03/07/17.
  */
class BlockViewTest extends FunSuite {

  test("Correct access to block images"){

    val resolution: String = Utils.getResolution().asString()

    val blockView: BlockView = new BlockViewImpl()

    var blockImage = Utils.getImage("block/"+resolution+"/horizontalBlock")
    assert(blockView.getHorizontal() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/leftEnd")
    assert(blockView.getLeftEnd() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/leftOpen")
    assert(blockView.getVerticalLeft() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/lowerEnd")
    assert(blockView.getLowerEnd() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/lowerLeftEdge")
    assert(blockView.getLowerLeftCorner() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/lowerOpen")
    assert(blockView.getHorizontalBottom() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/lowerRightEdge")
    assert(blockView.getLowerRightCorner() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/rightEnd")
    assert(blockView.getRightEnd() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/rightOpen")
    assert(blockView.getVerticalRight() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/upperEnd")
    assert(blockView.getUpperEnd() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/upperLeftEdge")
    assert(blockView.getUpperLeftCorner() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/upperOpen")
    assert(blockView.getHorizontalUp() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/upperRightEdge")
    assert(blockView.getUpperRightCorner() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/verticalBlock")
    assert(blockView.getVertical() == blockImage)

    blockImage = Utils.getImage("block/"+resolution+"/singleBlock")
    assert(blockView.getSingle() == blockImage)
  }

}
