package client.view

import java.awt.event.{KeyEvent, KeyListener}

import client.controller3.ControllerCharacter
import client.model.Direction

/**
  * Created by Manuel Bottax on 06/07/2017.
  */
class UserInputController (val controller: ControllerCharacter) extends KeyListener{


  override def keyPressed(e: KeyEvent): Unit = {

    e.getKeyCode match {
      case KeyEvent.VK_LEFT => {
        controller.move(Direction.LEFT)
      }

      case KeyEvent.VK_RIGHT => {
        controller.move(Direction.RIGHT)
      }

      case KeyEvent.VK_UP => {
        controller.move(Direction.UP)
      }

      case KeyEvent.VK_DOWN => {
        controller.move(Direction.DOWN)
      }

      case KeyEvent.VK_A => {
        controller.move(Direction.LEFT)
      }

      case KeyEvent.VK_D => {
        controller.move(Direction.RIGHT)
      }

      case KeyEvent.VK_W => {
        controller.move(Direction.UP)
      }

      case KeyEvent.VK_S => {
        controller.move(Direction.DOWN)
      }

      case _ => {}
    }

  }

  override def keyTyped(e: KeyEvent): Unit = {}

  override def keyReleased(e: KeyEvent): Unit = {}
}


