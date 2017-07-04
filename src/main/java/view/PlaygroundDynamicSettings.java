package view;

import client.utils.Dimension;

import java.awt.*;

import static view.MainFrame.DIMENSION;

/**
 * Created by Manuel Bottax on 04/07/2017.
 */
public class PlaygroundDynamicSettings {

    private int columns;
    private int rows;

    private int xCellSize;
    private int yCellSize;
    private int cellSize;

    private  java.awt.Dimension cellDim;

    static final Color backgroundColor = new Color(0,0,0);


    public PlaygroundDynamicSettings(Dimension playgroundDimension){
        xCellSize = (int)(DIMENSION.getWidth()/ playgroundDimension.xDimension());
        yCellSize = (int)(DIMENSION.getHeight()/ playgroundDimension.yDimension());

        rows = playgroundDimension.yDimension();
        columns = playgroundDimension.xDimension();

        cellSize = Math.min(xCellSize, yCellSize);
        cellDim = new java.awt.Dimension(cellSize,cellSize);
    }


    public int getCellSize() {return this.cellSize;}

    public int getRows() {return this.rows;}

    public int getColumns() {return this.columns;}

    public java.awt.Dimension getCellDim() {return this.cellDim;}




}
