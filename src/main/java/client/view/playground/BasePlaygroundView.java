package client.view.playground;

import client.view.utils.enumerations.BlocksImages;
import client.view.utils.enumerations.FruitsImages;

import java.awt.*;

/**
 * A base playground panel of Distributed Pacman game
 * which can render the whole labyrinth in which the game will take place
 * Created by Chiara Varini on 04/07/2017.
 */
public interface BasePlaygroundView {

    /**
     * Shows a labyrinth block in the specified position
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     * @param blocksImage The block imaget to be render
     */
    void renderBlock(int x, int y, BlocksImages blocksImage);

    /**
     * Shows a dot in the specified position
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     */
    void renderDot(int x, int y);

    /**
     * Shows a pill in the specified position
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     */
    void renderPill(int x, int y);

    /**
     * Shows a fruit in the specified position
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     * @param type The fruit type to be rendered.
     */
    void renderFruit(int x, int y, FruitsImages type);

    /**
     * Shows the specified client.model.character.gameElement.character in the specified position and direction
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     * @param characterView Chracter's name
     */
    void renderCharacter(int x, int y, Image characterView);


    /**
     * remove a client.model.character.gameElement.character sprite in the specified position (if present).
     *
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     */
    void removeCharacter(int x, int y);
}
