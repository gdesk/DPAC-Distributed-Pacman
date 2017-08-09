package client.communication

import client.communication.model.{ToClientCommunication, ToClientCommunicationImpl}

/**
  * Created by lucch on 09/08/2017.
  */
object ActorTest extends App {
  val model: ToClientCommunication = ToClientCommunicationImpl()

  //registration
 // println(model.registration("giulia","giuls","lucchigiulia@libero.it","ciao", "ciao1"))
 // println(model.registration("giulia","giuls","lucchigiulia@libero.it","ciao", "ciao"))

  //login
  println(model.login("giulia", "ciaogiulia").toString)
}
