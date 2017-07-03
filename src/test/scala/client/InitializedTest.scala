package client

import client.character.initializedInfoImpl
import client.utils.Point

/**
  *  Created by Giulia on 03/07/2017.
  *
  *  Testing of initialized information from logical part made of prolog.
  */
object InitializedTest extends App{
  private val initializedInfo = new initializedInfoImpl()

  /* Verify the extraction of pacman's start position from prolog*/
  var pacmanStartPosition : Point[Int,Int] = initializedInfo.getStartPosition()
  var x: Int = 30
  var y: Int = 30
  assert( pacmanStartPosition.x equals x)
  assert( pacmanStartPosition.y equals y)

  /*Verify the extraction pacman's number lives*/
  var pacmanLives = initializedInfo.getCharacterLives("pacman_lives(X)")
  assert(pacmanLives == 3 )

  /*Verify the extraction ghost's number lives*/
  var ghostLives = initializedInfo.getCharacterLives("ghost_lives(X)")
  assert(ghostLives == 1)

}
