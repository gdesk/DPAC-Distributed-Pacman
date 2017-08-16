package client.model

import java.net.InetAddress

import scala.collection.mutable.ListBuffer

/**
  * Represents current user's player.
  *
  * @author Margherita Pecorelli
  */
trait Player {

  /**
    * Returns player's ip.
    *
    * @return player's ip.
    */
  def ip: String

  /**
    * Returns player's port.
    *
    * @return player's port.
    */
  def port: Int

  /**
    * Returns player's username.
    *
    * @return player's username.
    */
  def username: String

  /**
    * Sets player's username.
    *
    * @param username - player's username.
    */
  def username_=(username: String): Unit

  /**
    * Returns a list with all previous matchs' results.
    *
    * @return a list with all previous matchs' results.
    */
  def allMatchesResults: List[MatchResult]

  /**
    * Sets all previous matchs' results.
    *
    * @param matchesResults - all previous matchs' results.
    */
  def allMatchesResults_=(matchesResults: List[MatchResult]): Unit

  /**
    * Adds to the list of all previous matchs' results, a new one.
    *
    * @param matchResult - the new match's result to be added at the list.
    */
  def addAMatchResult(matchResult: MatchResult): Unit

}

/**
  * Represents the implementation of the current user's player.
  *
  * @author Margherita Pecorelli
  */
object PlayerImpl extends Player {

  private var _matchesResults: ListBuffer[MatchResult] = ListBuffer.empty

  override val ip =InetAddress.getLocalHost.toString.split('/')(1)
  override val port = 2554
  override var username = ""

  /**
    * Returns a list with all previous matchs' results.
    *
    * @return a list with all previous matchs' results.
    */
  override def allMatchesResults = _matchesResults.toList

  /**
    * Sets all previous matchs' results.
    *
    * @param matchesResults - all previous matchs' results.
    */
  override def allMatchesResults_=(matchesResults: List[MatchResult]) = matchesResults.foreach(m => _matchesResults += m)

  /**
    * Adds to the list of all previous matchs' results, a new one.
    *
    * @param matcheResult - the new match's result to be added at the list.
    */
  override def addAMatchResult(matcheResult: MatchResult)= _matchesResults += matcheResult

}