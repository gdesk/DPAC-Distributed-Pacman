package client.character

import client.utils.LivesImpl


/**
  * Created by Giulia Lucchi on 28/06/2017.
  */
case class Ghost(override val name: String) extends CharacterImpl(false, new LivesImpl(1/*da prolog*/)){}
