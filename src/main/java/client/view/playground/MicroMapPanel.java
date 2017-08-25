package client.view.playground;

import client.model.PlaygroundImpl;
import client.model.gameElement.Block;
import client.model.utils.Point;
import client.model.utils.PointImpl;
import client.view.MainFrame;
import client.view.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the small map of the labyrinth
 * located at the bottom right
 * Created by Chiara Varini on 10/07/17.
 */
public class MicroMapPanel extends JPanel {

    private final static int DIMENTION_DIVIDER = 3;
    private final static int BOUND = 30;
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final MazePiece[][] panles;

    public MicroMapPanel(){
        int columns = PlaygroundImpl.dimension().x();
        int rows = PlaygroundImpl.dimension().y();
        this.panles = new MazePiece[columns+1][rows];

        setSize(new Dimension(new MazePiece().getPreferredSize().width*columns+BOUND/2, new MazePiece().getPreferredSize().width*rows+BOUND/2));
        setBorder(BorderFactory.createLineBorder(Color.white));
        setBounds((int) MainFrame.DIMENSION.getWidth()-getWidth()-BOUND, (int) MainFrame.DIMENSION.getHeight()-getHeight()-BOUND,getWidth(),getHeight());
        setLayout(new GridBagLayout());

        initMicroMap(columns,rows);

        revalidate();
        repaint();
    }

    /**
     * Creates a colored quadrant in the newPosition and deletes that in the oldPosition
     * @param color
     * @param newPosition
     * @param oldPosition
     */
    public void moveCharacter(Color color, Point<Integer,Integer> newPosition, Point<Integer,Integer> oldPosition) {
        panles[oldPosition.x()][oldPosition.y()].setBackground(Color.WHITE);
        panles[newPosition.x()][newPosition.y()].setBackground(color);
        revalidate();
        repaint();
    }

    private void initMicroMap(final int columns, final int rows){
        for(int x = 0; x<=columns; x++){
            for(int y = 0; y<rows; y++){
                gbc.gridx = x;
                gbc.gridy = y;
                MazePiece panel = new MazePiece();
                add(panel,gbc);
                panles[x][y] = panel;
            }
        }
    }

    /**
     * Private class representing each single quadrant of the micro map
     */
    private class MazePiece extends JPanel{

        private MazePiece(){
            Block fakeBlock = new Block(new PointImpl<Object, Object>(gbc.gridx,gbc.gridy));
            if( Utils.getJavaList(PlaygroundImpl.blocks()).contains(fakeBlock)) {
                this.setBackground(Color.BLACK);
            } else {
                this.setBackground(Color.WHITE);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            int shortedSide = (int)Math.min(MainFrame.DIMENSION.getWidth()/DIMENTION_DIVIDER, MainFrame.DIMENSION.getHeight()/DIMENTION_DIVIDER);
            int panelDimention = Math.min(shortedSide/PlaygroundImpl.dimension().x(),shortedSide/PlaygroundImpl.dimension().y());
            return new Dimension(panelDimention,panelDimention);
        }
    }
}
