package client

import client.gameElement.{Dot, Eatable, Fruit, Pill}
import client.utils.Point

/**
  * Created by ManuBottax on 25/06/2017.
*/
  object test extends App{


      val l: List[Eatable]  = List (new Dot(Point(1,2)), new Fruit(Point(10,4)) , new Pill (Point(5,7) ))

      l.foreach( x => println(x.position + " " +  x.score))


}
