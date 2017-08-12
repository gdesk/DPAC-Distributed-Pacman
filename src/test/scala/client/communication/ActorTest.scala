package client.communication

import java.util.Calendar

import client.communication.model.{ToClientCommunication, ToClientCommunicationImpl}
import client.model.{Match, MatchImpl, MatchResultImpl, PlayerImpl}

/**
  * Test of client-server architecture.
  * This is execute executable only with the server.
  *
  * @author Giulia Lucchi
  */
object ActorTest extends App {
  val model: ToClientCommunication = ToClientCommunicationImpl()
  val matchGame: Match = MatchImpl.instance()
  val player = PlayerImpl()

  println(model.registration("giulia","giuls","lucchigiulia@libero.it","ciao", "ciaonoo")) //false
  println(model.registration("giulia","giuls","lucchigiulia@libero.it","ciao", "ciao")) //true

  println(model.login("giulia", "ciaogiulia"))

  println(model.logout())

  println(model.getRanges)


  println(model.selectRange(Range(3,5)))

  println(model.getCharactersToChoose)

  println(model.chooseCharacter("pacman"))

  println(model.getPlaygrounds)
  println(model.choosePlayground(1))
  println(matchGame.playground)

  val game: client.model.MatchResult = new MatchResultImpl()
  game.result= true
  game.score =123
  game.date = Calendar.getInstance()
  println(model.matchResult(game, player.username))
}
