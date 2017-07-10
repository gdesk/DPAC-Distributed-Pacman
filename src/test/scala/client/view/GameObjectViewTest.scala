package client.view

import org.scalatest.FunSuite
import client.view.utils.FruitsImages._

/**
  * Created by chiaravarini on 03/07/17.
  */
class GameObjectViewTest extends FunSuite{

  test("Correct access to game object images"){

    val gameObjectView: GameObjectView = new GameObjectViewImpl()

    var gameObjectImage = Utils.getImage("dot/YellowDot")
    assert(gameObjectView.getDot() == gameObjectImage)

    gameObjectImage = Utils.getImage("pill/YellowPill")
    assert(gameObjectView.getPill() == gameObjectImage)

    var fruitPath = "fruit/"+Utils.getResolution().asString()+"/"

    gameObjectImage = Utils.getImage(fruitPath+"apple")
    assert(gameObjectView.getFruit(APPLE) == gameObjectImage)

    gameObjectImage = Utils.getImage(fruitPath+"bell")
    assert(gameObjectView.getFruit(BELL) == gameObjectImage)

    gameObjectImage = Utils.getImage(fruitPath+"cherry")
    assert(gameObjectView.getFruit(CHERRY) == gameObjectImage)

    gameObjectImage = Utils.getImage(fruitPath+"galaxian")
    assert(gameObjectView.getFruit(GALAXIAN) == gameObjectImage)

    gameObjectImage = Utils.getImage(fruitPath+"grapes")
    assert(gameObjectView.getFruit(GRAPES) == gameObjectImage)

    gameObjectImage = Utils.getImage(fruitPath+"key")
    assert(gameObjectView.getFruit(KEY) == gameObjectImage)

    gameObjectImage = Utils.getImage(fruitPath+"orange")
    assert(gameObjectView.getFruit(ORANGE) == gameObjectImage)

    gameObjectImage = Utils.getImage(fruitPath+"strawberry")
    assert(gameObjectView.getFruit(STRAWBERRY) == gameObjectImage)

  }

}
