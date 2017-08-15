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

object PlayerImpl extends Player {

  private var matchesResults: ListBuffer[MatchResult] = ListBuffer empty

  override val ip =InetAddress.getLocalHost.toString.split('/')(1)
  override val port = 2554
  override var username = ""

  override def allMatchesResults = matchesResults toList

  override def allMatchesResults_=(matches: List[MatchResult]) = {
    //matchesResults = matches.to[ListBuffer[MatchResult]]
    matches.foreach(m => matchesResults += m)
  }

  override def addAMatchResult(matchResult: MatchResult)= matchesResults += matchResult
}