package client.model

import scala.collection.mutable.ListBuffer

/**
  *
  */
trait Player {

  def ip: String

  def ip_=(ip: String): Unit

  def port: Int

  def port_=(port: Int): Unit

  def username: String

  def username_=(username: String): Unit

  def mail: String

  def mail_=(mail: String): Unit

  def allMatchesResults: List[MatchResult]

  def allMatchesResults_=(matches: List[MatchResult]): Unit

  def addAMatchResult(matchResult: MatchResult): Unit

}

case class PlayerImpl private() extends Player {

  private var matchesResults: ListBuffer[MatchResult] = ListBuffer empty

  override var ip = ""
  override var port = -1
  override var username = ""
  override var mail = ""

  override def allMatchesResults = matchesResults toList

  override def allMatchesResults_=(matches: List[MatchResult]) = matchesResults = matches.to[ListBuffer[MatchResult]]

  override def addAMatchResult(matchResult: MatchResult)= matchesResults += matchResult
}

object PlayerImpl {

  private var _instance: PlayerImpl = null

  def instance(): PlayerImpl = {
    if(_instance == null) _instance = PlayerImpl()
    _instance
  }

}
