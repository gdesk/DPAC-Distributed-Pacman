package client.model

import java.net.InetAddress
import java.util.Calendar

import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

class PlayerTest extends FunSuite {

  private val player: Player = PlayerImpl

  test("ip") {
    assert(player.ip equals InetAddress.getLocalHost.toString.split('/')(1))
  }

  test("port") {
    assert(player.port equals 2554)
  }

  test("username") {
    assert(player.username equals "")
    player.username = "Marghe"
    assert(player.username equals "Marghe")
  }

  test("allMatchesResults and addAMatchResult") {
    assert(player.allMatchesResults isEmpty)

    val matchResult = new MatchResultImpl
    matchResult.date = Calendar.getInstance()
    matchResult.score = 1000
    matchResult.result = true

    player.addAMatchResult(matchResult)
    assert(player.allMatchesResults contains matchResult)

    val results: ListBuffer[MatchResult] = ListBuffer.empty
    for(i <- 0 to 3) {
      val matchRes = new MatchResultImpl
      matchRes.date = Calendar.getInstance()
      matchRes.score = 1000
      matchRes.result = true
      results += matchRes
    }

    player.allMatchesResults = results.toList
    assert(player.allMatchesResults contains matchResult)
    assert(player.allMatchesResults.size equals 5)
  }

  test("singleton") {
    assert(player equals PlayerImpl)
  }

}
