package client.view.playground;

import client.view.GameObjectView;
import client.view.GameObjectViewImpl;
import client.view.Res;
import client.view.Utils;
import client.view.utils.enumerations.BlocksImages;
import client.view.utils.enumerations.FruitsImages;

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

    private JLabel[][] cells = new JLabel[0][0];
    private final List<JLabel> renderedCells = new ArrayList<>();
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final GameObjectView gameObjectImages = new GameObjectViewImpl();
    private final PlaygroundSettings settings;

    public BasePlaygroundPanel(PlaygroundSettings playgroundSetting){
        settings = playgroundSetting;
        setLayout(new GridBagLayout());
        setBackground(settings.getBackgroundColor());
        initLabyrinth();
        this.setFocusable(true);
    }

    @Override
    public void renderBlock(int x, int y, BlocksImages blocksImage){ insertImage(x,y,getImageIcon(blocksImage.getImage())); }

    @Override
    public void renderDot(int x, int y){
        insertImage(x,y,getImageIconSmall(gameObjectImages.getDot(),4));
    }

    @Override
    public void renderPill(int x, int y){
        insertImage(x,y,getImageIconSmall(gameObjectImages.getPill(),2));
    }

    @Override
    public void renderFruit(int x, int y, FruitsImages type){ insertImage(x,y,getImageIcon(gameObjectImages.getFruit(type))); }

    @Override
    public void renderCharacter(int x, int y, Image characterView){
        if (characterView != null) {
            insertImage(x,y, new ImageIcon(characterView));
            drawMap(x,y);
        }
    }

    @Override
    public void removeCharacter (int x, int y) {
        ImageIcon img = new ImageIcon(Utils.getImage(Res.EMPTY()));
        cells[x][y].setIcon(img);
        gbc.gridx = x;
        gbc.gridy = y;
        revalidate();
        repaint();
    }

    @Override
    public void addKeyListener(KeyListener listener){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        requestFocus();
        super.addKeyListener(listener);
        revalidate();
    }

    private void initLabyrinth(){
        cells = new JLabel[settings.getColumns()+1][settings.getRows()+1];
        for (int i = 0; i <= settings.getColumns(); ++i) {
            for (int j = 0; j <= settings.getRows(); ++j) {
                cells[i][j] = new JLabel();
                cells[i][j].setMaximumSize(settings.getCellDim());
                cells[i][j].setMinimumSize(settings.getCellDim());
                cells[i][j].setPreferredSize(settings.getCellDim());
            }
        }
    }

    private void drawMap(final int characterX, final int characterY){
        renderedCells.forEach(cell->remove(cell));
        renderedCells.clear();
        drawCells(characterX,characterY);
    }

    private void drawCells (final int characterX, final int characterY){
        int halfColumnsToRender = Math.floorDiv(settings.getColumnsToRender(),2);
        int halfRowsToRender = Math.floorDiv(settings.getRowsToRender(),2);

        boolean leftPosition = characterX<halfColumnsToRender;
        boolean upperPosition = characterY<halfRowsToRender;
        boolean rightPosition = characterX+halfColumnsToRender>settings.getColumns();
        boolean bottomPosition = characterY+halfRowsToRender>settings.getRows();
        boolean upperLeftCorner = leftPosition && upperPosition;
        boolean bottomLeftCorner = leftPosition && bottomPosition;
        boolean upperRightCorner = rightPosition && upperPosition;
        boolean bottomRightCorner = rightPosition && bottomPosition;

        if(upperLeftCorner){
            renderAllCells(0,0);

        } else if(bottomLeftCorner){
            int deltaRows = halfRowsToRender-(settings.getRows()-characterY);
            System.out.println(halfRowsToRender+"  "+settings.getRows()+"  "+characterY);
            renderAllCells(0, characterY - halfRowsToRender - deltaRows);

        } else if (upperRightCorner){
            int deltaColumms = halfColumnsToRender - (settings.getColumns() - characterX);
            renderAllCells(characterX - halfColumnsToRender - deltaColumms,0);

        } else if(bottomRightCorner)  {
            int deltaColumms = halfColumnsToRender - (settings.getColumns() - characterX);
            int deltaRows = halfRowsToRender-(settings.getRows()-characterY);
            System.out.println();
            renderAllCells(characterX - halfColumnsToRender - deltaColumms, characterY - halfRowsToRender - deltaRows);

        } else if(leftPosition) {
            renderAllCells(0, characterY - halfRowsToRender);

        }else  if(upperPosition) {
            renderAllCells(characterX - halfColumnsToRender, 0);

        }else  if(rightPosition) {
            int toAdd = halfColumnsToRender - (settings.getColumns() - characterX);
            renderAllCells(characterX - halfColumnsToRender - toAdd, characterY - halfRowsToRender);

        }else  if(bottomPosition){
            int toAdd = halfRowsToRender-(settings.getRows()-characterY);
            System.out.println(halfRowsToRender + "  "+settings.getRows()+"  "+characterY);
            renderAllCells(characterX - halfColumnsToRender, characterY - halfRowsToRender - toAdd);

        } else {
            renderAllCells(characterX - halfColumnsToRender, characterY - halfRowsToRender);
        }
    }

    private void renderAllCells(int ColumnsIndex, int RowsIndex){
        int c = settings.getColumnsToRender();
        int r = settings.getRowsToRender();
        for (int i = 0; i <= c; i++) {
            for (int j = 0; j < r; j++) {
                int x = ColumnsIndex + i;
                int y = RowsIndex + j;
                insertSingleCell(i,y,x,j);
            }
        }
    }

    private void insertSingleCell(int i, int y, int x, int j){
        if (checkBorder(x,j)) {
            gbc.gridx = i;
            gbc.gridy = j;
            add(cells[x][y], gbc); //add to JPanel
            renderedCells.add(cells[x][y]);
        }
    }

    private void insertImage(int x, int y, ImageIcon img){
        if(checkBorder(x,y)) {
            cells[x][y].setIcon(img);
            gbc.gridx = x;
            gbc.gridy = y;
            revalidate();
            repaint();
        }
    }

    private boolean checkBorder(final int x, final int y){
        if(x>=0 && y>=0 && x<=settings.getColumns() && y<=settings.getRows()){
            return true;
        }else {
            System.err.println("Error! Invalid position");
            return false;
        }
    }

    private ImageIcon getImageIcon(final Image image){
        return new ImageIcon(Utils.getScaledImage(image, settings.getCellSize(), settings.getCellSize()));
    }

    private ImageIcon getImageIconSmall(final Image image, final int divider){
        return new ImageIcon(Utils.getScaledImage(image, settings.getCellSize()/divider, settings.getCellSize()/divider));
    }
}
