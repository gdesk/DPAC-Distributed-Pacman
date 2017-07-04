package view;

import view.utils.FruitsImages;

import javax.swing.*;

/**
 * Created by Manuel Bottax on 04/07/2017.
 */
public interface PlaygroundView {

    /**
     * Shows a labyrinth block in the specified position
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     */
    void renderBlock(int x, int y);

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
     * Shows the specified character in the specified position and direction
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     * @param name Chracter's name
     * @param direction Character's direction
     */
    public void renderCharacter(int x, int y, String name, String direction);
}
