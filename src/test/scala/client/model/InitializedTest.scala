package client.model

import client.model.character.InitializedInfoImpl
import client.model.utils.Point

/**
  *  Created by Giulia on 03/07/2017.
  *
  *  Testing of initialized information from logical part made of prolog.
  */
object InitializedTest extends App{
/*
  /* Verify the extraction of pacman's start position from prolog*/
  var pacmanStartPosition : Point[Int,Int] = InitializedInfoImpl.getStartPosition()
  var x: Int = 30
  var y: Int = 30
  assert( pacmanStartPosition.x equals x)
  assert( pacmanStartPosition.y equals y)

  /*Verify the extraction pacman's number lives*/
  var pacmanLives = InitializedInfoImpl.getCharacterLives("pacman")
  assert(pacmanLives == 3 )

  /*Verify the extraction ghost's number lives*/
  var ghostLives = InitializedInfoImpl.getCharacterLives("ghost")
  assert(ghostLives == 1)
*/
}
