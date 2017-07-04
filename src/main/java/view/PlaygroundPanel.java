package view;

import client.utils.Dimension;
import view.utils.FruitsImages;
import view.utils.ImagesUtils;

import javax.swing.*;
import java.awt.*;

import static view.PlaygroundSettings.*;

/**
 * This class represent the base playground panel of Distributed Pacman game
 * Created by Manuel Bottax and Chiara Varini on 01/07/17.
 */


public class PlaygroundPanel extends JPanel implements PlaygroundView{

    private final JLabel[][] cells;
    private final GridBagConstraints gbc = new GridBagConstraints();
    private BlockView blocksImages = new BlockViewImpl();
    private GameObjectView gameObjectImages = new GameObjectViewImpl();

    private int rows;
    private int columns;

    public PlaygroundPanel(Dimension playgroundDimension){

        this.rows = playgroundDimension.yDimension();
        this.columns = playgroundDimension.xDimension();

        setLayout(new GridBagLayout());
        setBackground(backgroundColor); //Look at import static for settings
        cells = new JLabel[columns][rows];

        System.out.println(" size : [ " + columns + " | " + rows  + "] !");

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        for (int i = 0; i < columns; ++i) {
            for (int j = 0; j < rows; ++j) {
                cells[i][j] = new JLabel();
                cells[i][j].setMaximumSize(cellDim);
                cells[i][j].setMinimumSize(cellDim);
                cells[i][j].setPreferredSize(cellDim);

                gbc.gridx = i;
                gbc.gridy = j;
                add(cells[i][j], gbc);
            }
        }
    }


    /**
     * Shows a labyrinth block in the specified position
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     */
    public void renderBlock(int x, int y){  //TODO caricare blocchi diversi a seconda di quelli intorno a lui...da fare in un'altra classe
        checkAndInsert(x,y,getImageIcon(blocksImages.getHorizontal()));
    }

    /**
     * Shows a dot in the specified position
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     */
    public void renderDot(int x, int y){
        checkAndInsert(x,y,gameObjectImages.getDot());
    }

    /**
     * Shows a pill in the specified position
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     */
    public void renderPill(int x, int y){
        checkAndInsert(x,y,gameObjectImages.getPill());
    }

    /**
     * Shows a fruit in the specified position
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     * @param type The fruit type to be rendered.
     */
    public void renderFruit(int x, int y, FruitsImages type){
       checkAndInsert(x,y,getImageIcon(gameObjectImages.getFruit(type)));
    }

    /**
     * Shows the specified character in the specified position and direction
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     * @param name Chracter's name
     * @param direction Character's direction
     */
    public void renderCharacter(int x, int y, String name, String direction){

        CharacterView characterView = new CharacterViewImpl(new CharacterPathImpl(name));

        if (characterView != null) {
            ImageIcon img = getImageIcon(characterView.getCharacterLeft());
            switch(direction){
                case "up" :
                    img = getImageIcon(characterView.getCharacterUp());
                    break;
                case "down" :
                    img = getImageIcon(characterView.getCharacterDown());
                    break;
                case "right" :
                    img = getImageIcon(characterView.getCharacterRight());
                    break;
                case "left" :
                    img = getImageIcon(characterView.getCharacterLeft());
                    break;
            }
            checkAndInsert(x,y,img);
        }
    }


    private void checkAndInsert(int x, int y, Image img) {
        checkAndInsert(x,y,new ImageIcon(img));
    }

    private void checkAndInsert(int x, int y, ImageIcon img){

        if(x<columns && y<rows) {

            cells[x][y].setIcon(img);
            gbc.gridx = x;
            gbc.gridy = y;
            revalidate();
            repaint();

        } else {
            System.err.println("Error! Invalid position");
        }
    }

    private ImageIcon getImageIcon(final Image image){
        return new ImageIcon(ImagesUtils.getScaledImage(image, cellSize, cellSize));
    }
}
