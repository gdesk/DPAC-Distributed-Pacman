package client.controller3

import java.util.{Observable, Observer}

import client.model.utils.Dimension
import client.model.{Playground, PlaygroundImpl}

/**
  * Created by margherita on 25/07/17.
  */
case class Prova() extends Observer {

  override def update(o: Observable, arg: scala.Any): Unit = {
    val p: (String, client.model.character.Character) = if(arg.isInstanceOf[(String, client.model.character.Character)]) {arg.asInstanceOf[(String, client.model.character.Character)]} else {null}
    if(p != null) {
      p._1 match {
        case "remainingLives" => println("ci sono" + p._2.name)
      }
    }
  }

}

object mainDiProva extends App {

  private val playeground: Playground = PlaygroundImpl instance()
  playeground.dimension = Dimension(50,50)

  val prova = Prova()
  prova.update(new Observable(), ("remainingLives", client.model.character.BaseGhost("GhostCiao")))

}
