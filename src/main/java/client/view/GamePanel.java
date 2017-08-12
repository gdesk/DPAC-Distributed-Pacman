package client.view;

import client.model.utils.Point;

import java.awt.*;

/**
 * Panel in which is represented the whole of a match
 * Created by Chiara Varini on 29/07/17.
 */
public interface GamePanel {

    /**
     * Render the number of lives passed
     * @param lives
     */
    void updateLives(final int lives);

    /**
     * Remove the image present in characterToDeletePosition
     * @param characterToDeletePosition
     */
    void deleteCharacter(final Point<Integer, Integer> characterToDeletePosition);

    /**
     * Render the score passed
     * @param score
     */
    void renderScore(final int score);

    /**
     * Render the characterImage in newPosition and delete the image present in oldPosition
     * @param characterImage
     * @param oldPosition
     * @param newPosition
     */
    void move(final Image characterImage, final Point<Integer,Integer> oldPosition, final Point<Integer,Integer> newPosition);

    /**
     * Render the player's final result
     * @param result
     */
    void showResult(final String result);

    /**
     * Terminates the match and renders the GameOverDialog
     */
    void gameOver();
}
