package view;

import view.utils.BlocksImages;
import view.utils.FruitsImages;
import view.utils.ImagesUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represent the base playground panel of Distributed Pacman game
 * Created by Manuel Bottax and Chiara Varini on 01/07/17.
 */


public class BasePlaygroundPanel extends JPanel implements BasePlaygroundView {

    private final JLabel[][] cells;
    private final List<JLabel> renderedCells = new ArrayList<>();
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final GameObjectView gameObjectImages = new GameObjectViewImpl();
    private final PlaygroundSettings settings;

    public BasePlaygroundPanel(PlaygroundSettings playgroundSetting){

        settings = playgroundSetting;

        setLayout(new GridBagLayout());
        setBackground(settings.getBackgroundColor());
        cells = new JLabel[settings.getColumns()][settings.getRows()];

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        for (int i = 0; i < settings.getColumns(); ++i) {
            for (int j = 0; j < settings.getRows(); ++j) {
                cells[i][j] = new JLabel();
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.white));
                cells[i][j].setMaximumSize(settings.getCellDim());
                cells[i][j].setMinimumSize(settings.getCellDim());
                cells[i][j].setPreferredSize(settings.getCellDim());

                gbc.gridx = i;
                gbc.gridy = j;
            }
        }

        this.setFocusable(true);
    }

    @Override
    public void addKeyListener(KeyListener listener){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.requestFocus();
        super.addKeyListener(listener);
        this.revalidate();
    }


    /**
     * Shows a labyrinth block in the specified position
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     */
    public void renderBlock(int x, int y, BlocksImages blocksImage){
        checkAndInsert(x,y,getImageIcon(blocksImage.getImage()));
    }

    /**
     * Shows a dot in the specified position
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     */
    public void renderDot(int x, int y){
        checkAndInsert(x,y,getImageIconSmall(gameObjectImages.getDot(),4));
    }

    /**
     * Shows a pill in the specified position
     * @param x Horizontal position on grid
     * @param y Vertical position on grid
     */
    public void renderPill(int x, int y){
        checkAndInsert(x,y,getImageIconSmall(gameObjectImages.getPill(),2));
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
            drawMap(x,y);
        }
    }

    public void removeCharacter (int x, int y) {
        //todo: check if there is a character there
        if(x>=0 && y>=0 && x<settings.getColumns() && y<settings.getRows()) {
            ImageIcon img = new ImageIcon(Utils.getImage("empty"));
            cells[x][y].setIcon(img);
            gbc.gridx = x;
            gbc.gridy = y;
            revalidate();
            repaint();
        }
    }

    private void drawMap(final int characterX, final int characterY){
        int c = settings.getColumnsToRender();
        int r = settings.getRowsToRender();
        int indexC = Math.floorDiv(c,2);
        int indexR = Math.floorDiv(r,2);

        //pulisco la griglia
        renderedCells.forEach(cell->remove(cell));
        renderedCells.clear();

        if(characterX<indexC && characterY<indexR){
            System.out.println("angolo alto sinistro");
            insert2(0,0);

        } else if(characterX<indexC && characterY+indexR>settings.getRows()){
            System.out.println("angolo basso sinistro");
            int toAdd = indexR-(settings.getRows()-characterY);
            insert2(0, characterY - indexR - toAdd);

        } else if (characterX+indexC>settings.getColumns() && characterY<indexR){
            System.out.println("angolo alto destra");
            int toAdd = indexC - (settings.getColumns() - characterX);
            insert2(characterX - indexC - toAdd,0);

        } else if(characterX+indexC>settings.getColumns() && characterY+indexR>settings.getRows())  {
            System.out.println("angolo basso destra");
            int toAddRight = indexC - (settings.getColumns() - characterX);
            int toAddBottom = indexR-(settings.getRows()-characterY);
            insert2(characterX - indexC - toAddRight, characterY - indexR - toAddBottom);

        } else if(characterX<indexC) { //sul bordo sinistro
            System.out.println("bordo sinistro");
            insert2(0, characterY - indexR);

        }else  if(characterY<indexR) { //sul bordo sinistro
            System.out.println("bordo superiore");
            insert2(characterX - indexC, 0);

        }else  if(characterX+indexC>settings.getColumns()) {
            System.out.println("bordo destro");
            int toAdd = indexC - (settings.getColumns() - characterX);
            insert2(characterX - indexC - toAdd, characterY - indexR);

        }else  if(characterY+indexR>settings.getRows()){
            System.out.println("bordo inferiore");
            int toAdd = indexR-(settings.getRows()-characterY);
            insert2(characterX - indexC, characterY - indexR - toAdd);

        } else {
            System.out.println("centro");
            insert2(characterX - indexC, characterY - indexR);

        }
    }

    private void insert2(int x1, int y1){
        int c = settings.getColumnsToRender();
        int r = settings.getRowsToRender();
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                int x = x1 + i;
                int y = y1 + j;
                insert(i,y,x,j);
            }
        }
    }

    private void insert(int i, int y, int x, int j){
        if (x >= 0 && y >= 0 && x < settings.getColumns() && y < settings.getRows()) {
            gbc.gridx = i;
            gbc.gridy = j;
            add(cells[x][y], gbc); // aggiunge il personaggio
            renderedCells.add(cells[x][y]);
        }
    }

    private void checkAndInsert(int x, int y, ImageIcon img){

        if(x>=0 && y>=0 && x<settings.getColumns() && y<settings.getRows()) {
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
        return new ImageIcon(ImagesUtils.getScaledImage(image, settings.getCellSize(), settings.getCellSize()));
    }

    private ImageIcon getImageIconSmall(final Image image, final int divider){
        return new ImageIcon(ImagesUtils.getScaledImage(image, settings.getCellSize()/divider, settings.getCellSize()/divider));
    }
}
