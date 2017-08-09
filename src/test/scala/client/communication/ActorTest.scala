package client.communication

import client.communication.model.ToClientCommunicationImpl

/**
  * Created by lucch on 09/08/2017.
  */
object ActorTest extends App {
  val model = ToClientCommunicationImpl()

  //registration
  assert(model.registration("giulia","giuls","lucchigiulia@libero.it","ciao", "ciao1"), false)
  assert(model.registration("giulia","giuls","lucchigiulia@libero.it","ciao", "ciao"), true)

  //login


}
