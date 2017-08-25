package client.view.playground;

import java.awt.*;

import static client.view.MainFrame.DIMENSION;

/**
 * This class represent the configuration
 * of all the elements of a PlaygroundView
 * Created by Chiara Varini on 04/07/2017.
 */
public class PlaygroundSettings {

    private final static int DEFAULT_COLUMS_TO_RENDER = 30;
    private final static int DEFAULT_ROWS_TO_RENDER = 30;

    private final int columnsToRender;
    private final int rowsToRender;

    private final int columns;
    private final int rows;
    private final int cellSize;
    private final Dimension cellDim;

    private Color backgroundColor;
    private Image backgroundImage;

    public PlaygroundSettings(final int columns, final int rows){
        this.rows = rows;
        this.columns = columns;

        this.columnsToRender = Math.min(DEFAULT_COLUMS_TO_RENDER,columns);
        this.rowsToRender = Math.min(DEFAULT_ROWS_TO_RENDER,rows);

        int xCellSize = (int)(DIMENSION.getWidth()/ columnsToRender);
        int yCellSize = (int)(DIMENSION.getHeight()/ rowsToRender);

        this.cellSize = Math.min(xCellSize, yCellSize);
        this.cellDim = new Dimension(cellSize,cellSize);
    }

    public int getCellSize() {return this.cellSize;}

    public int getRows() {return this.rows;}

    public int getColumns() {return this.columns;}

    public Dimension getCellDim() {return this.cellDim;}

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public int getColumnsToRender() {
        return columnsToRender;
    }

    public int getRowsToRender() {
        return rowsToRender;
    }

}
