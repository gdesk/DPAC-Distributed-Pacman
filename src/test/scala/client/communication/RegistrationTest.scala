package client.communication

import client.communication.model.{ToClientCommunication, ToClientCommunicationImpl}

/**
  * Created by lucch on 30/07/2017.
  */
object RegistrationTest extends App {

  var comm: ToClientCommunication = ToClientCommunicationImpl();

  //registration
  assert(comm.registration("ciao", "pippo", "email", "la", "la"), true)
  assert(comm.registration("ciao", "pippo", "email", "la", "la"), false)
}
