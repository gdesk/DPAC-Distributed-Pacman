package client.communication

import client.communication.model.{ToClientCommunication, ToClientCommunicationImpl}

/**
  * Test of client-server architecture
  *
  * @author Giulia Lucchi
  */
object ActorTest extends App {
  val model: ToClientCommunication = ToClientCommunicationImpl()
/*
  //registration
  println(model.registration("giulia","giuls","lucchigiulia@libero.it","ciao", "ciaonoo"))
  println(model.registration("giulia","giuls","lucchigiulia@libero.it","ciao", "ciao"))


  //login
  println(model.login("giulia", "ciaogiulia"))


  //logout
  println(model.logout())

  println(model.getRanges)

  //selectRange
  println(model.selectRange(Range(3,5)))

  println(model.getCharactersToChoose)*/

  println(model.chooseCharacter("pacman"))
}
