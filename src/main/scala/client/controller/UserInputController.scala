package client.controller

import java.awt.event.{KeyEvent, KeyListener}

import client.model.Direction
import client.view.CharacterFactory
import client.view.playground.PlaygroundPanel
import controller.FakeController


/**
  * Created by Manuel Bottax on 06/07/2017.
  */
class UserInputController (val controller: FakeController)/*(val client.model.character.gameElement.character: Character) */ extends KeyListener{

  override def keyPressed(e: KeyEvent): Unit = {
    //todo: controllare bordi
    e.getKeyCode match {
      case KeyEvent.VK_LEFT => {

        controller.move(Direction.LEFT)
        /* view.removeCharacter(currentX, currentY)
         currentX = currentX - 1
         view.renderCharacter(currentX, currentY, new CharacterFactory().createPacman, "left")*/
      }


      case KeyEvent.VK_RIGHT => {

        controller.move(Direction.RIGHT)
        /* view.removeCharacter(currentX, currentY)
         currentX = currentX + 1
         view.renderCharacter(currentX, currentY,  new CharacterFactory().createPacman, "right")*/
      }


      case KeyEvent.VK_UP => {

          controller.move(Direction.UP)/*
          view.removeCharacter(currentX, currentY)
          currentY = currentY - 1
          view.renderCharacter(currentX, currentY,  new CharacterFactory().createPacman, "up")*/
      }

      case KeyEvent.VK_DOWN => {
          controller.move(Direction.DOWN)/*
          view.removeCharacter(currentX, currentY)
          currentY = currentY + 1
          view.renderCharacter(currentX, currentY,  new CharacterFactory().createPacman, "down")*/
        }

      case KeyEvent.VK_A => {
          controller.move(Direction.LEFT)/*
          view.removeCharacter(currentX, currentY)
          currentX = currentX - 1
          view.renderCharacter(currentX, currentY,  new CharacterFactory().createPacman, "left")*/
      }

      case KeyEvent.VK_D => {
          controller.move(Direction.RIGHT)/*
          view.removeCharacter(currentX, currentY)
          currentX = currentX + 1
          view.renderCharacter(currentX, currentY,  new CharacterFactory().createPacman, "right")*/
        }

      case KeyEvent.VK_W => {
          controller.move(Direction.UP)/*
          view.removeCharacter(currentX, currentY)
          currentY = currentY - 1
          view.renderCharacter(currentX, currentY,  new CharacterFactory().createPacman, "up")*/
        }


      case KeyEvent.VK_S => {
          controller.move(Direction.DOWN)/*
          view.removeCharacter(currentX, currentY)
          currentY = currentY + 1
          view.renderCharacter(currentX, currentY,  new CharacterFactory().createPacman, "down")*/
        }

      case _ =>
    }

  }

  override def keyTyped(e: KeyEvent): Unit = {}

  override def keyReleased(e: KeyEvent): Unit = {}

  private def check(x: Int, y: Int): Boolean = x>=0 && y>=0 && x<61 && y<16 //TODO passare le columns e le rows del playground

}


