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

    /**
     * @return The screen cells size
     */
    public int getCellSize() {return this.cellSize;}

    /**
     * @return The screen cells dimension
     */
    public Dimension getCellDim() {return this.cellDim;}

    /**
     * @return The number of lines in the playground
     */
    public int getRows() {return this.rows;}

    /**
     * @return The number of columns in the playground
     */
    public int getColumns() {return this.columns;}

    /**
     * @return The number of playground columns to render at one time
     */
    public int getColumnsToRender() {
        return columnsToRender;
    }

    /**
     * @return The number of playground rows to render at one time
     */
    public int getRowsToRender() {
        return rowsToRender;
    }

    /**
     * @return The color of the playground's background
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Set the color of the playground's background
     * @param backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @return The background images of the playground
     */
    public Image getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Set the background image of the playground
     * @param backgroundImage
     */
    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
