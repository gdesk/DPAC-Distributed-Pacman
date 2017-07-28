package client.view.playground;

import client.view.MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by chiaravarini on 10/07/17.
 */
public class MicroMapPanel extends JPanel {

    private final static int BOUND = 30;

    public MicroMapPanel(){
        int shortedSide = (int)Math.min(MainFrame.DIMENSION.getWidth(), MainFrame.DIMENSION.getHeight());
        setSize((int) shortedSide/5, (int)shortedSide/5);
        setBounds((int) MainFrame.DIMENSION.getWidth()-getWidth()-BOUND, (int) MainFrame.DIMENSION.getHeight()-getHeight()-BOUND,getWidth(),getHeight());

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
    }


}
