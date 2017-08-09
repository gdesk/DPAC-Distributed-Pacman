package client.model

import java.net.InetAddress

import scala.collection.mutable.ListBuffer

/**
  *
  */
trait Player {

  def ip: String

  def port: Int

  def username: String

  def username_=(username: String): Unit

  def allMatchesResults: List[MatchResult]

  def allMatchesResults_=(matches: List[MatchResult]): Unit

  def addAMatchResult(matchResult: MatchResult): Unit

}

case class PlayerImpl private() extends Player {

  private var matchesResults: ListBuffer[MatchResult] = ListBuffer empty

  override val ip = InetAddress.getLocalHost.toString
  override val port = 2554
  override var username = ""

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
