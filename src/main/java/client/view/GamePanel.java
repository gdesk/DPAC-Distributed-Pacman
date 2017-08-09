package client.view;

import client.model.utils.Point;

import java.awt.*;

/**
 * Created by chiaravarini on 29/07/17.
 */
public interface GamePanel {

    void updateLives(final int lives);
    void gameOver();
    void deleteCharacter(final Point<Integer, Integer> characterToDeletePosition);
    void updateScore(final int score);
    void move(final Image characterImage, final Point<Integer,Integer> oldPosition, final Point<Integer,Integer> newPosition);
}
