package client.view

import java.awt.event.{KeyEvent, KeyListener}
import javax.swing.Timer

import client.controller.BaseControllerCharacter
import client.model.Direction

/**
  * Created by Manuel Bottax on 06/07/2017.
  */
class UserInputController () extends KeyListener{

  val controller = BaseControllerCharacter
 // val timer: Timer = new Timer(300, e => canMove = true)
 // var canMove: Boolean = true

  override def keyPressed(e: KeyEvent): Unit = {

    e.getKeyCode match {
      case KeyEvent.VK_LEFT => {
   //     if(canMove) {
          controller.move(Direction.LEFT)
     //     canMove = false
       //   timer.start()
       // }
      }

      case KeyEvent.VK_RIGHT => {
       // if(canMove) {
          controller.move(Direction.RIGHT)
         // canMove = false
          //timer.start()
        //}
      }

      case KeyEvent.VK_UP => {
        //if(canMove) {
          controller.move(Direction.DOWN)
          //canMove = false
          //timer.start()
        //}
      }

      case KeyEvent.VK_DOWN => {
        //if(canMove) {
          controller.move(Direction.UP)
          //canMove = false
          //timer.start()
        //}
      }

      case KeyEvent.VK_A => {
       // if(canMove) {
          controller.move(Direction.LEFT)
         // canMove = false
         // timer.start()
        //}
      }

      case KeyEvent.VK_D => {
        //if(canMove) {
          controller.move(Direction.RIGHT)
          //canMove = false
          //timer.start()
        //}
      }

      case KeyEvent.VK_W => {
        //if(canMove) {
          controller.move(Direction.DOWN)
          //canMove = false
          //timer.start()
        //}
      }

      case KeyEvent.VK_S => {
        //if(canMove) {
          controller.move(Direction.UP)
          //canMove = false
          //timer.start()
        //}
      }

      case _ => {}
    }

  }

  override def keyTyped(e: KeyEvent): Unit = {}

  override def keyReleased(e: KeyEvent): Unit = {}
}


