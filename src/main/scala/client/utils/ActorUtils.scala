package client.utils


/**
  * This object includes client actors' paths and server ip, received as input parameter.
  *
  * @author Giulia Lucchi
  */
object ActorUtils {

  val INBOX_ACTOR : String = "/system/dsl/inbox-1"
  val TOSERVER_ACTOR: String = "/user/toServerCommunication"
  val FROMSERVER_ACTOR: String =  "/user/fromServerCommunication"
  val P2P_ACTOR: String = "/user/P2PCommunication"

  var serverIP: String = _
}
