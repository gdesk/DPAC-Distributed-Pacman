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

//  def name: String

//  def name_=(name: String): Unit

  def username: String

  def username_=(username: String): Unit

  def mail: String

  def mail_=(mail: String): Unit

  def allMatchesResults: List[MatchResult]

  def addAMatchResult(matchResult: MatchResult): Unit

//  def character: Character

//  def character_=(character: Character): Unit

}

case class PlayerImpl(override var ip: String, override var port: Int, /*override var name: String, */ override var username: String, override var mail: String, /*override var character: Character, */private val matchesResults: ListBuffer[MatchResult]) extends Player {

  override def allMatchesResults = matchesResults toList

  override def addAMatchResult(matchResult: MatchResult)= matchesResults += matchResult
}
