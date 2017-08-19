package client.utils


import java.net.InetAddress


/**
  * This object includes client actors' paths to improve code
  *
  * @author Giulia Lucchi
  */
object ActorUtils {

  val IP_ADDRESS: String = InetAddress.getLocalHost.toString

  val INBOX_ACTOR : String = "/system/dsl/inbox-1"
  val TOSERVER_ACTOR: String = "/user/toServerCommunication"
  val FROMSERVER_ACTOR: String =  "/user/fromServerCommunication"
  val P2P_ACTOR: String = "/user/P2PCommunication"

  var serverIP: String = _
}
