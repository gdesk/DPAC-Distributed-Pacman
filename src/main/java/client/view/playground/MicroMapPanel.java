package client.view.playground;

import client.model.Playground;
import client.model.gameElement.Block;
import client.model.utils.Point;
import client.model.utils.PointImpl;
import client.view.MainFrame;
import client.view.Utils;

import javax.swing.*;
import java.awt.*;


/**
 * This class represents the small general map of the labyrinth
 * Created by chiaravarini on 10/07/17.
 */
public class MicroMapPanel extends JPanel {

    private final static int DIMENTION_DIVIDER = 3;
    private final static int BOUND = 30;
    private final Playground playground;
    private final GridBagConstraints gbc = new GridBagConstraints();

    public MicroMapPanel(Playground playground){
        this.playground = playground;
        int columns = playground.dimension().x();
        int rows = playground.dimension().y();

        setSize(new Dimension(new MazePecePanel().getPreferredSize().width*columns+BOUND/2, new MazePecePanel().getPreferredSize().width*rows+BOUND/2));
        setBorder(BorderFactory.createLineBorder(Color.white));
        setBounds((int) MainFrame.DIMENSION.getWidth()-getWidth()-BOUND, (int) MainFrame.DIMENSION.getHeight()-getHeight()-BOUND,getWidth(),getHeight());
        setLayout(new GridBagLayout());

        for(int x = 2; x<=columns; x++){
            for(int y = 0; y<rows; y++){
                gbc.gridx = x;
                gbc.gridy = y;
                add(new MazePecePanel(),gbc);
            }
        }

        revalidate();
        repaint();
    }

    public void moveCharacter(Color color, Point<Integer,Integer> position){
        gbc.gridx = position.x();
        gbc.gridy = position.y();
        MazePecePanel characterPanel = new MazePecePanel();
        characterPanel.setBackground(color);
        add(characterPanel, position);
    }

    private class MazePecePanel extends JPanel{

        private MazePecePanel(){
            Block fakeBlock = new Block(new PointImpl<Object, Object>(gbc.gridx,gbc.gridy));
            if( Utils.getJavaList(playground.blocks()).contains(fakeBlock)) {
                this.setBackground(Color.BLACK);
            } else {
                this.setBackground(Color.WHITE);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            int shortedSide = (int)Math.min(MainFrame.DIMENSION.getWidth()/DIMENTION_DIVIDER, MainFrame.DIMENSION.getHeight()/DIMENTION_DIVIDER);
            int panelDimention = Math.min(shortedSide/playground.dimension().x(),shortedSide/playground.dimension().y());
            return new Dimension(panelDimention,panelDimention);
        }
    }
}
