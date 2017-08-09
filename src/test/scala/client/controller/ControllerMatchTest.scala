package client.controller

import org.scalatest.FunSuite

class ControllerCharacterTest() extends FunSuite {

  val controller: ControllerMatch = BaseControllerMatch.instance()

  test("singleton") {
    assert(controller == BaseControllerMatch.instance())
  }

  /*
  Singleton

  def getRanges: List[Range]

  def getCharacters: Map[String, Image]

  def chooseCharacter(characterName: String): Boolean

  def getPlaygrounds: Map[Int, Image]

  def choosePlayground(playgroundId: Int): Unit

  def MatchResul(result: MatchResult, user: String): Unit

  def view: View

  def view_=(view: View): Unit

  def model: ToClientCommunication

  def model_=(model: ToClientCommunication): Unit

  def sendRequestAt(username: String): Boolean

  def sendResponse(response: Boolean): Unit

  def startMatch: Map[String, Map[Direction, Image]]
   */

}
