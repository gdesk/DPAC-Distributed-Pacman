package client

import java.awt.Color

import scala.collection.immutable.HashMap
import client.character.{GhostImpl, PacmanImpl}
import client.utils.Point
import client.character.Character
import org.scalatest.FunSuite


/**
  * Created by margherita on 05/07/17.
  */
class MatchTest extends FunSuite {

  var usersAndCharacters: HashMap[String, Character[Int,Int]] = new HashMap
  usersAndCharacters = usersAndCharacters + (("Marghe", PacmanImpl("Pacman")),
                                             ("Chiara", GhostImpl("Red", Color.red)),
                                             ("Giuls", GhostImpl("Blue", Color.blue)),
                                             ("Manu", GhostImpl("Green", Color.green)),
                                             ("Fede", GhostImpl("Yellow", Color.yellow)))
  val playground: List[Point[Int,Int]] = List(Point(1,2), Point(2,3), Point(5,7))
  val gameMatch: Match = MatchImpl(playground, usersAndCharacters)
  val characters: List[Character[Int, Int]] = gameMatch characters()

  test("Match's getter of characters' list") {
    assert(characters.size.equals(5))
    assert(characters.contains(PacmanImpl("Pacman")))
    assert(characters.contains(GhostImpl("Red", Color.red)))
    assert(characters.contains(GhostImpl("Blue", Color.blue)))
    assert(characters.contains(GhostImpl("Green", Color.green)))
    assert(characters.contains(GhostImpl("Yellow", Color.yellow)))
  }


  //def main(args: Array[String]): Unit = {
    //println(characters.toString())
  //}

}
